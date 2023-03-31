package pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerHomePage extends BorderPane {

    public CustomerHomePage(Stage stage) {
        Label welcomeLabel = new Label("Customer Homepage");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        Button logoutButton = new Button("Logout");
        Button button1 = new Button("View Flights");
        Button button2 = new Button("Account Information");

        // set logout button event handler
        logoutButton.setOnAction(event -> {
            // log user out and redirect to login page
            LoginPage loginPage = new LoginPage();
            loginPage.setStage((Stage) getScene().getWindow());
            loginPage.createLoginPage();
        });

        VBox centerBox = new VBox(10, button1, button2);
        centerBox.setAlignment(Pos.CENTER);

        BorderPane.setMargin(welcomeLabel, new Insets(45));
        BorderPane.setMargin(logoutButton, new Insets(10));
        BorderPane.setMargin(centerBox, new Insets(50, 10, 10, 10));

        this.setTop(welcomeLabel);
        this.setCenter(centerBox);
        this.setBottom(logoutButton);
    }
}
