package pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PaymentPage extends BorderPane {

    public PaymentPage(Stage stage) {
        Label welcomeLabel = new Label("Payment");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // create the label and text field
        Label label1 = new Label("Passenger Name:");
        TextField textfield1 = new TextField();
        textfield1.setMaxWidth(200);

        Label label2 = new Label("Booking ID No:");
        TextField textfield2 = new TextField();
        textfield2.setMaxWidth(200);

        Label label3 = new Label("Total Price:");
        TextField textfield3 = new TextField();
        textfield3.setMaxWidth(200);

        Label label4 = new Label("Cardholder Name:");
        TextField textfield4 = new TextField();
        textfield4.setMaxWidth(200);

        Label label5 = new Label("Card Number:");
        TextField textfield5 = new TextField();
        textfield5.setMaxWidth(200);

        Label label6 = new Label("Expiry Date:");
        TextField textfield6 = new TextField();
        textfield6.setMaxWidth(200);

        Label label7 = new Label("CVV:");
        TextField textfield7 = new TextField();
        textfield7.setMaxWidth(200);

        // create the grid pane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        // add the label and text field to the grid pane
        gridPane.add(label1, 0, 0);
        gridPane.add(textfield1, 1, 0);
        gridPane.add(label2, 0, 1);
        gridPane.add(textfield2, 1, 1);
        gridPane.add(label3, 0, 2);
        gridPane.add(textfield3, 1, 2);
        gridPane.add(label4, 0, 3);
        gridPane.add(textfield4, 1, 3);
        gridPane.add(label5, 0, 4);
        gridPane.add(textfield5, 1, 4);
        gridPane.add(label6, 0, 5);
        gridPane.add(textfield6, 1, 5);
        gridPane.add(label7, 0, 6);
        gridPane.add(textfield7, 1, 6);

        gridPane.setMaxWidth(500);

        // Create the HBox for the buttons and add them
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        Button button1 = new Button("Continue To Payment");
        Button button2 = new Button("Return To Home");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        buttonBox.getChildren().addAll(button1, spacer, button2);

        // Add the HBox to the bottom of the BorderPane
        this.setBottom(buttonBox);

        this.setTop(welcomeLabel);
        this.setCenter(gridPane);
        this.setPadding(new Insets(20));
    }
}
