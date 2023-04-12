package pages.systemadministrator;

import dbfuncs.DBMethods;
import interfaces.User;
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

public class SystemAdministratorEditStaffUser extends BorderPane {

    private final TextField firstNameField = new TextField();
    private final TextField lastNameField = new TextField();
    private final TextField addressField = new TextField();
    private final TextField phoneNumberField = new TextField();
    private final TextField emailField = new TextField();
    private final TextField travelAgentIdField = new TextField();

    public SystemAdministratorEditStaffUser(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("Edit Staff User");
        pageTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 36));
        pageTitle.setUnderline(true);
        pageTitle.setTextAlignment(TextAlignment.CENTER);

        // Create Back To Home Navigation Button
        Button backToHomePage = new Button("Back To Home");

        // Back To Home Button Logic
        backToHomePage.setOnAction(event -> {
            SystemAdministratorMainPage systemAdministratorMainPage = new SystemAdministratorMainPage(stage);
            Scene scene = new Scene(systemAdministratorMainPage, 850, 500);
            stage.setScene(scene);
        });

        // Create Search Button
        Button searchButton = new Button("Search");
        searchButton.setPrefWidth(200);

        // Search Button Logic
        searchButton.setOnAction(event -> {
            String emailInput = emailField.getText();

        });

        // Create Update Button
        Button updateButton = new Button("Update");
        updateButton.setPrefWidth(200);

        // Update Button Logic
        updateButton.setOnAction(event -> {
            String emailInput = emailField.getText();
            String firstNameInput = firstNameField.getText();
            String lastNameInput = lastNameField.getText();
            String addressInput = addressField.getText();
            String phoneNumberInput = phoneNumberField.getText();
            String travelAgentIdInput = travelAgentIdField.getText();
            if (!(emailInput == null) && !(firstNameInput == null) && !(lastNameInput == null) &&
                    !(addressInput == null) && !(phoneNumberInput == null) && !(travelAgentIdInput == null)) {
            //    DBMethods.updateUser(emailInput, firstNameInput, lastNameInput, addressInput,
                   //     phoneNumberInput, travelAgentIdInput);
                SystemAdministratorMainPage systemAdministratorMainPage = new SystemAdministratorMainPage(stage);
                Scene scene = new Scene(systemAdministratorMainPage, 850, 500);
                stage.setScene(scene);
            } else {
                // display error message
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields.");
                alert.showAndWait();
            }
        });

        // Create VBox to hold input fields and Update button
        VBox inputFields = new VBox();
        inputFields.setSpacing(10);
        inputFields.setAlignment(Pos.CENTER);
        inputFields.setPadding(new Insets(20, 20, 20, 20));
        inputFields.getChildren().addAll(
                new Label("Email:"),
                emailField,
                new Label("First Name:"),
                firstNameField,
                new Label("Last Name:"),
                lastNameField,
                new Label("Address:"),
                addressField,
                new Label("Phone Number:"),
                phoneNumberField,
                new Label("Travel Agent ID:"),
                travelAgentIdField,
                updateButton
        );

        // Create VBox to hold search button and back to home button
        VBox buttons = new VBox();
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(20, 20, 20, 20));
        buttons.getChildren().addAll(
                searchButton,
                backToHomePage
        );

        // Set top, center, and bottom nodes of BorderPane
        setTop(pageTitle);
        setCenter(inputFields);
        setBottom(buttons);
    }
}