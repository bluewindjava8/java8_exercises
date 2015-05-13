package digitalwatch;

import digitalwatch.arcadjuster.ArcAdjuster;
import digitalwatch.arcadjuster.HourArcAdjuster;
import digitalwatch.arcadjuster.MinuteArcAdjuster;
import digitalwatch.arcadjuster.SecondArcAdjuster;
import digitalwatch.prefs.Point;
import digitalwatch.prefs.WatchPreferences;
import digitalwatch.property.ColorSelectDialog;
import digitalwatch.property.Colors;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.dialog.Dialogs;

public class DigitalWatch extends Application {

    private static final int MENU_BAR_HEIGHT = 30;
    private static final int ARC_STROKE_WIDTH = 10;
    private Group root = new Group();
    private Scene scene = new Scene(root, 400, 400);
    private Stage stage;

    private ArcAdjuster secondAdjuster = new SecondArcAdjuster();
    private ArcAdjuster minuteAdjuster = new MinuteArcAdjuster();
    private ArcAdjuster hourAdjuster = new HourArcAdjuster();
    private Arc secondArc = generateArc(10, Color.BLACK, root);
    private Arc minuteArc = generateArc(10, Color.BLUE, root);
    private Arc hourArc = generateArc(10, Color.RED, root);

    private Canvas canvas;
    private GraphicsContext gc;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    WatchPreferences watchPref = new WatchPreferences();

    @Override
    public void init() throws Exception {
        super.init();

    }

