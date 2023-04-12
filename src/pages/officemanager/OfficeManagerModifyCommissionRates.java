package pages.officemanager;

import dbfuncs.DBMethods;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class OfficeManagerModifyCommissionRates extends BorderPane {

    public OfficeManagerModifyCommissionRates(Stage stage){

        //Create Title Text:

        Label pageTitle = new Label("Modify Commission Rate");
        pageTitle.setFont(Font.font("Karla",FontWeight.BOLD,36));
        pageTitle.setUnderline(true);
        pageTitle.setTextAlignment(TextAlignment.CENTER);

        //Create Back to Home Button:
        Button backToHomePage = new Button("Back to Home");

        //The page routing:
        backToHomePage.setOnAction(event -> {
            OfficeManagerMainPage officeManagerMainPage = new OfficeManagerMainPage(stage);
            Scene scene = new Scene(officeManagerMainPage,850,500);
            stage.setScene(scene);
        });

        //Create Labels:

        Label customerNameLabel = new Label("New Commission Rate:  ");

        //Create Textfields:

        TextField customerNameField = new TextField();

        //Create Apply Discount Button:

        Button applyDiscountButton = new Button("Apply Discount");

        //Apply Button Logic:
        applyDiscountButton.setOnAction(event -> {

            // Taking the values from the input fields
            int customerName = Integer.parseInt(customerNameField.getText());

            // Updating the database with the new discount rates
            boolean success = DBMethods.modifyCommissionRate(customerName);

            if(success){
                System.out.println("Discount applied!");
            } else {
                System.out.println("Error!");
            }
        });


        //Setting Button Widths:

        int buttonWidth = 200;

        //Setting Widths for Input Fields:

        int inputFieldsWidth = 150;
        customerNameField.setPrefWidth(inputFieldsWidth);

        //Creating Horizontal Box for Customer Name Input:

        HBox customerNameBox = new HBox (10, customerNameLabel, customerNameField);
        customerNameBox.setAlignment(Pos.CENTER);


        //Creating Vertical Box for Inputs and Apply Discount Button:

        VBox applyDiscountBox = new VBox(20, customerNameBox,applyDiscountButton);
        applyDiscountBox.setAlignment(Pos.CENTER);

        //Moving around things:

        BorderPane.setMargin(pageTitle, new Insets(65));
        BorderPane.setAlignment(pageTitle, Pos.CENTER);
        BorderPane.setMargin(backToHomePage, new Insets(10));
        BorderPane.setMargin(applyDiscountBox, new Insets(-150,10,10,10));

        this.setTop(pageTitle);
        this.setCenter(applyDiscountBox);
        this.setBottom(backToHomePage);


    }

}