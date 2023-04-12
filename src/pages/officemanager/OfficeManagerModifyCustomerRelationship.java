package pages.officemanager;

import dbfuncs.DBMethods;
import interfaces.Customer;
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

public class OfficeManagerModifyCustomerRelationship extends BorderPane {

    private final TextField fullNameField = new TextField();
    private final TextField relationshipField = new TextField();


    public OfficeManagerModifyCustomerRelationship(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("Modify Customer Relationship");
        pageTitle.setFont(Font.font("Karla", FontWeight.BOLD, 36));
        pageTitle.setUnderline(true);
        pageTitle.setTextAlignment(TextAlignment.CENTER);

        // Create Back To Home Navigation Button
        Button backToHomePage = new Button("Back To Home");

        // Page Routes
        backToHomePage.setOnAction(event -> {
            OfficeManagerMainPage systemAdministratorMainPage = new OfficeManagerMainPage(stage);
            Scene scene = new Scene(systemAdministratorMainPage, 850, 500);
            stage.setScene(scene);
        });

        // Create Search Button
        Button searchButton = new Button("Search");
        searchButton.setPrefWidth(200);

        searchButton.setOnAction(event -> {
            String customerName = fullNameField.getText();
            if (!(fullNameField == null)) {
                Customer customer = DBMethods.getCustomerByName(customerName);
                if (customer != null) {
                    fullNameField.setText(customer.getCustomerName());
                    relationshipField.setText(customer.getRelationship());
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
            String fullNameInput = fullNameField.getText();
            String relationshipInput = relationshipField.getText();

            // IF the fields are all filled out
            if (!(fullNameInput == null) && !(relationshipInput == null)) {
                // call the database update method
                DBMethods.updateCustomerRelationship(fullNameInput, relationshipInput);
                // redirect the user to the sys admin homepage
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
                new Label("Full Name"), fullNameField,
                new Label("Relationship"), relationshipField,
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
