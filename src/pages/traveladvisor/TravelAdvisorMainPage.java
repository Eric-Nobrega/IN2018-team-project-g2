package pages.traveladvisor;

import dbfuncs.DBMethods;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import pages.general.LoginPage;

import java.sql.ResultSet;
import java.sql.SQLException;

import static dbfuncs.DBMethods.getAllBlanksCodes;

public class TravelAdvisorMainPage extends BorderPane {

    public TravelAdvisorMainPage(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("Travel Advisor Homepage");
        pageTitle.setFont(Font.font("Karla", FontWeight.BLACK, 36));
        pageTitle.setUnderline(true);
        pageTitle.setTextAlignment(TextAlignment.CENTER);

        // Create Functionality Buttons
        Button generateDomesticSalesReport = new Button("Generate Domestic Sales Report");
        Button generateInterlineSalesReport = new Button("Generate Interline Sales Report");
        Button viewOwnBlankStockButton = new Button("View Blank Stock (Own)");
        ComboBox<String> blankTypeField = new ComboBox<>();
        blankTypeField.setPromptText("Record A Sale");
        Button recordRefundButton = new Button("Record A Refund");
        Button viewCustomerDetailsButton = new Button("View Customer Details");
        Button viewRefundsButton = new Button("View Refunds");

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

        blankTypeField.setOnAction(event -> {
            String selectedBlankCode = blankTypeField.getValue();
            // Do something with the selected blank code
            boolean isEnough = DBMethods.isEnoughBlanksByAdvisorID(selectedBlankCode, DBMethods.getTravelAdvisorID());

            if(isEnough){
                TravelAdvisorRecordSale travelAdvisorRecordSale = new TravelAdvisorRecordSale(stage, selectedBlankCode);
                Scene scene = new Scene(travelAdvisorRecordSale, 850, 500);
                stage.setScene(scene);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Not enough blanks of type " + selectedBlankCode + " available.", ButtonType.OK);
                alert.showAndWait();
            }
        });


        // Create Logout Button
        Button logoutButton = new Button("Logout");

        // Page Routes
        viewOwnBlankStockButton.setOnAction(event -> {
        TravelAdvisorViewOwnBlankStock travelAdvisorViewOwnBlankStock = new TravelAdvisorViewOwnBlankStock(stage);
         Scene scene = new Scene(travelAdvisorViewOwnBlankStock, 850, 500);
        stage.setScene(scene);
        });

        viewRefundsButton.setOnAction(event -> {
          TravelAdvisorViewRefunds travelAdvsorViewRefunds = new TravelAdvisorViewRefunds(stage);
          Scene scene = new Scene(travelAdvsorViewRefunds, 850, 500);
          stage.setScene(scene);
        });

        viewCustomerDetailsButton.setOnAction(event -> {
            TravelAdvisorViewCustomerDetails travelAdvisorViewOwnBlankStock = new TravelAdvisorViewCustomerDetails(stage);
            Scene scene = new Scene(travelAdvisorViewOwnBlankStock, 850, 500);
            stage.setScene(scene);
        });

        recordRefundButton.setOnAction(event -> {
            TravelAdvisorRecordRefund travelAdvisorRecordRefund = new TravelAdvisorRecordRefund(stage);
            Scene scene = new Scene(travelAdvisorRecordRefund, 850, 500);
            stage.setScene(scene);
        });

        generateDomesticSalesReport.setOnAction(event -> {
            TravelAdvisorDomesticSalesReport travelAdvisorRecordRefund = new TravelAdvisorDomesticSalesReport(stage);
            Scene scene = new Scene(travelAdvisorRecordRefund, 850, 500);
            stage.setScene(scene);
        });

        generateInterlineSalesReport.setOnAction(event -> {
            TravelAdvisorInterlineSalesReport travelAdvisorRecordRefund = new TravelAdvisorInterlineSalesReport(stage);
            Scene scene = new Scene(travelAdvisorRecordRefund, 850, 500);
            stage.setScene(scene);
        });

        // Logout Button Functionality
        logoutButton.setOnAction(event -> {
            LoginPage loginPage = new LoginPage();
            loginPage.setStage((Stage) getScene().getWindow());
            loginPage.createLoginPage();
        });

        // Set the same width for all buttons
        int buttonWidth = 200;
        generateInterlineSalesReport.setPrefWidth(buttonWidth);
        generateDomesticSalesReport.setPrefWidth(buttonWidth);
        viewOwnBlankStockButton.setPrefWidth(buttonWidth);
        blankTypeField.setPrefWidth(buttonWidth);
        recordRefundButton.setPrefWidth(buttonWidth);
        viewCustomerDetailsButton.setPrefWidth(buttonWidth);
        viewRefundsButton.setPrefWidth(buttonWidth);

        // Center the Items
        VBox centerBox = new VBox(20, generateDomesticSalesReport, generateInterlineSalesReport,  viewOwnBlankStockButton, blankTypeField, recordRefundButton , viewCustomerDetailsButton, viewRefundsButton);
        centerBox.setAlignment(Pos.CENTER);

        BorderPane.setMargin(pageTitle, new Insets(65));
        BorderPane.setAlignment(pageTitle, Pos.CENTER);
        BorderPane.setMargin(logoutButton, new Insets(10));
        BorderPane.setMargin(centerBox, new Insets(-110, 10, 10, 10));

        this.setTop(pageTitle);
        this.setCenter(centerBox);
        this.setBottom(logoutButton);
    }
}