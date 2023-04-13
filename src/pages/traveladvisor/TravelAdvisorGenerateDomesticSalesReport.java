package pages.traveladvisor;

import dbfuncs.DBMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pages.officemanager.OfficeManagerGenerateGlobalDomesticReport;
import pages.officemanager.OfficeManagerGenerateGlobalSalesReport;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static dbfuncs.DBMethods.*;
import static dbfuncs.DBMethods.countAmountOfBlanksOfAGivenType;

public class TravelAdvisorGenerateDomesticSalesReport extends BorderPane {

    public TravelAdvisorGenerateDomesticSalesReport(Stage stage, String startDate, String endDate) {
        // Create Title Text
        Label pageTitle = new Label(startDate + " - " + endDate + " Personal Domestic Sales Report");
        pageTitle.setFont(Font.font("Karla", FontWeight.BOLD, 24));
        pageTitle.setUnderline(true);
        pageTitle.setTextAlignment(TextAlignment.CENTER);

        TableView<GlobalInterlineSalesReport> table = new TableView<>();

        TableColumn<GlobalInterlineSalesReport, Integer> totalNetAmountColumn = new TableColumn<>("Total Net Amount");
        totalNetAmountColumn.setCellValueFactory(new PropertyValueFactory<>("totalNetAmount"));

        TableColumn<GlobalInterlineSalesReport, Integer> totalAmountPaidColumn = new TableColumn<>("Total Amount Paid");
        totalAmountPaidColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmountPaid"));

        TableColumn<GlobalInterlineSalesReport, Integer> totalCommissionAmountColumn = new TableColumn<>("Total Commission Amount");
        totalCommissionAmountColumn.setCellValueFactory(new PropertyValueFactory<>("totalCommissionAmount"));

        table.getColumns().addAll(totalNetAmountColumn, totalAmountPaidColumn, totalCommissionAmountColumn);

        ObservableList<GlobalInterlineSalesReport> reportList = FXCollections.observableArrayList();
        GlobalInterlineSalesReport report1 = DBMethods.PersonalDomesticSalesReport(startDate, endDate);
        reportList.add(report1);

        table.setStyle("-fx-font-size: 16px; -fx-font-family: Arial;");

        table.setItems(reportList);

        // Create the save button
        Button saveButton = new Button("Print");
        saveButton.setOnAction(e -> {
            // Get the printer job
            PrinterJob printerJob = PrinterJob.createPrinterJob();
            if (printerJob != null && printerJob.showPrintDialog(stage)) {
                // Set up the page layout
                PageLayout pageLayout = printerJob.getJobSettings().getPageLayout();
                double pageWidth = pageLayout.getPrintableWidth();
                double pageHeight = pageLayout.getPrintableHeight();
                double scaleX = pageWidth / table.getBoundsInParent().getWidth();
                double scaleY = scaleX;
                double tableHeight = table.getBoundsInParent().getHeight() * scaleY;
                if (tableHeight > pageHeight) {
                    scaleY = pageHeight / table.getBoundsInParent().getHeight();
                    scaleX = scaleY;
                }
                double tableWidth = table.getBoundsInParent().getWidth() * scaleX;

                // Create a snapshot of the table
                SnapshotParameters snapshotParams = new SnapshotParameters();
                snapshotParams.setFill(Color.WHITE);
                WritableImage snapshot = table.snapshot(snapshotParams, null);

                // Print the snapshot
                ImageView imageView = new ImageView(snapshot);
                imageView.setFitWidth(tableWidth);
                imageView.setFitHeight(tableHeight);
                boolean success = printerJob.printPage(imageView);
                if (success) {
                    printerJob.endJob();
                }
            }
        });

        // Create Back To Home Navigation Button
        Button backToHomePage = new Button("Back To Home");

        // Back To Home Button Logic
        backToHomePage.setOnAction(event -> {
            TravelAdvisorMainPage travelAdvisorMainPage = new TravelAdvisorMainPage(stage);
            Scene scene = new Scene(travelAdvisorMainPage, 850, 500);
            stage.setScene(scene);
        });

        // Create the save button
        Button saveButton2 = new Button("Save");
        saveButton2.setOnAction(e -> {
            // Create a new file chooser dialog
            FileChooser fileChooser = new FileChooser();

            // Set the title and initial directory for the file chooser dialog
            fileChooser.setTitle("Save Table Data");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

            // Add a file filter to show only .txt files in the dialog
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            // Show the dialog and get the selected file
            File file = fileChooser.showSaveDialog(stage);

            // If a file was selected, write the table data to it
            if (file != null) {
                try {
                    // Create a new file writer
                    FileWriter fileWriter = new FileWriter(file);

                    // Write the table data to the file
                    ObservableList<GlobalInterlineSalesReport> data = table.getItems();
                    for (GlobalInterlineSalesReport row : data) {
                        fileWriter.write("Data From " + startDate + " - " + endDate);
                        fileWriter.write("Total Net Amount: " + row.getTotalNetAmount() + "\t");
                        fileWriter.write("Total Amount Paid: " + row.getTotalAmountPaid() + "\t");
                        fileWriter.write("Total Commission Amount: " + row.getTotalCommissionAmount() + "\t");
                        // etc. for all fields
                        fileWriter.write("\n");
                    }

                    // Close the file writer
                    fileWriter.close();

                } catch (IOException event) {
                    event.printStackTrace();
                }
            }

        });

        // Add TableView to layout
        this.setCenter(table);

        HBox buttonsBox = new HBox(saveButton, saveButton2, backToHomePage);
        this.setBottom(buttonsBox);

        // Create BorderPane Layout
        this.setTop(pageTitle);
        this.setPadding(new Insets(20));
        this.setStyle("-fx-background-color: #FFFFFF;");
    }

    public static class GlobalInterlineSalesReport extends OfficeManagerGenerateGlobalDomesticReport.GlobalDomesticSalesReport {
        private int totalNetAmount;
        private int totalAmountPaid;
        private int totalCommissionAmount;

        public GlobalInterlineSalesReport(int totalNetAmount, int totalAmountPaid, int totalCommissionAmount) {
            this.totalNetAmount = totalNetAmount;
            this.totalAmountPaid = totalAmountPaid;
            this.totalCommissionAmount = totalCommissionAmount;
        }

        public GlobalInterlineSalesReport() {

        }

        public int getTotalNetAmount() {
            return totalNetAmount;
        }

        public void setTotalNetAmount(int totalNetAmount) {
            this.totalNetAmount = totalNetAmount;
        }

        public int getTotalAmountPaid() {
            return totalAmountPaid;
        }

        public void setTotalAmountPaid(int totalAmountPaid) {
            this.totalAmountPaid = totalAmountPaid;
        }

        public int getTotalCommissionAmount() {
            return totalCommissionAmount;
        }

        public void setTotalCommissionAmount(int totalCommissionAmount) {
            this.totalCommissionAmount = totalCommissionAmount;
        }
    }
}
