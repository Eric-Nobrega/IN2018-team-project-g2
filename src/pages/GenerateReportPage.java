package pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GenerateReportPage extends BorderPane {

    public GenerateReportPage(Stage stage) {
        Label welcomeLabel = new Label("Generate Reports");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button button1 = new Button("Generate Individual Sales Report");
        Button button2 = new Button("Generate Domestic Sales Report");
        Button button3 = new Button("Generate Interline Sales Report");
        Button button4 = new Button("Generate Stock Turnover Report");

        // set assign blanks route
        button2.setOnAction(event -> {
            AssignBlanksPage assignBlanksPage = new AssignBlanksPage(stage);
            Scene scene = new Scene(assignBlanksPage, 400, 400);
            stage.setScene(scene);
        });


        VBox centerBox = new VBox(10, button1, button2, button3, button4);
        centerBox.setAlignment(Pos.CENTER);

        BorderPane.setMargin(welcomeLabel, new Insets(45));
        BorderPane.setMargin(centerBox, new Insets(50, 10, 10, 10));


        Button returnToHome = new Button("Return To Home");

        // Add the HBox to the bottom of the BorderPane
        this.setBottom(returnToHome);

        this.setTop(welcomeLabel);
        this.setCenter(centerBox);
    }
}
