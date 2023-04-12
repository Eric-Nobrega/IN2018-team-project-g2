package pages.systemadministrator;

import dbfuncs.DBMethods;
import interfaces.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SystemAdministratorRemoveUser extends BorderPane {

    public SystemAdministratorRemoveUser(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("Remove User");
        pageTitle.setFont(Font.font("Karla", FontWeight.BOLD, 36));
        pageTitle.setUnderline(true);
        pageTitle.setTextAlignment(TextAlignment.CENTER);

        // Create Back To Home Navigation Button
        Button backToHomePage = new Button("Back To Home");

        // Page Routes
        backToHomePage.setOnAction(event -> {
            SystemAdministratorMainPage systemAdministratorMainPage = new SystemAdministratorMainPage(stage);
            Scene scene = new Scene(systemAdministratorMainPage, 850, 500);
            stage.setScene(scene);
        });

        // Create TableView to display users
        TableView<User> userTableView = new TableView<>();
        userTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Create TableColumn for each user attribute
        TableColumn<User, String> usernameCol = new TableColumn<>("Username");
        TableColumn<User, String> nameCol = new TableColumn<>("Name");
        TableColumn<User, String> roleCol = new TableColumn<>("Role");

        // Set cell value factory for each column
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));

        // Add columns to TableView
        userTableView.getColumns().add(usernameCol);
        userTableView.getColumns().add(nameCol);
        userTableView.getColumns().add(roleCol);

        // Fetch user data from database and populate TableView
        try {
            ResultSet rs = DBMethods.getAllUsers();
            while (rs.next()) {
                String username = rs.getString("UserName");
                String name = rs.getString("Name");
                String role = rs.getString("Role");
                User user = new User(username, name, role);
                userTableView.getItems().add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set the same width for all buttons
        int buttonWidth = 200;

        Button deleteSelectedButton = new Button("Delete Selected");
        deleteSelectedButton.setPrefWidth(buttonWidth);
        deleteSelectedButton.setOnAction(event -> {
            User selectedUser = userTableView.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
                DBMethods.deleteUser(selectedUser.getUsername());
                userTableView.getItems().remove(selectedUser);
            }
        });

        HBox bottomRightHBox = new HBox(10, deleteSelectedButton);
        bottomRightHBox.setAlignment(Pos.CENTER_RIGHT);

        VBox bottomBox = new VBox(10, bottomRightHBox);
        bottomBox.setAlignment(Pos.CENTER_RIGHT);

        BorderPane.setMargin(pageTitle, new Insets(65));
        BorderPane.setAlignment(pageTitle, Pos.CENTER);
        BorderPane.setMargin(backToHomePage, new Insets(10));
        BorderPane.setMargin(userTableView, new Insets(10));
        BorderPane.setMargin(bottomBox, new Insets(10));
        BorderPane.setAlignment(bottomBox, Pos.CENTER_RIGHT);

        this.setTop(pageTitle);
        this.setCenter(userTableView);
        this.setBottom(backToHomePage);
        this.setRight(bottomBox);
    }
}