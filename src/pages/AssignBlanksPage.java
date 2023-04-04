package pages;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AssignBlanksPage extends BorderPane {

    public AssignBlanksPage(Stage stage) {
        Label welcomeLabel = new Label("Assign Blanks");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        ComboBox<String> sourceCombo = new ComboBox<>(FXCollections.observableArrayList("placeholderoption1", "placeholderoption2", "placeholderoption3"));
        sourceCombo.setPromptText("Select Agent");

        ComboBox<String> destinationCombo = new ComboBox<>(FXCollections.observableArrayList("placeholderoption1", "placeholderoption2", "placeholderoption3"));
        destinationCombo.setPromptText("Select Blank");

        VBox centerBox = new VBox(10, sourceCombo, destinationCombo);
        centerBox.setAlignment(Pos.CENTER);

        BorderPane.setMargin(welcomeLabel, new Insets(45));
        BorderPane.setMargin(centerBox, new Insets(50, 10, 10, 10));

        // Create the HBox for the buttons and add them
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        Button button1 = new Button("Confirm Assignment");
        Button button2 = new Button("Return To Home");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        buttonBox.getChildren().addAll(button1, spacer, button2);

        // Add margin to the bottom and left of the buttonBox
        BorderPane.setMargin(buttonBox, new Insets(0, 10, 10, 10));

        // Add the HBox to the bottom of the BorderPane
        this.setBottom(buttonBox);

        this.setTop(welcomeLabel);
        this.setCenter(centerBox);
    }
}