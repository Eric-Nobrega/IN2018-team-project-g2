package pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SystemAdministratorHomePage extends BorderPane {

    public SystemAdministratorHomePage(Stage stage) {
        Label welcomeLabel = new Label("System Admin Homepage");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        Button logoutButton = new Button("Logout");
        Button button1 = new Button("Agency Details");
        Button button2 = new Button("Database");
        Button button3 = new Button("Blanks");
        Button button4 = new Button("Manage Staff");

        // set agency details page route
        button1.setOnAction(event -> {
            AgencyDetailsPage agencyDetailsPage = new AgencyDetailsPage(stage);
            Scene scene = new Scene(agencyDetailsPage, 400, 400);
            stage.setScene(scene);
        });

        // set logout button event handler
        logoutButton.setOnAction(event -> {
            // log user out and redirect to login page
            LoginPage loginPage = new LoginPage();
            loginPage.setStage((Stage) getScene().getWindow());
            loginPage.createLoginPage();
        });

        VBox centerBox = new VBox(10, button1, button2, button3, button4);
        centerBox.setAlignment(Pos.CENTER);

        BorderPane.setMargin(welcomeLabel, new Insets(50));
        BorderPane.setMargin(logoutButton, new Insets(10));
        BorderPane.setMargin(centerBox, new Insets(50, 10, 10, 10));

        this.setTop(welcomeLabel);
        this.setCenter(centerBox);
        this.setBottom(logoutButton);
    }
}
