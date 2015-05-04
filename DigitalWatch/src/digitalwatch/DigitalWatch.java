package digitalwatch;

import digitalwatch.arcadjuster.ArcAdjuster;
import digitalwatch.arcadjuster.HourArcAdjuster;
import digitalwatch.arcadjuster.MinuteArcAdjuster;
import digitalwatch.arcadjuster.SecondArcAdjuster;
import digitalwatch.property.ColorSelectDialog;
import digitalwatch.property.PropertyDialog;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.Stream;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import org.controlsfx.dialog.Dialogs;

public class DigitalWatch extends Application {

    private static final int MENU_BAR_HEIGHT = 30;
    private static final int ARC_STROKE_WIDTH = 10;
    Group root = new Group();
    Scene scene = new Scene(root, 400, 400);
    Stage stage ;

    Arc secondArc = generateArc(10, Color.BLACK, root);
    Arc minuteArc = generateArc(10, Color.BLUE, root);
    Arc hourArc = generateArc(10, Color.RED, root);

    Canvas canvas;
    GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        root = new Group();
        scene = new Scene(root, 400, 400);

        secondArc = generateArc(10, Color.BLACK, root);
        minuteArc = generateArc(10, Color.BLUE, root);
        hourArc = generateArc(10, Color.RED, root);

        canvas = new Canvas(400, 400);
        gc = canvas.getGraphicsContext2D();
        //drawShapes(gc);
        drawText();
        root.getChildren().add(canvas);

        bindComponents();
        createMenu(primaryStage);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

        ArcAdjuster secondAdjuster = new SecondArcAdjuster();
        ArcAdjuster minuteAdjuster = new MinuteArcAdjuster();
        ArcAdjuster hourAdjuster = new HourArcAdjuster();

        primaryStage.widthProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            primaryStage.setHeight(t1.doubleValue());
        });
        primaryStage.heightProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            primaryStage.setWidth(t1.doubleValue());
        });

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        //Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> arc.setLength(arc.getLength() + 10)));
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), ev -> {
            secondAdjuster.adjustArcByCurrentTime(secondArc);
            minuteAdjuster.adjustArcByCurrentTime(minuteArc);
            hourAdjuster.adjustArcByCurrentTime(hourArc);

            LocalTime now = LocalTime.now();
            String timeStr = formatter.format(now);

            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            Text text = new Text("00:00:00");
            text.setFont(gc.getFont());
            double textWidth = text.getLayoutBounds().getWidth();
            double textHeight = text.getLayoutBounds().getHeight();

            System.out.println("font width = " + textWidth + ", font height = " + textHeight);
            System.out.println("text.x =" + (secondArc.getCenterX() - textWidth / 2) + ", text.y = " + (secondArc.getCenterY() - textHeight / 2));
            System.out.println("centerx = " + secondArc.getCenterX() + ", centery = " + secondArc.getCenterY());
            gc.fillText(timeStr, secondArc.getCenterX() - textWidth / 2, secondArc.getCenterY());

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
        root.getChildren().add(arc);

        return arc;
    }

    private void drawText() {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        //Font font = new Font()
        //gc.setFont();
        gc.setLineWidth(5);
        gc.fillText("12:33:55", 40, 50);
        gc.strokeLine(40, 10, 10, 40);
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
    }

//    private void showPropertyDialog() {
//        PropertyDialog dialog = new PropertyDialog();
//        Optional<Pair<String, String>> result = dialog.showAndWait();
//
//        result.ifPresent(usernamePassword -> {
//            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
//        });
//    }
    private void selectFont() {
        Optional<Font> response = Dialogs.create()
                .masthead("Choose what you like")
                .showFontSelector(Font.font("Times New Roman"));

        response.ifPresent(font -> {
            System.out.println("\n" + font.toString());
            Text text = new Text("00:00:00");
            text.setFont(font);
            text.setFill(Color.RED);
            double textWidth = text.getLayoutBounds().getWidth();
            if (textWidth > hourArc.getRadiusX() * 2 - 20) {
                stage.setWidth(stage.getWidth() * 1.1);
                stage.setHeight(stage.getHeight() * 1.1);
            }
            System.out.println(text.getLayoutBounds().getWidth());
            gc.setFont(font);

        });
    }

    private void selectColor() {
        Dialog colorDialog = new ColorSelectDialog();
        Optional<Color[]> colorsOptional = colorDialog.showAndWait();
        colorsOptional.ifPresent(colors -> {
            Stream.of(colors).forEach(System.out::println);
        });
    }

    /*
     private void showPropertyDialog() {
     // Create the custom dialog.
     Dialog<Pair<String, String>> dialog = new Dialog<>();
     dialog.setTitle("Login Dialog");
     dialog.setHeaderText("Look, a Custom Login Dialog");

     // Set the icon (must be included in the project).
     //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

     // Set the button types.
     ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
     dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

     // Create the username and password labels and fields.
     GridPane grid = new GridPane();
     grid.setHgap(10);
     grid.setVgap(10);
     grid.setPadding(new Insets(20, 150, 10, 10));

     TextField username = new TextField();
     username.setPromptText("Username");
     PasswordField password = new PasswordField();
     password.setPromptText("Password");

     grid.add(new Label("Username:"), 0, 0);
     grid.add(username, 1, 0);
     grid.add(new Label("Password:"), 0, 1);
     grid.add(password, 1, 1);

     // Enable/Disable login button depending on whether a username was entered.
     Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
     loginButton.setDisable(true);

     // Do some validation (using the Java 8 lambda syntax).
     username.textProperty().addListener((observable, oldValue, newValue) -> {
     loginButton.setDisable(newValue.trim().isEmpty());
     });

     dialog.getDialogPane().setContent(grid);

     // Request focus on the username field by default.
     Platform.runLater(() -> username.requestFocus());

     // Convert the result to a username-password-pair when the login button is clicked.
     dialog.setResultConverter(dialogButton -> {
     if (dialogButton == loginButtonType) {
     return new Pair<>(username.getText(), password.getText());
     }
     return null;
     });

     Optional<Pair<String, String>> result = dialog.showAndWait();

     result.ifPresent(usernamePassword -> {
     System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
     });
     }
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
