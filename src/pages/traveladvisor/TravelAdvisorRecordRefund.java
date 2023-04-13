package pages.traveladvisor;

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
import pages.traveladvisor.TravelAdvisorMainPage;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dbfuncs.DBMethods.*;

public class TravelAdvisorRecordRefund  extends BorderPane {

    public TravelAdvisorRecordRefund(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("Refund A Ticket");
        pageTitle.setFont(Font.font("Karla", FontWeight.BOLD, 36));
        pageTitle.setUnderline(true);
        pageTitle.setTextAlignment(TextAlignment.CENTER);

        // Create Back To Home Navigation Button
        Button backToHomePage = new Button("Back To Home");

        // Back To Home Button Logic
        backToHomePage.setOnAction(event -> {
            TravelAdvisorMainPage travelAdvisorMainPage = new TravelAdvisorMainPage(stage);
            Scene scene = new Scene(travelAdvisorMainPage, 850, 500);
            stage.setScene(scene);
        });

        // Create Labels
        Label blankTypeLabel = new Label("Ticket Number: ");

        // Create TextFields
        ComboBox<String> blankTypeField = new ComboBox<>();

        // call the getAllTravelAgents method to retrieve the ResultSet
        ResultSet rs = getAllTickets();

        try {
            // iterate over the ResultSet and add each FullName value to the ComboBox
            while (rs.next()) {
                String fullName = rs.getString("TicketId");
                blankTypeField.getItems().add(fullName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Create Add Blanks Button
        Button addBlanksButton = new Button("Refund Ticket");

        // Add Blanks Button Logic
        addBlanksButton.setOnAction(event -> {
            DBMethods.refundTicket(Integer.parseInt(blankTypeField.getValue()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully Recorded A Refund");
            alert.showAndWait();
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
