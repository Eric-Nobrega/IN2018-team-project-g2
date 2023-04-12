package pages.systemadministrator;

import dbfuncs.DBMethods;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

import static dbfuncs.DBMethods.getAllBlanksCodes;
import static dbfuncs.DBMethods.getAllTravelAgents;

public class SystemAdministratorRemoveBlank extends BorderPane {

    public SystemAdministratorRemoveBlank(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("Remove Blanks");
        pageTitle.setFont(Font.font("Karla", FontWeight.BOLD, 36));
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
        Label amountReceivedLabel = new Label("Amount To Remove: ");
        Label travelAgentsLabel = new Label("Travel Agent: ");

        // Create TextFields
        ComboBox<String> blankTypeField = new ComboBox<>();
        TextField amountReceivedField = new TextField();
        DatePicker dateReceivedField = new DatePicker();
        ComboBox<String> travelAgents = new ComboBox<>();

        // call the getAllTravelAgents method to retrieve the ResultSet
        ResultSet rs = getAllTravelAgents();

        try {
            // iterate over the ResultSet and add each FullName value to the ComboBox
            while (rs.next()) {
                String fullName = rs.getString("FullName");
                travelAgents.getItems().add(fullName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet rs2 = getAllBlanksCodes();

        try {
            // iterate over the ResultSet and add each FullName value to the ComboBox
            while (rs2.next()) {
                String blankCode = rs2.getString("BlankCode");
                blankTypeField.getItems().add(blankCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Create Add Blanks Button
        Button removeBlanksButton = new Button("Remove Blanks");

        // Add Blanks Button Logic
        removeBlanksButton.setOnAction(event -> {
            String blankType = blankTypeField.getValue();
            int amountToRemove = Integer.parseInt(amountReceivedField.getText());
            int travelAgentID = DBMethods.getTravelAgentIDByName(travelAgents.getValue());

            // Add the new blanks to the database
            boolean success = DBMethods.removeBlanks(blankType, amountToRemove, travelAgentID);
            if (success) {
                System.out.println("Blanks Removed!");
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
        HBox travelAgentBox = new HBox(10, travelAgentsLabel, travelAgents);
        travelAgentBox.setAlignment(Pos.CENTER);

        // Create Vertical Box for Inputs and Add Blanks Button
        VBox addBlanksBox = new VBox(20, blankTypeBox, amountReceivedBox, travelAgentBox, removeBlanksButton);
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