    @Override
    public void stop() throws Exception {
//        System.out.println("Stage is stopping");
//        Font font = gc.getFont();
//        watchPref.putFont(font);
//        watchPref.putFontColor((Color) gc.getFill());
//        watchPref.putBackgroundColor((Color) scene.getFill());
//        //Point winPos = new Point(scene.getX(), scene.getY());
//        Point leftUpCorner = new Point(stage.getX(), stage.getY());
//        watchPref.putPos(leftUpCorner);

        saveWatchPreferences();
        super.stop();

    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        root = new Group();
        scene = new Scene(root, 400, 400, Color.BLACK);
        scene.setOnMouseClicked(event -> System.out.println("X = " + event.getX() + ", Y = " + event.getY()));
        stage.initStyle(StageStyle.UNDECORATED);
        //stage.setFullScreen(true);
        //stage.initOwner(null);
        stage.fullScreenProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> prop, Boolean wasIconified, Boolean isIconified) {
                System.out.println("iiiiiiiiiiiiiignore fullscreen");
                //stage.setFullScreen(false);
            }
        });

        secondArc = generateArc(10, Color.BLACK, root);
        minuteArc = generateArc(10, Color.BLUE, root);
        hourArc = generateArc(10, Color.RED, root);

        Line line = new Line();
        line.startXProperty().set(0);
        line.startYProperty().bind(secondArc.centerYProperty());
        line.endXProperty().bind(scene.widthProperty());
        line.endYProperty().bind(secondArc.centerYProperty());
        root.getChildren().add(line);

        Line vline = new Line();
        vline.startYProperty().set(0);
        vline.startXProperty().bind(secondArc.centerXProperty());
        vline.endYProperty().bind(scene.heightProperty());
        vline.endXProperty().bind(secondArc.centerXProperty());
        root.getChildren().add(vline);

        canvas = new Canvas(400, 400);
        gc = canvas.getGraphicsContext2D();

        canvas.widthProperty().bind(scene.widthProperty());
        canvas.heightProperty().bind(scene.heightProperty());
        root.getChildren().add(canvas);

        bindComponents();
        createMenu(primaryStage);

        restoreWatchPreferences();

        primaryStage.setResizable(false);
        primaryStage.setTitle("Digital Watch");
        primaryStage.setScene(scene);
        primaryStage.show();

        drawArcs();
        drawTimeStr();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), ev -> {
            if (!stage.isShowing()) {
                stage.show();
            }

            drawArcs();
            drawTimeStr();

        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private Arc generateArc(int strokeWith, Color strokeColor, Group root) {
        Arc arc = new Arc();
        arc.setStartAngle(90.0f);
        arc.setLength(0.0f);
        arc.setType(ArcType.OPEN);
        arc.setFill(null);
        arc.setStroke(strokeColor);
        arc.setStrokeWidth(1);
        root.getChildren().add(arc);

        return arc;
    }

    private void bindComponents() {

        secondArc.centerXProperty().bind(Bindings.divide(scene.widthProperty(), 2));
        secondArc.centerYProperty().bind(Bindings.add(secondArc.radiusYProperty(), MENU_BAR_HEIGHT + ARC_STROKE_WIDTH / 2));
        secondArc.radiusXProperty().bind(secondArc.radiusYProperty());
        secondArc.radiusYProperty().bind(Bindings.createDoubleBinding(() -> (scene.getHeight() - MENU_BAR_HEIGHT - ARC_STROKE_WIDTH) / 2, scene.heightProperty()));

        minuteArc.centerXProperty().bind(secondArc.centerXProperty());
        minuteArc.centerYProperty().bind(secondArc.centerYProperty());
        minuteArc.radiusXProperty().bind(Bindings.divide(secondArc.radiusXProperty(), 1.3));
        minuteArc.radiusYProperty().bind(Bindings.divide(secondArc.radiusYProperty(), 1.3));

        hourArc.centerXProperty().bind(secondArc.centerXProperty());
        hourArc.centerYProperty().bind(secondArc.centerYProperty());
        hourArc.radiusXProperty().bind(Bindings.divide(secondArc.radiusXProperty(), 1.7));
        hourArc.radiusYProperty().bind(Bindings.divide(secondArc.radiusYProperty(), 1.7));

    }

    private double xOffset = 0;
    private double yOffset = 0;

    private void createMenu(Stage primaryStage) {
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        menuBar.setPrefHeight(MENU_BAR_HEIGHT);
//    menuBar.layoutXProperty().set(10);
//    menuBar.layoutYProperty().set(10);
        root.getChildren().add(menuBar);

        Menu fileMenu = new Menu("File");
        MenuItem fontSelectMenuItem = new MenuItem("Font Select");
        MenuItem colorSelectMenuItem = new MenuItem("Color Select");
        MenuItem exitMenuItem = new MenuItem("Exit");

        fontSelectMenuItem.setOnAction(actionEvent -> selectFont());
        colorSelectMenuItem.setOnAction(actionEvent -> selectColor());
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        fileMenu.getItems().addAll(fontSelectMenuItem, colorSelectMenuItem,
                new SeparatorMenuItem(), exitMenuItem);

        menuBar.getMenus().add(fileMenu);

        menuBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = primaryStage.getX() - event.getScreenX();
                yOffset = primaryStage.getY() - event.getScreenY();
            }
        });
        
        menuBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() + xOffset);
                primaryStage.setY(event.getScreenY() + yOffset);
            }
        });
    }

    private void selectFont() {
        Optional<Font> response = Dialogs.create()
                .masthead("Choose what you like")
                .showFontSelector(Font.font("Times New Roman"));

        response.ifPresent(font -> {
            System.out.println("font changed.");
            gc.setFont(font);
            //this.redrawAllComponents();
            this.resizeSceneByFont();
            this.drawTimeStr();
        });
    }

    private void selectColor() {
        Dialog colorDialog = new ColorSelectDialog();
        colorDialog.initOwner(stage);
        Optional<Colors> colorsOptional = colorDialog.showAndWait();
        colorsOptional.ifPresent(colors -> {
            System.out.println(colors);
            gc.setFill(colors.getFontColor());

            drawTimeStr();

            scene.setFill(colors.getBackgroundColor());
        });
    }

    private void resizeSceneByFont() {
        Text text = new Text("00:00:00");
        text.setFont(gc.getFont());
        double textWidth = text.getLayoutBounds().getWidth();
        double newWidth = (textWidth + 15) * 1.3 * 1.7 + MENU_BAR_HEIGHT;
        stage.setWidth(newWidth);
        stage.setHeight(newWidth);
        stage.hide();
        stage.show();
    }

    private void drawArcs() {
        secondAdjuster.adjustArcByCurrentTime(secondArc);
        minuteAdjuster.adjustArcByCurrentTime(minuteArc);
        hourAdjuster.adjustArcByCurrentTime(hourArc);
    }

    private void drawTimeStr() {
        Text text = new Text("00:00:00");
        text.setFont(gc.getFont());
        double textWidth = text.getLayoutBounds().getWidth();

        LocalTime now = LocalTime.now();
        String timeStr = formatter.format(now);
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        //gc.clearRect(0, 0, stage.getWidth(), stage.getHeight());
        gc.fillText(timeStr, secondArc.getCenterX() - textWidth / 2, secondArc.getCenterY());
        gc.applyEffect(new DropShadow(10, 20, 20, (Color)gc.getFill()));
    }

    private void saveWatchPreferences() {
        Font font = gc.getFont();
        watchPref.putFont(font);
        watchPref.putFontColor((Color) gc.getFill());
        watchPref.putBackgroundColor((Color) scene.getFill());
        Point leftUpCorner = new Point(stage.getX(), stage.getY());
        watchPref.putPos(leftUpCorner);
    }

    private void restoreWatchPreferences() {
        gc.setFont(watchPref.getFont(gc.getFont()));
        gc.setFill(watchPref.getFontColor(Color.BLACK));
        scene.setFill(watchPref.getBackgroundColor(Color.TRANSPARENT));

        Point leftUpCorner = watchPref.getPos(new Point(stage.getX(), stage.getY()));
        stage.setX(leftUpCorner.getX());
        stage.setY(leftUpCorner.getY());

        resizeSceneByFont();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
