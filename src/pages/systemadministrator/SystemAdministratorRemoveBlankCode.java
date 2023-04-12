package pages.systemadministrator;

import dbfuncs.DBMethods;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

public class SystemAdministratorRemoveBlankCode extends BorderPane {

    public SystemAdministratorRemoveBlankCode(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("Remove Existing Blank Code");
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
        Label blankTypeLabel = new Label("Existing Blank Code: ");

        // Create TextFields
        ComboBox<String> blankTypeField = new ComboBox<>();

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

        // Create Remove Code Button
        Button removeCodeButton = new Button("Remove Code");

        // Remove Code Button Logic
        removeCodeButton.setOnAction(event -> {
            String blankType = blankTypeField.getValue();

            // Remove the blank code from the database
            DBMethods.removeBlankCode(blankType);
            System.out.println("Blank code removed successfully");
        });

        // Create Horizontal Box for Blank Type Input
        HBox blankTypeBox = new HBox(10, blankTypeLabel, blankTypeField);
        blankTypeBox.setAlignment(Pos.CENTER);

        // Create Vertical Box for Inputs and Remove Code Button
        VBox removeCodeBox = new VBox(20, blankTypeBox, removeCodeButton);
        removeCodeBox.setAlignment(Pos.CENTER);

        BorderPane.setMargin(pageTitle, new Insets(65));
        BorderPane.setAlignment(pageTitle, Pos.CENTER);
        BorderPane.setMargin(backToHomePage, new Insets(10));
        BorderPane.setMargin(removeCodeBox, new Insets(-150, 10, 10, 10));

        this.setTop(pageTitle);
        this.setCenter(removeCodeBox);
        this.setBottom(backToHomePage);
    }
}
