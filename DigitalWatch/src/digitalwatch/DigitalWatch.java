package digitalwatch;

import digitalwatch.arcadjuster.ArcAdjuster;
import digitalwatch.arcadjuster.HourArcAdjuster;
import digitalwatch.arcadjuster.MinuteArcAdjuster;
import digitalwatch.arcadjuster.SecondArcAdjuster;
import digitalwatch.prefs.Point;
import digitalwatch.prefs.WatchPreferences;
import digitalwatch.property.ColorSelectDialog;
import digitalwatch.property.Colors;
import digitalwatch.timesource.RealTimeSource;
import digitalwatch.timesource.TimeSource;
import digitalwatch.timesource.VirtualTimeSource;
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
    private static final Color SECOND_ARC_COLOR = Color.BLACK;
    private static final Color MINUTE_ARC_COLOR = Color.BLUE;
    private static final Color HOUR_ARC_COLOR = Color.RED;

    private final Group root = new Group();
    private final Scene scene = new Scene(root, 400, 400);
    private Stage stage;

    private final TimeSource timeSource = new VirtualTimeSource();

    private final ArcAdjuster secondAdjuster = new SecondArcAdjuster(timeSource);
    private final ArcAdjuster minuteAdjuster = new MinuteArcAdjuster(timeSource);
    private final ArcAdjuster hourAdjuster = new HourArcAdjuster(timeSource);

//    private final Arc secondArc = generateArc(ARC_STROKE_WIDTH, SECOND_ARC_COLOR, root);
//    private final Arc minuteArc = generateArc(ARC_STROKE_WIDTH, MINUTE_ARC_COLOR, root);
//    private final Arc hourArc = generateArc(ARC_STROKE_WIDTH, HOUR_ARC_COLOR, root);
    private final TimeArc secondArc = new TimeArc(ARC_STROKE_WIDTH, SECOND_ARC_COLOR);
    private final TimeArc minuteArc = new TimeArc(ARC_STROKE_WIDTH, MINUTE_ARC_COLOR);
    private final TimeArc hourArc = new TimeArc(ARC_STROKE_WIDTH, HOUR_ARC_COLOR);

    private final Canvas canvas = generateCanvas();
    private final GraphicsContext gc = canvas.getGraphicsContext2D();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final WatchPreferences watchPref = new WatchPreferences();

    @Override
    public void init() throws Exception {
        super.init();

    }

    @Override
    public void stop() throws Exception {
        saveWatchPreferences();
        super.stop();

    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        //scene.setOnMouseClicked(event -> System.out.println("X = " + event.getX() + ", Y = " + event.getY()));
        stage.initStyle(StageStyle.UNDECORATED);

        root.getChildren().addAll(canvas, secondArc, minuteArc, hourArc);

        Runnable dragEndHandler = () -> {
            System.out.println("secondArc.length = " + -secondArc.getLength());
            System.out.println("minuteArc.length = " + -minuteArc.getLength());
            System.out.println("hourArc.length = " + -hourArc.getLength());
            
            LocalTime newTime = TimeArc.toTime(-hourArc.getLength(), -minuteArc.getLength(), -secondArc.getLength(),timeSource);
            System.out.println("newTime = " + formatter.format(newTime));
            timeSource.setCurrentTime(newTime);
            System.out.println("after set = " + formatter.format(timeSource.getCurrentTime()));
            
        };
        secondArc.setDragEndHanlder(dragEndHandler);
        minuteArc.setDragEndHanlder(dragEndHandler);
        hourArc.setDragEndHanlder(dragEndHandler);

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

//        canvas = new Canvas(400, 400);
//        gc = canvas.getGraphicsContext2D();
//
//        canvas.widthProperty().bind(scene.widthProperty());
//        canvas.heightProperty().bind(scene.heightProperty());
//        root.getChildren().add(canvas);
//        canvas.setOnMouseClicked(event -> {
//            System.out.println("cavas mouse clicked.");
//        });
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
        arc.setStrokeWidth(strokeWith);
        arc.setEffect(new DropShadow(20, 0, 0, strokeColor));
        //root.getChildren().add(arc);

        arc.setOnMouseDragged(event -> {
            double theta = Math.toDegrees(Math.atan2(arc.getCenterY() - event.getY(), event.getX() - arc.getCenterX()));

            double arcLength = theta - 90;
            arcLength = arcLength < 0 ? arcLength : arcLength - 360;
            arc.setLength(arcLength);
            System.out.println("event.getY() = " + event.getY() + ", centery = " + arc.getCenterY() + "event.getX() = " + event.getX() + ", centerX = " + arc.getCenterX());
            System.out.println("nnnnnnnnnnnnnn theat = " + theta + ", arcLength = " + arcLength);
        });

        arc.setOnMouseClicked(event -> {
            System.out.println("second arc mouse clicked  x = " + event.getX());
        });
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

        menuBar.setOnMousePressed((MouseEvent event) -> {
            xOffset = primaryStage.getX() - event.getScreenX();
            yOffset = primaryStage.getY() - event.getScreenY();
        });

        menuBar.setOnMouseDragged((MouseEvent event) -> {
            primaryStage.setX(event.getScreenX() + xOffset);
            primaryStage.setY(event.getScreenY() + yOffset);
        });
    }

    private Canvas generateCanvas() {
        Canvas canvas = new Canvas(400, 400);

        canvas.widthProperty().bind(scene.widthProperty());
        canvas.heightProperty().bind(scene.heightProperty());
        canvas.setOnMouseClicked(event -> {
            System.out.println("cavas mouse clicked.");
        });

        return canvas;
    }

    private void selectFont() {
        Optional<Font> response = Dialogs.create()
                .masthead("Choose what you like")
                //.showFontSelector(Font.font("Times New Roman"));
                .showFontSelector(gc.getFont());

        response.ifPresent(font -> {
            System.out.println("font changed.");
            gc.setFont(font);
            //this.redrawAllComponents();
            this.resizeSceneByFont();
            this.drawTimeStr();
        });
    }

    private void selectColor() {
        Dialog colorDialog = new ColorSelectDialog((Color) gc.getFill(), (Color) scene.getFill());
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
        if (!secondArc.isDragging()) {
            secondAdjuster.adjustArcByCurrentTime(secondArc);
        }

        if (!minuteArc.isDragging()) {
            minuteAdjuster.adjustArcByCurrentTime(minuteArc);
        }

        if (!hourArc.isDragging()) {
            hourAdjuster.adjustArcByCurrentTime(hourArc);
        }
    }

    private void drawTimeStr() {
        Text text = new Text("00:00:00");
        text.setFont(gc.getFont());
        double textWidth = text.getLayoutBounds().getWidth();

        //LocalTime now = LocalTime.now();
        LocalTime now = timeSource.getCurrentTime();
        String timeStr = formatter.format(now);
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        //gc.clearRect(0, 0, stage.getWidth(), stage.getHeight());
        gc.fillText(timeStr, secondArc.getCenterX() - textWidth / 2, secondArc.getCenterY());
        gc.applyEffect(new DropShadow(10, 20, 20, (Color) gc.getFill()));
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
