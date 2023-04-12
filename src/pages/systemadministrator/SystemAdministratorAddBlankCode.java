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

import java.sql.SQLException;

public class SystemAdministratorAddBlankCode extends BorderPane {

    public SystemAdministratorAddBlankCode(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("Add New Blank Code");
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
        Label blankTypeLabel = new Label("New Blank Code: ");

        // Create TextFields
        TextField blankTypeField = new TextField();

        // Create Add Blanks Button
        Button addBlanksButton = new Button("Add Code");

        // Add Blanks Button Logic
        addBlanksButton.setOnAction(event -> {
            String blankType = blankTypeField.getText();

            // Add the new blanks to the database
            boolean success = false;
            try {
                DBMethods.addBlankCode(blankType);
                System.out.println("SUCCESS");
            } catch (SQLException e) {
                System.out.println("FAILED");
                throw new RuntimeException(e);
            }
        });

        // Create Horizontal Box for Blank Type Input
        HBox blankTypeBox = new HBox(10, blankTypeLabel, blankTypeField);
        blankTypeBox.setAlignment(Pos.CENTER);

        // Create Vertical Box for Inputs and Add Blanks Button
        VBox addBlanksBox = new VBox(20, blankTypeBox, addBlanksButton);
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
