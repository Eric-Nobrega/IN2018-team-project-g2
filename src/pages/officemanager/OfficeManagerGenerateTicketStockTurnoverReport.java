package pages.officemanager;

import com.sun.javafx.menu.MenuItemBase;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pages.traveladvisor.TravelAdvisorMainPage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static dbfuncs.DBMethods.*;


public class OfficeManagerGenerateTicketStockTurnoverReport extends BorderPane {

    public OfficeManagerGenerateTicketStockTurnoverReport(Stage stage, String startDate, String endDate) {
        // Create Title Text
        Label pageTitle = new Label(startDate + " - " + endDate + " Ticket Stock Turnover Report");
        pageTitle.setFont(Font.font("Karla", FontWeight.BOLD, 24));
        pageTitle.setUnderline(true);
        pageTitle.setTextAlignment(TextAlignment.CENTER);

        // Create a table view
        TableView<TicketStockTurnoverReport> tableView = new TableView<>();

// Create columns and map them to the properties of the TicketStockTurnoverReport class
        TableColumn<TicketStockTurnoverReport, Integer> receivedColumn = new TableColumn<>("New Blanks Received");
        receivedColumn.setCellValueFactory(new PropertyValueFactory<>("newBlanksReceivedByAgent"));

        TableColumn<TicketStockTurnoverReport, Integer> assignedColumn = new TableColumn<>("Blanks Assigned to Advisors");
        assignedColumn.setCellValueFactory(new PropertyValueFactory<>("blanksAssignedToAdvisors"));

        TableColumn<TicketStockTurnoverReport, Integer> existingAssignedColumn = new TableColumn<>("Existing Blanks Assigned to Advisors");
        existingAssignedColumn.setCellValueFactory(new PropertyValueFactory<>("existingBlanksAssignedToAdvisors"));

        TableColumn<TicketStockTurnoverReport, Integer> usedColumn = new TableColumn<>("Blanks Used by Agent");
        usedColumn.setCellValueFactory(new PropertyValueFactory<>("blanksUsedByAgent"));

        TableColumn<TicketStockTurnoverReport, Integer> availableColumn = new TableColumn<>("Blanks Available in Agent Stock");
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("blanksAvailableInAgentStock"));

        TableColumn<TicketStockTurnoverReport, Integer> amountColumn = new TableColumn<>("Amount of Blanks of Given Type");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amountOfBlanksOfGivenType"));
        amountColumn.setPrefWidth(300);

// Add the columns to the table view
        tableView.getColumns().addAll(receivedColumn, assignedColumn, existingAssignedColumn, usedColumn, availableColumn, amountColumn);

// Create a list of TicketStockTurnoverReport objects and add them to the table view
        ObservableList<TicketStockTurnoverReport> reportList = FXCollections.observableArrayList();
        TicketStockTurnoverReport report1 = new TicketStockTurnoverReport(countNewBlanksReceivedByAgent(startDate, endDate), countBlanksAssignedToAdvisors(startDate, endDate), countExistingBlanksAssignedToAdvisors(startDate, endDate), countBlanksUsedByTheAdvisors(startDate, endDate), countBlanksAvailableInAgentsStock(startDate, endDate), countAmountOfBlanksOfAGivenType(startDate, endDate));
        reportList.add(report1);

        tableView.setStyle("-fx-font-size: 16px; -fx-font-family: Arial;");

// Add the table view to the layout
        tableView.setItems(reportList);

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
                double scaleX = pageWidth / tableView.getBoundsInParent().getWidth();
                double scaleY = scaleX;
                double tableHeight = tableView.getBoundsInParent().getHeight() * scaleY;
                if (tableHeight > pageHeight) {
                    scaleY = pageHeight / tableView.getBoundsInParent().getHeight();
                    scaleX = scaleY;
                }
                double tableWidth = tableView.getBoundsInParent().getWidth() * scaleX;

