package pages.traveladvisor;

import dbfuncs.DBMethods;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import pages.traveladvisor.TravelAdvisorMainPage;

public class TravelAdvisorRecordSale extends BorderPane {

    public TravelAdvisorRecordSale(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("Record Sale");
        pageTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 36));
        pageTitle.setUnderline(true);
        pageTitle.setTextAlignment(TextAlignment.CENTER);

        // Create Back To Home Navigation Button
        Button backToHomePage = new Button("Back To Home");

        // Page Routes
        backToHomePage.setOnAction(event -> {
            TravelAdvisorMainPage travelAdvisorMainPage = new TravelAdvisorMainPage(stage);
            Scene scene = new Scene(travelAdvisorMainPage, 850, 500);
            stage.setScene(scene);
        });

        // Create Input Fields
        TextField customerNameField = new TextField();
        customerNameField.setPromptText("Customer Name");

        TextField addressField = new TextField();
        addressField.setPromptText("Address");

        TextField billingAddressField = new TextField();
        billingAddressField.setPromptText("Billing Address");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Phone Number");

        DatePicker dateField = new DatePicker();
        dateField.setPromptText("Date");

        ChoiceBox<String> destinationField = new ChoiceBox<>();
        destinationField.getItems().addAll("Domestic", "International");
        destinationField.setValue("Domestic");

        ChoiceBox<String> payField = new ChoiceBox<>();
        payField.getItems().addAll("Now", "Later");
        payField.setValue("Now");

        ChoiceBox<String> paymentMethodField = new ChoiceBox<>();
        paymentMethodField.getItems().addAll("Cash", "Card");
        paymentMethodField.setValue("Cash");

        TextField currencyField = new TextField();
        currencyField.setPromptText("Currency");

        TextField priceField = new TextField();
        priceField.setPromptText("Price");

        TextField cardNumberField = new TextField();
        cardNumberField.setPromptText("Card Number");

        TextField cvvField = new TextField();
        cvvField.setPromptText("CVV");

        TextField discountField = new TextField();
        discountField.setPromptText("Discount");

        // Create Submit Button
        Button submitButton = new Button("Record Sale");
        submitButton.setOnAction(event -> {
            String customerName = customerNameField.getText();
            String address = addressField.getText();
            String billingAddress = billingAddressField.getText();
            String email = emailField.getText();
            String phoneNumber = phoneNumberField.getText();
            String date = dateField.getValue().toString();
            String destination = destinationField.getValue();
            String payNowOrLater = payField.getValue();
            String paymentMethod = paymentMethodField.getValue();
            String currencyName = currencyField.getText();
            int priceAmount = Integer.parseInt(priceField.getText());
            String cardNumber = cardNumberField.getText();
            String cardCVV = cvvField.getText();
            String discountGiven = discountField.getText();

            DBMethods.recordSale(customerName, address, email, phoneNumber, date, destination, payNowOrLater, paymentMethod, currencyName, priceAmount, cardNumber, cardCVV, discountGiven);

            System.out.println("Form submitted!");
        });

        // Create the Left Form GridPane and add the input fields to it
        GridPane leftFormGridPane = new GridPane();
        leftFormGridPane.setHgap(10);
        leftFormGridPane.setVgap(10);

        // Create the Right Form GridPane and add the submit button to it
        GridPane rightFormGridPane = new GridPane();
        rightFormGridPane.setHgap(10);
        rightFormGridPane.setVgap(10);

        leftFormGridPane.add(new Label("Customer Name:"), 0, 0);
        leftFormGridPane.add(customerNameField, 1, 0);

        leftFormGridPane.add(new Label("Address:"), 0, 1);
        leftFormGridPane.add(addressField, 1, 1);

        leftFormGridPane.add(new Label("Billing Address:"), 0, 2);
        leftFormGridPane.add(billingAddressField, 1, 2);

        leftFormGridPane.add(new Label("Email:"), 0, 3);
        leftFormGridPane.add(emailField, 1, 3);

        leftFormGridPane.add(new Label("Phone Number:"), 0, 4);
        leftFormGridPane.add(phoneNumberField, 1, 4);

        leftFormGridPane.add(new Label("Date:"), 0, 5);
        leftFormGridPane.add(dateField, 1, 5);

        leftFormGridPane.add(new Label("Destination:"), 0, 6);
        leftFormGridPane.add(destinationField, 1, 6);

        rightFormGridPane.add(new Label("Pay:"), 0, 0);
        rightFormGridPane.add(payField, 1, 0);

        rightFormGridPane.add(new Label("Payment Method:"), 0, 1);
        rightFormGridPane.add(paymentMethodField, 1, 1);

        rightFormGridPane.add(new Label("Currency:"), 0, 2);
        rightFormGridPane.add(currencyField, 1, 2);

        rightFormGridPane.add(new Label("Price:"), 0, 3);
        rightFormGridPane.add(priceField, 1, 3);

        rightFormGridPane.add(new Label("Card Number:"), 0, 4);
        rightFormGridPane.add(cardNumberField, 1, 4);

        rightFormGridPane.add(new Label("CVV:"), 0, 5);
        rightFormGridPane.add(cvvField, 1, 5);

        rightFormGridPane.add(new Label("Discount:"), 0, 6);
        rightFormGridPane.add(discountField, 1, 6);

        rightFormGridPane.add(submitButton, 2, 6);

        // Create two containers to hold the buttons
        VBox leftBox = new VBox(20, leftFormGridPane);
        leftBox.setAlignment(Pos.CENTER);
        VBox rightBox = new VBox(20,  rightFormGridPane);
        rightBox.setAlignment(Pos.CENTER);

        // Create a container to hold the two containers
        HBox centerBox = new HBox(20, leftBox, rightBox);
        centerBox.setAlignment(Pos.CENTER);

        BorderPane.setMargin(pageTitle, new Insets(65));
        BorderPane.setAlignment(pageTitle, Pos.CENTER);
        BorderPane.setMargin(backToHomePage, new Insets(10));
        BorderPane.setMargin(centerBox, new Insets(-110, 10, 10, 10));

        this.setTop(pageTitle);
        this.setCenter(centerBox);
        this.setBottom(backToHomePage);
    }
}
