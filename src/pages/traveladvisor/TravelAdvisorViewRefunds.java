package pages.traveladvisor;

import dbfuncs.DBMethods;
import interfaces.Refund;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

import static dbfuncs.DBMethods.getAllRefunds;
import static dbfuncs.DBMethods.getBlanksByAdvisorID;

public class TravelAdvisorViewRefunds extends BorderPane {

    public TravelAdvisorViewRefunds(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("View Refunds");
        pageTitle.setFont(Font.font("Karla", FontWeight.BOLD, 36));
        pageTitle.setUnderline(true);
        pageTitle.setTextAlignment(TextAlignment.CENTER);

        // Create Back To Home Navigation Button
        Button backToHomePage = new Button("Back To Home");

        // Page Routes
        backToHomePage.setOnAction(event -> {
            TravelAdvisorMainPage travelAdvisorMainPage = new TravelAdvisorMainPage(stage);
            Scene scene = new Scene(travelAdvisorMainPage, 850, 500);
            stage.setScene(scene);
        });

        BorderPane.setMargin(pageTitle, new Insets(65));
        BorderPane.setAlignment(pageTitle, Pos.CENTER);
        BorderPane.setMargin(backToHomePage, new Insets(10));

        // Create the TableView control
        TableView<Refund> table = new TableView<>();

        TableColumn<Refund, Integer> typeColumn = new TableColumn<>("TicketID");
        TableColumn<Refund, String> qtyColumn = new TableColumn<>("Date");

        typeColumn.setCellValueFactory(new PropertyValueFactory<>("TicketID"));
        qtyColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));

        // Add the columns to the table
        table.getColumns().addAll(typeColumn, qtyColumn);

        // Retrieve the data from the database and populate the table
        ObservableList<Refund> data = FXCollections.observableArrayList();
        ResultSet rs = getAllRefunds();

        try{
            System.out.println("SUCCESS");
            while (rs.next()) {
                String dateValue = rs.getString("Date");
                int ticketID = rs.getInt("ticketID");
                Refund refundObj = new Refund(dateValue, ticketID);
                data.add(refundObj);
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("ERROR");
        }

        table.setItems(data);

        // Add the table to the center of the BorderPane
        this.setCenter(table);

        // Set the top and bottom of the BorderPane as before
        this.setTop(pageTitle);
        this.setBottom(backToHomePage);
    }

    // Define a BlankStock class to hold the data from the database
    public static class BlankStock {
        private final String type;
        private final int qty;

        public BlankStock(String type, int qty) {
            this.type = type;
            this.qty = qty;
        }

        public String getType() {
            return type;
        }

        public int getQty() {
            return qty;
        }
    }

}
