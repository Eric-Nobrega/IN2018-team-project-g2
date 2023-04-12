package pages.traveladvisor;

import dbfuncs.DBMethods;
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

import static dbfuncs.DBMethods.getBlanksByAdvisorID;

public class TravelAdvisorViewOwnBlankStock extends BorderPane {

    public TravelAdvisorViewOwnBlankStock(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("View Blank Stock");
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
        TableView<BlankStock> table = new TableView<>();

        TableColumn<BlankStock, String> typeColumn = new TableColumn<>("Type");
        TableColumn<BlankStock, Integer> qtyColumn = new TableColumn<>("Quantity");

        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        qtyColumn.setCellValueFactory(new PropertyValueFactory<>("qty"));

        // Add the columns to the table
        table.getColumns().addAll(typeColumn, qtyColumn);

        // Retrieve the data from the database and populate the table
        ObservableList<BlankStock> data = FXCollections.observableArrayList();
        ResultSet rs = getBlanksByAdvisorID();

        try{
            System.out.println("SUCCESS");
            while (rs.next()) {
                String type = rs.getString("type");
                int qty = rs.getInt("qty");
                BlankStock test = new BlankStock(type, qty);
                System.out.println(test.type);
                System.out.println(test.qty);
                data.add(test);
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("ERROR");
        }

        for (BlankStock item : data) {
            System.out.println(item.type);
            System.out.println(item.qty);
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
        private String type;
        private int qty;

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
