package digitalwatch.property;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ColorSelectDialog extends Dialog<Colors> {

    public ColorSelectDialog() {
        setTitle("Color Select Dialog");
        setHeaderText("Set font color and background color.");

        // Set the icon (must be included in the project).
        //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));
        // Set the button types.
        //ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        final ColorPicker fontColorPicker = new ColorPicker();
        final ColorPicker backgroundColorPicker = new ColorPicker();

        Label fontColorLabel = new Label("Font Color:");
        Label backgroundColorLabel = new Label("Background Color:");
        grid.add(fontColorLabel, 0, 0);
        grid.add(fontColorPicker, 1, 0);
        grid.add(backgroundColorLabel, 0, 1);
        grid.add(backgroundColorPicker, 1, 1);

        GridPane.setHalignment(fontColorLabel, HPos.RIGHT);
        GridPane.setHalignment(backgroundColorLabel, HPos.RIGHT);

        // Enable/Disable login button depending on whether a username was entered.
        Node okButton = getDialogPane().lookupButton(ButtonType.OK);
        //okButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
//        username.textProperty().addListener((observable, oldValue, newValue) -> {
//            okButton.setDisable(newValue.trim().isEmpty());
//        });
//
        getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> fontColorPicker.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return new Colors(fontColorPicker.getValue(), backgroundColorPicker.getValue());
            }
            return null;
        });
    }

}
