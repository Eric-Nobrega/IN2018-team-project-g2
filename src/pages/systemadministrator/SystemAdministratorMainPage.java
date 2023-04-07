package pages.systemadministrator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import pages.general.LoginPage;

public class SystemAdministratorMainPage extends BorderPane {

    public SystemAdministratorMainPage(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("System Administrator Homepage");
        pageTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 36));
        pageTitle.setUnderline(true);
        pageTitle.setTextAlignment(TextAlignment.CENTER);

        // Create Functionality Buttons
        Button viewBlankStockButton = new Button("View Total Blank Stock");
        Button addBlankButton = new Button("Add Blank");
        Button removeBlankButton = new Button("Remove Blank");
        Button addBlankCodeButton = new Button("Add Blank Code");
        Button removeBlankCodeButton = new Button("Remove Blank Code");
        Button addUserButton = new Button("Add New User");
        Button removeUserButton = new Button("Remove User");
        Button addStaffMemberButton = new Button("Add Staff Member");
        Button removeStaffMemberButton = new Button("Remove Staff Member");
        Button editUserDetailsButton = new Button("Edit User Details");
        Button editStaffDetailsButton = new Button("Edit Staff Details");
        Button editTravelAgentDetailsButton = new Button("Edit Travel Agent Details");

        // Create Logout Button
        Button logoutButton = new Button("Logout");

        // Logout Button Logic
        logoutButton.setOnAction(event -> {
            LoginPage loginPage = new LoginPage();
            loginPage.setStage((Stage) getScene().getWindow());
            loginPage.createLoginPage();
        });

        // Functionality Button Page Routes
        // Add User
        addUserButton.setOnAction(event -> {
            SystemAdministratorAddNewUser addNewUserPage = new SystemAdministratorAddNewUser(stage);
            Scene scene = new Scene(addNewUserPage, 850, 500);
            stage.setScene(scene);
        });

        // Remove User
        removeUserButton.setOnAction(event -> {
            SystemAdministratorRemoveUser removeUserPage = new SystemAdministratorRemoveUser(stage);
            Scene scene = new Scene(removeUserPage, 850, 500);
            stage.setScene(scene);
        });

        // Edit User
        editUserDetailsButton.setOnAction(event -> {
            SystemAdministratorEditUserDetails editUserDetails = new SystemAdministratorEditUserDetails(stage);
            Scene scene = new Scene(editUserDetails, 850, 500);
            stage.setScene(scene);
        });

        // Edit Travel Agent
        editTravelAgentDetailsButton.setOnAction(event -> {
            SystemAdministratorEditTravelAgentDetails editTravelAgentDetails = new SystemAdministratorEditTravelAgentDetails(stage);
            Scene scene = new Scene(editTravelAgentDetails, 850, 500);
            stage.setScene(scene);
        });

        // Set the same width for all buttons
        int buttonWidth = 200;
        viewBlankStockButton.setPrefWidth(buttonWidth);
        addBlankButton.setPrefWidth(buttonWidth);
        removeBlankButton.setPrefWidth(buttonWidth);
        addBlankCodeButton.setPrefWidth(buttonWidth);
        removeBlankCodeButton.setPrefWidth(buttonWidth);
        addUserButton.setPrefWidth(buttonWidth);
        removeUserButton.setPrefWidth(buttonWidth);
        addStaffMemberButton.setPrefWidth(buttonWidth);
        removeStaffMemberButton.setPrefWidth(buttonWidth);
        editUserDetailsButton.setPrefWidth(buttonWidth);
        editStaffDetailsButton.setPrefWidth(buttonWidth);
        editTravelAgentDetailsButton.setPrefWidth(buttonWidth);

        // Create two containers to hold the buttons
        VBox leftBox = new VBox(20, viewBlankStockButton, addBlankButton, removeBlankButton, addBlankCodeButton, removeBlankCodeButton, addUserButton);
        leftBox.setAlignment(Pos.CENTER);
        VBox rightBox = new VBox(20, removeUserButton, addStaffMemberButton, removeStaffMemberButton, editUserDetailsButton, editStaffDetailsButton, editTravelAgentDetailsButton);
        rightBox.setAlignment(Pos.CENTER);

        // Create a container to hold the two containers
        HBox centerBox = new HBox(20, leftBox, rightBox);
        centerBox.setAlignment(Pos.CENTER);

        BorderPane.setMargin(pageTitle, new Insets(65));
        BorderPane.setAlignment(pageTitle, Pos.CENTER);
        BorderPane.setMargin(logoutButton, new Insets(10));
        BorderPane.setMargin(centerBox, new Insets(-110, 10, 10, 10));

        this.setTop(pageTitle);
        this.setCenter(centerBox);
        this.setBottom(logoutButton);
    }
}