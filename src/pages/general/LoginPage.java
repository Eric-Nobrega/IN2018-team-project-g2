package pages.general;

import dbfuncs.DBMethods;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pages.officemanager.OfficeManagerMainPage;
import pages.systemadministrator.SystemAdministratorMainPage;
import pages.traveladvisor.TravelAdvisorMainPage;

import java.util.Objects;

public class LoginPage {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void createLoginPage() {

        // Create Title Text
        Label airViaLabel = new Label("AirVia LTD Demo Software");
        airViaLabel.setFont(Font.font("Karla", FontWeight.BOLD, 36));

        // Create Input Field Labels
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        // Create Input Fields
        TextField usernameTextField = new TextField();
        PasswordField passwordTextField = new PasswordField();

        // Create Login Button
        Button loginButton = new Button("Login");

        // Authentication Logic
        loginButton.setOnAction(e -> {
            // Check The Username and Password Combination Exists
            if(DBMethods.authenticate(usernameTextField.getText(), passwordTextField.getText())){
                // Redirect User Based on Role Type (Office Manager, System Administrator, Travel Advisor)
                if(Objects.equals(DBMethods.getRole(usernameTextField.getText()), "Office Manager")){
                    OfficeManagerMainPage officeManagerMainPage = new OfficeManagerMainPage(stage);
                    Scene scene = new Scene(officeManagerMainPage, 850, 500);
                    stage.setScene(scene);
                }else if (Objects.equals(DBMethods.getRole(usernameTextField.getText()), "System Administrator")) {
                    SystemAdministratorMainPage systemAdministratorMainPage = new SystemAdministratorMainPage(stage);
                    Scene scene = new Scene(systemAdministratorMainPage, 850, 500);
                    stage.setScene(scene);
                }else if (Objects.equals(DBMethods.getRole(usernameTextField.getText()), "Travel Advisor")) {
                    TravelAdvisorMainPage travelAdvisorMainPage = new TravelAdvisorMainPage(stage);
                    Scene scene = new Scene(travelAdvisorMainPage, 850, 500);
                    stage.setScene(scene);
                }
            }
        });

        // Assemble Page
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(30);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.add(airViaLabel, 0, 0, 2, 1);
        gridPane.add(usernameLabel, 0, 2);
        gridPane.add(usernameTextField, 1, 2);
        gridPane.add(passwordLabel, 0, 3);
        gridPane.add(passwordTextField, 1, 3);
        gridPane.add(loginButton, 1, 4);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridPane);

        Scene scene = new Scene(borderPane, 850, 500);

        stage.setScene(scene);
        stage.show();
    }
}
