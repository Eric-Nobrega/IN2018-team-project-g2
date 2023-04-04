package pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OfficeManagerHomePage extends BorderPane {

    public OfficeManagerHomePage(Stage stage) {
        Label welcomeLabel = new Label("Office Manager Homepage");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        Button logoutButton = new Button("Logout");
        Button button1 = new Button("Generate Report");
        Button button2 = new Button("Assign Blanks");
        Button button3 = new Button("Re-Assign Blanks");

        // set generate report route
        button1.setOnAction(event -> {
            GenerateReportPage generateReportPage = new GenerateReportPage(stage);
            Scene scene = new Scene(generateReportPage, 400, 400);
            stage.setScene(scene);
        });

        // set assign blanks route
        button2.setOnAction(event -> {
            AssignBlanksPage assignBlanksPage = new AssignBlanksPage(stage);
            Scene scene = new Scene(assignBlanksPage, 400, 400);
            stage.setScene(scene);
        });

        // set re assign blanks route
        button3.setOnAction(event -> {
            BlanksReassignmentPage blanksReassignmentPage = new BlanksReassignmentPage(stage);
            Scene scene = new Scene(blanksReassignmentPage, 400, 400);
            stage.setScene(scene);
        });

        // set logout button event handler
        logoutButton.setOnAction(event -> {
            // log user out and redirect to login page
            LoginPage loginPage = new LoginPage();
            loginPage.setStage((Stage) getScene().getWindow());
            loginPage.createLoginPage();
        });

        VBox centerBox = new VBox(10, button1, button2, button3);
        centerBox.setAlignment(Pos.CENTER);

        BorderPane.setMargin(welcomeLabel, new Insets(45));
        BorderPane.setMargin(logoutButton, new Insets(10));
        BorderPane.setMargin(centerBox, new Insets(50, 10, 10, 10));

        this.setTop(welcomeLabel);
        this.setCenter(centerBox);
        this.setBottom(logoutButton);
    }
}
