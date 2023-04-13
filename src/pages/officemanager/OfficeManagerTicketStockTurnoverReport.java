package pages.officemanager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class OfficeManagerTicketStockTurnoverReport extends BorderPane {

    public OfficeManagerTicketStockTurnoverReport(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("Ticket Report");
        pageTitle.setFont(Font.font("Karla", FontWeight.BOLD, 36));
        pageTitle.setUnderline(true);
        pageTitle.setTextAlignment(TextAlignment.CENTER);

        // Create Back To Home Navigation Button
        Button backToHomePage = new Button("Back To Home");
        backToHomePage.setPrefWidth(200);

        // Back To Home Button Logic
        backToHomePage.setOnAction(event -> {
            OfficeManagerMainPage officeManagerMainPage = new OfficeManagerMainPage(stage);
            Scene scene = new Scene(officeManagerMainPage, 850, 500);
            stage.setScene(scene);
        });

        // Create Report Period Start Field
        Label reportPeriodStartLabel = new Label("Report Period Start:");
        DatePicker reportPeriodStartTextField = new DatePicker();

        // Create Report Period End Field
        Label reportPeriodEndLabel = new Label("Report Period End:");
        DatePicker reportPeriodEndTextField = new DatePicker();

        // Create Generate Report Button
        Button generateReportButton = new Button("Generate Report");
        generateReportButton.setPrefWidth(200);

        // Generate Report Button Logic
        generateReportButton.setOnAction(event -> {
            String reportPeriodStart = String.valueOf(reportPeriodStartTextField.getValue());
            String reportPeriodEnd = String.valueOf(reportPeriodEndTextField.getValue());
            OfficeManagerGenerateTicketStockTurnoverReport modifyCustomerRelationship = new OfficeManagerGenerateTicketStockTurnoverReport(stage, reportPeriodStart, reportPeriodEnd);
            Scene scene = new Scene(modifyCustomerRelationship, 1200, 500);
            stage.setScene(scene);
        });

        // Create VBox for Report Period Start and End Fields
        VBox dateBox = new VBox();
        dateBox.setSpacing(10);
        dateBox.setPadding(new Insets(20));
        dateBox.setAlignment(Pos.CENTER);
        // Add the label and text field for Report Period Start
        dateBox.getChildren().addAll(reportPeriodStartLabel, reportPeriodStartTextField);
        // Add the label and text field for Report Period End
        dateBox.getChildren().addAll(reportPeriodEndLabel, reportPeriodEndTextField);

        // Create VBox for Generate Report Button
        VBox generateReportBox = new VBox();
        generateReportBox.setSpacing(10);
        generateReportBox.setPadding(new Insets(20));
        generateReportBox.setAlignment(Pos.CENTER);
        generateReportBox.getChildren().add(generateReportButton);

        // Create VBox for Back To Home Button
        VBox backToHomeBox = new VBox();
        backToHomeBox.setSpacing(10);
        backToHomeBox.setPadding(new Insets(20));
        backToHomeBox.setAlignment(Pos.CENTER);
        backToHomeBox.getChildren().add(backToHomePage);

        // Create BorderPane Layout
        this.setTop(pageTitle);
        this.setCenter(dateBox);
        this.setRight(generateReportBox);
        this.setBottom(backToHomeBox);
        this.setPadding(new Insets(20));
        this.setStyle("-fx-background-color: #FFFFFF;");
    }

    private void generateReport(String reportPeriodStart, String reportPeriodEnd) {
        System.out.println("Generating TIcket Stock Turnover Report From " + reportPeriodStart + " to " + reportPeriodEnd);
    }
}
