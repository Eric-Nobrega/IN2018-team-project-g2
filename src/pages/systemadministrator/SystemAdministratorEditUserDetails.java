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

public class SystemAdministratorEditUserDetails extends BorderPane {

    private final TextField usernameField = new TextField();
    private final TextField nameField = new TextField();
    private final TextField roleField = new TextField();


        public SystemAdministratorEditUserDetails(Stage stage) {
            // Create Title Text
            Label pageTitle = new Label("Edit User Details");
            pageTitle.setFont(Font.font("Karla", FontWeight.BOLD, 36));
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
                String usernameInput = usernameField.getText();
                if (!usernameInput.isBlank()) {
                    User user = DBMethods.getUserByUsername(usernameInput);
                    if (user != null) {
                        nameField.setText(user.getName());
                        roleField.setText(user.getRole());
                    } else {
                        // display error message
                        Alert alert = new Alert(Alert.AlertType.ERROR, "User not found.");
                        alert.showAndWait();
                    }
                } else {
                    // display error message
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please enter a username.");
                    alert.showAndWait();
                }
            });

            // Create Update Button
            Button updateButton = new Button("Update");
            updateButton.setPrefWidth(200);

            // Update Button Logic
            updateButton.setOnAction(event -> {
                String usernameInput = usernameField.getText();
                String nameInput = nameField.getText();
                String roleInput = roleField.getText();
                if (!(usernameInput == null) && !(nameInput == null) && !(roleInput == null)) {
                    DBMethods.updateUser(usernameInput, nameInput, roleInput);
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
            VBox inputFieldsVBox = new VBox(10);
            inputFieldsVBox.setAlignment(Pos.CENTER);
            inputFieldsVBox.getChildren().addAll(
                    new Label("Username"), usernameField,
                    new Label("Name"), nameField,
                    new Label("Role"), roleField,
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
