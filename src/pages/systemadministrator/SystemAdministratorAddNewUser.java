package pages.systemadministrator;

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

public class SystemAdministratorAddNewUser extends BorderPane {

    public SystemAdministratorAddNewUser(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("Add A New User");
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

        // Create Labels
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        Label roleLabel = new Label("Role:");
        Label nameLabel = new Label("Name:");

        // Create TextFields
        TextField usernameField = new TextField();
        TextField passwordField = new TextField();
        TextField roleField = new TextField();
        TextField nameField = new TextField();

        // Create Confirm New Addition Button
        Button confirmButton = new Button("Confirm New Addition");

        // Confirm Button Logic
        confirmButton.setOnAction(event -> {
            // get values from input fields
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = roleField.getText();
            String name = nameField.getText();

            // call addUser method to insert new user
            boolean success = DBMethods.addUser(username, password, role, name);

            if (success) {
                // show success message
                System.out.println("New user added successfully!");
            } else {
                // show error message
                System.out.println("Error adding new user!");
            }
        });


        // Set the same width for all buttons
        int buttonWidth = 200;

        // Set the same width for all input fields
        int inputFieldWidth = 300;
        usernameField.setPrefWidth(inputFieldWidth);
        passwordField.setPrefWidth(inputFieldWidth);
        roleField.setPrefWidth(inputFieldWidth);

        // Create HBox for Username input
        HBox usernameBox = new HBox(10, usernameLabel, usernameField);
        usernameBox.setAlignment(Pos.CENTER);

        // Create HBox for Password input
        HBox passwordBox = new HBox(10, passwordLabel, passwordField);
        passwordBox.setAlignment(Pos.CENTER);

        // Create HBox for Role input
        HBox roleBox = new HBox(10, roleLabel, roleField);
        roleBox.setAlignment(Pos.CENTER);

        // Create HBox for Role input
        HBox nameBox = new HBox(10, nameLabel, nameField);
        roleBox.setAlignment(Pos.CENTER);

        // Create VBox for input fields and Confirm New Addition button
        VBox centerBox = new VBox(20, usernameBox, passwordBox, roleBox, nameBox, confirmButton);
        centerBox.setAlignment(Pos.CENTER);

        BorderPane.setMargin(pageTitle, new Insets(65));
        BorderPane.setAlignment(pageTitle, Pos.CENTER);
        BorderPane.setMargin(backToHomePage, new Insets(10));
        BorderPane.setMargin(centerBox, new Insets(-150, 10, 10, 10));

        this.setTop(pageTitle);
        this.setCenter(centerBox);
        this.setBottom(backToHomePage);
    }
}
