package pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TravelAdvisorHomePage extends BorderPane {

    public TravelAdvisorHomePage(Stage stage) {
        Label welcomeLabel = new Label("Travel Advisor Homepage");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        Button logoutButton = new Button("Logout");
        Button button1 = new Button("Create Ticket");
        Button button2 = new Button("Create Sale");
        Button button3 = new Button("Blanks");
        Button button4 = new Button("Void Blanks");
        Button button5 = new Button("Generate Individual Report");

        // set create tickets page route
        button1.setOnAction(event -> {
            CreateTicketPage createTicketPage = new CreateTicketPage(stage);
            Scene scene = new Scene(createTicketPage, 400, 400);
            stage.setScene(scene);
        });

        // set logout button event handler
        logoutButton.setOnAction(event -> {
            // log user out and redirect to login page
            LoginPage loginPage = new LoginPage();
            loginPage.setStage((Stage) getScene().getWindow());
            loginPage.createLoginPage();
        });

        VBox centerBox = new VBox(10, button1, button2, button3, button4, button5);
        centerBox.setAlignment(Pos.CENTER);

        BorderPane.setMargin(welcomeLabel, new Insets(35));
        BorderPane.setMargin(logoutButton, new Insets(10));
        BorderPane.setMargin(centerBox, new Insets(50, 10, 10, 10));

        this.setTop(welcomeLabel);
        this.setCenter(centerBox);
        this.setBottom(logoutButton);
    }
}
