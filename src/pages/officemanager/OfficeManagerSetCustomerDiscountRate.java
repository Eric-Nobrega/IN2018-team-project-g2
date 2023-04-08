package pages.officemanager;

import dbfuncs.DBMethods;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import pages.general.LoginPage;

public class OfficeManagerSetCustomerDiscountRate extends BorderPane {

    public OfficeManagerSetCustomerDiscountRate(Stage stage){

        //Create Title Text:

        Label pageTitle = new Label("Set Customer Discount Rate");
        pageTitle.setFont(Font.font("Verdana",FontWeight.BOLD,36));
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

        Label customerNameLabel = new Label("Customer Name: ");
        Label fixedDiscountLabel = new Label("Fixed Discount: ");
        Label flexibleDiscountLabel = new Label("Flexible Discount: ");

        //Create Textfields:

        TextField customerNameField = new TextField();
        TextField fixedDiscountField = new TextField();
        TextField flexibleDiscountField = new TextField();

        //Create Apply Discount Button:

        Button applyDiscountButton = new Button("Apply Discount");

        //Apply Button Logic:

        applyDiscountButton.setOnAction(event -> {

            //Taking the values from the input fields

            String customerName = customerNameField.getText();
            String fixedDiscount = fixedDiscountField.getText();
            String flexibleDiscount = flexibleDiscountField.getText();

            //db stuff?

           //boolean success = DBMethods.applyDiscount(customerName, fixedDiscount, flexibleDiscount);

            //if(success){
              //  System.out.println("Discount applied!");
           // } else {
             //   System.out.println("Error!");
            //}
        });

        //Setting Button Widths:

        int buttonWidth = 200;

        //Setting Widths for Input Fields:

        int inputFieldsWidth = 150;
        customerNameField.setPrefWidth(inputFieldsWidth);
        fixedDiscountField.setPrefWidth(inputFieldsWidth);
        flexibleDiscountField.setPrefWidth(inputFieldsWidth);

        //Creating Horizontal Box for Customer Name Input:

        HBox customerNameBox = new HBox (10, customerNameLabel, customerNameField);
        customerNameBox.setAlignment(Pos.CENTER);

        //Creating Horizontal Box for Fixed Discount Input:

        HBox fixedDiscountBox = new HBox(10, fixedDiscountLabel,fixedDiscountField);
        fixedDiscountBox.setAlignment(Pos.CENTER);

        //Creating Horizontal Box for Flexible Discount Input:

        HBox flexibleDiscountBox = new HBox(10, flexibleDiscountLabel,flexibleDiscountField);
        flexibleDiscountBox.setAlignment(Pos.CENTER);

        //Creating Vertical Box for Inputs and Apply Discount Button:

        VBox applyDiscountBox = new VBox(20, customerNameBox,fixedDiscountBox,flexibleDiscountBox,applyDiscountButton);
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