                // Create a snapshot of the table
                SnapshotParameters snapshotParams = new SnapshotParameters();
                snapshotParams.setFill(Color.WHITE);
                WritableImage snapshot = tableView.snapshot(snapshotParams, null);

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
                    ObservableList<TicketStockTurnoverReport> data = tableView.getItems();
                    for (TicketStockTurnoverReport row : data) {
                        fileWriter.write("Data From " + startDate + " - " + endDate);
                        fileWriter.write("Blanks Received By Agent: " + row.getNewBlanksReceivedByAgent() + "\t");
                        fileWriter.write("Blanks Assigned To Advisors: " + row.getBlanksAssignedToAdvisors() + "\t");
                        fileWriter.write("Existing Blanks Assigned To Advisors: " + row.getExistingBlanksAssignedToAdvisors() + "\t");
                        fileWriter.write("Blanks Used By Agents: " + row.getBlanksUsedByAgent() + "\t");
                        fileWriter.write("Blanks Available In Agent Stock: " + row.getBlanksAvailableInAgentStock() + "\t");
                        fileWriter.write("Blanks Of Given Type: " + row.getAmountOfBlanksOfGivenType() + "\t");
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

        // Back To Home Button Logic
        Button backToHomePage = new Button("Back To Home");
        backToHomePage.setOnAction(event -> {
            OfficeManagerMainPage travelAdvisorMainPage = new OfficeManagerMainPage(stage);
            Scene scene = new Scene(travelAdvisorMainPage, 850, 500);
            stage.setScene(scene);
        });

// Add the table view to the layout
        this.setCenter(tableView);


// Add the components to the layout
        this.setTop(pageTitle);
        HBox buttonsBox = new HBox(saveButton, saveButton2, backToHomePage);
        this.setBottom(buttonsBox);

// Set the layout properties
        this.setPadding(new Insets(10));
        setAlignment(pageTitle, Pos.CENTER);
        setAlignment(buttonsBox, Pos.CENTER_RIGHT);
        setMargin(buttonsBox, new Insets(0, 10, 0, 0));

    }

    public class TicketStockTurnoverReport {
        // received
        private int newBlanksReceivedByAgent;
        private int blanksAssignedToAdvisors;

        // assigned/used blanks
        private int existingBlanksAssignedToAdvisors;
        private int blanksUsedByAgent;

        // final amounts
        private int blanksAvailableInAgentStock;
        private int amountOfBlanksOfGivenType;

        // Constructor
        public TicketStockTurnoverReport(int newBlanksReceivedByAgent, int blanksAssignedToAdvisors,
                                         int existingBlanksAssignedToAdvisors, int blanksUsedByAgent,
                                         int blanksAvailableInAgentStock, int amountOfBlanksOfGivenType) {
            this.newBlanksReceivedByAgent = newBlanksReceivedByAgent;
            this.blanksAssignedToAdvisors = blanksAssignedToAdvisors;
            this.existingBlanksAssignedToAdvisors = existingBlanksAssignedToAdvisors;
            this.blanksUsedByAgent = blanksUsedByAgent;
            this.blanksAvailableInAgentStock = blanksAvailableInAgentStock;
            this.amountOfBlanksOfGivenType = amountOfBlanksOfGivenType;
        }

        // Getters and Setters

        public int getNewBlanksReceivedByAgent() {
            return newBlanksReceivedByAgent;
        }

        public void setNewBlanksReceivedByAgent(int newBlanksReceivedByAgent) {
            this.newBlanksReceivedByAgent = newBlanksReceivedByAgent;
        }

        public int getBlanksAssignedToAdvisors() {
            return blanksAssignedToAdvisors;
        }

        public void setBlanksAssignedToAdvisors(int blanksAssignedToAdvisors) {
            this.blanksAssignedToAdvisors = blanksAssignedToAdvisors;
        }

        public int getExistingBlanksAssignedToAdvisors() {
            return existingBlanksAssignedToAdvisors;
        }

        public void setExistingBlanksAssignedToAdvisors(int existingBlanksAssignedToAdvisors) {
            this.existingBlanksAssignedToAdvisors = existingBlanksAssignedToAdvisors;
        }

        public int getBlanksUsedByAgent() {
            return blanksUsedByAgent;
        }

        public void setBlanksUsedByAgent(int blanksUsedByAgent) {
            this.blanksUsedByAgent = blanksUsedByAgent;
        }

        public int getBlanksAvailableInAgentStock() {
            return blanksAvailableInAgentStock;
        }

        public void setBlanksAvailableInAgentStock(int blanksAvailableInAgentStock) {
            this.blanksAvailableInAgentStock = blanksAvailableInAgentStock;
        }

        public int getAmountOfBlanksOfGivenType() {
            return amountOfBlanksOfGivenType;
        }

        public void setAmountOfBlanksOfGivenType(int amountOfBlanksOfGivenType) {
            this.amountOfBlanksOfGivenType = amountOfBlanksOfGivenType;
        }


    }
}
