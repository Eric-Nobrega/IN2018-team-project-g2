package pages;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
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

        // Create the HBox for the buttons and add them
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        Button button1 = new Button("Continue To Payment");
        Button button2 = new Button("Return To Home");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        buttonBox.getChildren().addAll(button1, spacer, button2);

        // set payments page route
        button1.setOnAction(event -> {
            PaymentPage paymentPage = new PaymentPage(stage);
            Scene scene = new Scene(paymentPage, 400, 400);
            stage.setScene(scene);
        });

        // Add margin to the bottom and left of the buttonBox
        BorderPane.setMargin(buttonBox, new Insets(0, 10, 10, 10));

        // Add the HBox to the bottom of the BorderPane
        this.setBottom(buttonBox);

        this.setTop(welcomeLabel);
        this.setCenter(centerBox);
    }
}
