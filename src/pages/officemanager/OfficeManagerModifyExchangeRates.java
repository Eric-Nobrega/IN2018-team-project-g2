package pages.officemanager;

import dbfuncs.DBMethods;
import interfaces.Customer;
import interfaces.ExchangeRate;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class OfficeManagerModifyExchangeRates extends BorderPane {

    private final TextField currencyNameField = new TextField();
    private final TextField amountInUSDField = new TextField();


    public OfficeManagerModifyExchangeRates(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("Modify Exchange Rates");
        pageTitle.setFont(Font.font("Karla", FontWeight.BOLD, 36));
        pageTitle.setUnderline(true);
        pageTitle.setTextAlignment(TextAlignment.CENTER);

        // Create Back To Home Navigation Button
        Button backToHomePage = new Button("Back To Home");

        // Page Routes
        backToHomePage.setOnAction(event -> {
            OfficeManagerMainPage officeManagerMainPage = new OfficeManagerMainPage(stage);
            Scene scene = new Scene(officeManagerMainPage, 850, 500);
            stage.setScene(scene);
        });

        // Create Search Button
        Button searchButton = new Button("Search");
        searchButton.setPrefWidth(200);

        searchButton.setOnAction(event -> {
            String currencyName = currencyNameField.getText();
            if (!(currencyName == null)) {
                ExchangeRate exchangeRate = DBMethods.getExchangeRate(currencyName);
                if (exchangeRate != null) {
                    currencyNameField.setText(exchangeRate.getCurrencyName());
                    amountInUSDField.setText(String.valueOf(exchangeRate.getAmountUSD()));
                } else {
                    // display error message
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Customer not found.");
                    alert.showAndWait();
                }
            } else {
                // display error message
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a full name.");
                alert.showAndWait();
            }
        });

        // Create Update Button
        Button updateButton = new Button("Update");
        updateButton.setPrefWidth(200);

        updateButton.setOnAction(event -> {
            // capture the text field values
            String currencyName = currencyNameField.getText();
            String amountInUSD = amountInUSDField.getText();

            // IF the fields are all filled out
            if (!(currencyName.isEmpty()) && !(amountInUSD.isEmpty())) {
                // convert amountInUSD to integer
                int amountUSD = Integer.parseInt(amountInUSD);
                // call the database update method
                DBMethods.updateExchangeRate(currencyName, amountUSD);
                // redirect the user to the office manager homepage
                OfficeManagerMainPage officeManagerMainPage = new OfficeManagerMainPage(stage);
                Scene scene = new Scene(officeManagerMainPage, 850, 500);
                stage.setScene(scene);
            } else {
                // display error message
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields.");
                alert.showAndWait();
            }
        });

        // Create VBox to hold input fields and Update button
        VBox inputFieldsVBox = new VBox(10);
        inputFieldsVBox.setAlignment(Pos.CENTER);
        inputFieldsVBox.getChildren().addAll(
                new Label("Currency Name"), currencyNameField,
                new Label("USD Amount"), amountInUSDField,
                searchButton,
                updateButton
        );
        inputFieldsVBox.setPadding(new Insets(20));
        inputFieldsVBox.setStyle("-fx-background-color: #f2f2f2;");

        BorderPane.setMargin(pageTitle, new Insets(65));
        BorderPane.setAlignment(pageTitle, Pos.CENTER);
        BorderPane.setMargin(backToHomePage, new Insets(10));
        BorderPane.setMargin(inputFieldsVBox, new Insets(10));

        this.setTop(pageTitle);
        this.setCenter(inputFieldsVBox);
        this.setBottom(backToHomePage);
    }

}
