package pages;

import dbfuncs.DBQuery;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Objects;

public class LoginPage {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void createLoginPage() {

        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        TextField usernameTextField = new TextField();
        PasswordField passwordTextField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            // Check if user exists
            if(DBQuery.authenticate(usernameTextField.getText(), passwordTextField.getText())){
                // IF user DOES exist, redirect them to the correct page
                if(Objects.equals(DBQuery.getRole(usernameTextField.getText()), "Office Manager")){
                    OfficeManagerHomePage offManagerHomePage = new OfficeManagerHomePage(stage);
                    Scene scene = new Scene(offManagerHomePage, 400, 400);
                    stage.setScene(scene);
                }else if (Objects.equals(DBQuery.getRole(usernameTextField.getText()), "System Administrator")) {
                    SystemAdministratorHomePage sysAdminHomePage = new SystemAdministratorHomePage(stage);
                    Scene scene = new Scene(sysAdminHomePage, 400, 400);
                    stage.setScene(scene);
                }else if (Objects.equals(DBQuery.getRole(usernameTextField.getText()), "Customer")) {
                    CustomerHomePage customerHomePage = new CustomerHomePage(stage);
                    Scene scene = new Scene(customerHomePage, 400, 400);
                    stage.setScene(scene);
                }else if (Objects.equals(DBQuery.getRole(usernameTextField.getText()), "Travel Advisor")) {
                    TravelAdvisorHomePage travelAdvisorHomePage = new TravelAdvisorHomePage(stage);
                    Scene scene = new Scene(travelAdvisorHomePage, 400, 400);
                    stage.setScene(scene);
                }
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.add(usernameLabel, 0, 1);
        gridPane.add(usernameTextField, 1, 1);
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(passwordTextField, 1, 2);
        gridPane.add(loginButton, 1, 3);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridPane);

        Scene scene = new Scene(borderPane, 400, 400);

        stage.setScene(scene);
        stage.show();
    }
}
