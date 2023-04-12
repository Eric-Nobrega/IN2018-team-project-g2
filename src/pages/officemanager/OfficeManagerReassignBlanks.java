package pages.officemanager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import static dbfuncs.DBMethods.*;

public class OfficeManagerReassignBlanks extends BorderPane {

    public OfficeManagerReassignBlanks(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("Reassign Blanks");
        pageTitle.setFont(Font.font("Karla", FontWeight.BOLD, 36));
        pageTitle.setUnderline(true);
        pageTitle.setTextAlignment(TextAlignment.CENTER);

        int buttonWidth = 200;


        // Create Back To Home Navigation Button
        Button backToHomePage = new Button("Back To Home");
        backToHomePage.setPrefWidth(buttonWidth);

        // Back To Home Button Logic
        backToHomePage.setOnAction(event -> {
            OfficeManagerMainPage officeManagerMainPage = new OfficeManagerMainPage(stage);
            Scene scene = new Scene(officeManagerMainPage, 850, 500);
            stage.setScene(scene);
        });

        // Create Blank Type Selection
        Label blankTypeLabel = new Label("Select Blank Type:");
        ComboBox<String> blankTypeComboBox = new ComboBox<>();

        // Create Current Advisor Selection
        Label currentAdvisorLabel = new Label("Select Current Advisor:");
        ComboBox<String> currentAdvisorComboBox = new ComboBox<>();

        // Create New Advisor Selection
        Label newAdvisorLabel = new Label("Select New Advisor:");
        ComboBox<String> newAdvisorComboBox = new ComboBox<>();

        // Create Amount TextField
        Label amountLabel = new Label("Amount:");
        TextField amountTextField = new TextField();

        // Create Confirm Button
        Button confirmButton = new Button("Confirm");
        confirmButton.setPrefWidth(buttonWidth);

        // call the getAllTravelAdvisors method to retrieve the ResultSet
        ResultSet rs = getAllTravelAdvisors();

        try {
            // iterate over the ResultSet and add each FullName value to the ComboBox
            while (rs.next()) {
                String fullName = rs.getString("AdvisorFirstName") + " " + rs.getString("AdvisorLastName");
                currentAdvisorComboBox.getItems().add(fullName);
                newAdvisorComboBox.getItems().add(fullName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // call the getAllTravelAgents method to retrieve the ResultSet
        ResultSet rs2 = getAllBlanksCodes();

        try {
            // iterate over the ResultSet and add each FullName value to the ComboBox
            while (rs2.next()) {
                String blankCode = rs2.getString("BlankCode");
                blankTypeComboBox.getItems().add(blankCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Confirm Button Logic
        confirmButton.setOnAction(event -> {
            String selectedBlankType = blankTypeComboBox.getValue();
            String selectedCurrentAdvisor = currentAdvisorComboBox.getValue();
            String selectedNewAdvisor = newAdvisorComboBox.getValue();
            int amount = Integer.parseInt(amountTextField.getText());
            try {
                reassignBlanks(selectedBlankType, selectedCurrentAdvisor, selectedNewAdvisor, amount);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        // Create VBox for Blank Type, Current Advisor and New Advisor Selection
        VBox selectionBox = new VBox();
        selectionBox.setAlignment(Pos.CENTER);
        selectionBox.setSpacing(20);
        selectionBox.setPadding(new Insets(50, 0, 0, 0));
        selectionBox.getChildren().addAll(blankTypeLabel, blankTypeComboBox, currentAdvisorLabel, currentAdvisorComboBox, newAdvisorLabel, newAdvisorComboBox);
        // Create VBox for Amount TextField and Confirm Button
        VBox actionBox = new VBox();
        actionBox.setAlignment(Pos.CENTER);
        actionBox.setSpacing(20);
        actionBox.getChildren().addAll(amountLabel, amountTextField, confirmButton);

        // Add Title, Selection VBox, Action VBox and Back To Home Button to the BorderPane
        setTop(pageTitle);
        setCenter(selectionBox);
        setRight(actionBox);
        setBottom(backToHomePage);
    }
}