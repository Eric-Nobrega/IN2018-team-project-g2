package pages.officemanager;

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
import pages.systemadministrator.SystemAdministratorMainPage;

import java.sql.ResultSet;
import java.sql.SQLException;

import static dbfuncs.DBMethods.*;

public class OfficeManagerAssignBlank extends BorderPane {

    public OfficeManagerAssignBlank(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("Office Manager Homepage");
        pageTitle.setFont(Font.font("Karla", FontWeight.BOLD, 36));
        pageTitle.setUnderline(true);
        pageTitle.setTextAlignment(TextAlignment.CENTER);

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

        // Create Travel Advisor Selection
        Label travelAdvisorLabel = new Label("Select Travel Advisor:");
        ComboBox<String> travelAdvisorComboBox = new ComboBox<>();

        // call the getAllTravelAgents method to retrieve the ResultSet
        ResultSet rs = getAllTravelAdvisors();

        try {
            // iterate over the ResultSet and add each FullName value to the ComboBox
            while (rs.next()) {
                String fullName = rs.getString("AdvisorFirstName") + " " + rs.getString("AdvisorLastName");
                travelAdvisorComboBox.getItems().add(fullName);
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

        // Create Confirm Button
        Button confirmButton = new Button("Confirm");
        confirmButton.setPrefWidth(buttonWidth);

        // Create Amount TextField
        Label amountLabel = new Label("Amount:");
        TextField amountTextField = new TextField();

        // Confirm Button Logic
        confirmButton.setOnAction(event -> {
            String selectedBlankType = blankTypeComboBox.getValue();
            String selectedTravelAdvisor = travelAdvisorComboBox.getValue();
            int amount = Integer.parseInt(amountTextField.getText());
            assignBlanks(selectedBlankType, selectedTravelAdvisor, amount);
        });

        // Create VBox for Blank Type and Travel Advisor Selection
        VBox selectionBox = new VBox();
        selectionBox.setSpacing(10);
        selectionBox.setPadding(new Insets(20));
        selectionBox.setAlignment(Pos.CENTER);
        // Add the Amount Label and TextField to the VBox
        selectionBox.getChildren().addAll(blankTypeLabel, blankTypeComboBox, travelAdvisorLabel, travelAdvisorComboBox, amountLabel, amountTextField, confirmButton);

        BorderPane.setMargin(pageTitle, new Insets(65));
        BorderPane.setAlignment(pageTitle, Pos.CENTER);
        BorderPane.setMargin(selectionBox, new Insets(10));
        BorderPane.setAlignment(selectionBox, Pos.CENTER);
        BorderPane.setMargin(confirmButton, new Insets(10, 0, 0, 0));
        BorderPane.setAlignment(confirmButton, Pos.CENTER);

        // Center all the inputs and buttons
        blankTypeComboBox.setMaxWidth(Double.MAX_VALUE);
        travelAdvisorComboBox.setMaxWidth(Double.MAX_VALUE);
        amountTextField.setMaxWidth(Double.MAX_VALUE);
        confirmButton.setMaxWidth(Double.MAX_VALUE);
        selectionBox.setAlignment(Pos.CENTER);

        BorderPane bottomPane = new BorderPane();
        bottomPane.setLeft(backToHomePage);
        BorderPane.setMargin(backToHomePage, new Insets(10, 0, 0, 10));
        bottomPane.setPadding(new Insets(10, 10, 10, 10));

        this.setTop(pageTitle);
        this.setCenter(selectionBox);
        this.setBottom(bottomPane);
    }

    // Set the same width for all buttons
    private final int buttonWidth = 200;
}