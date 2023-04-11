package pages.systemadministrator;

import dbfuncs.DBMethods;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class SystemAdministratorAddBlank extends BorderPane {

    public SystemAdministratorAddBlank(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("Add Blanks");
        pageTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 36));
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

        // Create Labels
        Label blankTypeLabel = new Label("Blank Type: ");
        Label amountReceivedLabel = new Label("Amount Received: ");
        Label dateReceivedLabel = new Label("Date Received: ");

        // Create TextFields
        TextField blankTypeField = new TextField();
        TextField amountReceivedField = new TextField();
        TextField dateReceivedField = new TextField();

        // Create Add Blanks Button
        Button addBlanksButton = new Button("Add Blanks");

        // Add Blanks Button Logic
        addBlanksButton.setOnAction(event -> {
            String blankType = blankTypeField.getText();
            int amountReceived = Integer.parseInt(amountReceivedField.getText());
            String dateReceived = dateReceivedField.getText();

            // Add the new blanks to the database
            boolean success = DBMethods.addBlanks(blankType, dateReceived, amountReceived);
            if (success) {
                System.out.println("Blanks added!");
            } else {
                System.out.println("Error!");
            }
        });

        // Create Horizontal Box for Blank Type Input
        HBox blankTypeBox = new HBox(10, blankTypeLabel, blankTypeField);
        blankTypeBox.setAlignment(Pos.CENTER);

        // Create Horizontal Box for Amount Received Input
        HBox amountReceivedBox = new HBox(10, amountReceivedLabel, amountReceivedField);
        amountReceivedBox.setAlignment(Pos.CENTER);

        // Create Horizontal Box for Date Received Input
        HBox dateReceivedBox = new HBox(10, dateReceivedLabel, dateReceivedField);
        dateReceivedBox.setAlignment(Pos.CENTER);

        // Create Vertical Box for Inputs and Add Blanks Button
        VBox addBlanksBox = new VBox(20, blankTypeBox, amountReceivedBox, dateReceivedBox, addBlanksButton);
        addBlanksBox.setAlignment(Pos.CENTER);

        BorderPane.setMargin(pageTitle, new Insets(65));
        BorderPane.setAlignment(pageTitle, Pos.CENTER);
        BorderPane.setMargin(backToHomePage, new Insets(10));
        BorderPane.setMargin(addBlanksBox, new Insets(-150, 10, 10, 10));

        this.setTop(pageTitle);
        this.setCenter(addBlanksBox);
        this.setBottom(backToHomePage);
    }
}
