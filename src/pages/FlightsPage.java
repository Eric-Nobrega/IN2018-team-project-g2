package pages;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlightsPage extends BorderPane {

    public FlightsPage(Stage stage) {
        Label welcomeLabel = new Label("Flights");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        ComboBox<String> sourceCombo = new ComboBox<>(FXCollections.observableArrayList("placeholderoption1", "placeholderoption2", "placeholderoption3"));
        sourceCombo.setPromptText("Source City");

        ComboBox<String> destinationCombo = new ComboBox<>(FXCollections.observableArrayList("placeholderoption1", "placeholderoption2", "placeholderoption3"));
        destinationCombo.setPromptText("Destination City");

        Label priceLabel = new Label("Final price: $1000"); // replace with actual final price

        VBox centerBox = new VBox(10, sourceCombo, destinationCombo, priceLabel);
        centerBox.setAlignment(Pos.CENTER);

        BorderPane.setMargin(welcomeLabel, new Insets(45));
        BorderPane.setMargin(centerBox, new Insets(50, 10, 10, 10));

        this.setTop(welcomeLabel);
        this.setCenter(centerBox);
    }
}