package pages.systemadministrator;

import dbfuncs.DBMethods;
import interfaces.Blank;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SystemAdministratorViewBlankStock extends BorderPane {

    // Table view to display blank stock
    TableView<Blank> blankTable = new TableView<>();

    public SystemAdministratorViewBlankStock(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("Total Blank Stock");
        pageTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 36));
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

        BorderPane.setMargin(pageTitle, new Insets(65));
        BorderPane.setAlignment(pageTitle, Pos.CENTER);
        BorderPane.setMargin(backToHomePage, new Insets(10));

        // Define table columns
        TableColumn<Blank, String> blankTypeColumn = new TableColumn<>("Blank Type");
        blankTypeColumn.setCellValueFactory(new PropertyValueFactory<>("blankType"));

        // Define quantity column
        TableColumn<Blank, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(cellData -> {
            String blankType = cellData.getValue().getBlankType();
            int quantity = getBlankTypeQuantity(blankType);
            return new SimpleIntegerProperty(quantity).asObject();
        });

        // Add table columns to table view
        blankTable.getColumns().addAll(blankTypeColumn, quantityColumn);

        // Set table view properties
        blankTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        blankTable.setPlaceholder(new Label("No blanks found."));
        blankTable.setPrefHeight(300);

        // Retrieve data from database and populate table view
        ObservableList<Blank> blanks = getAllBlanksFromDB();
        blankTable.setItems(blanks);

        // Add table view to border pane
        this.setCenter(blankTable);
        this.setBottom(backToHomePage);
        this.setMargin(backToHomePage, new Insets(10));
    }

    private ObservableList<Blank> getAllBlanksFromDB() {
        ObservableList<Blank> blanks = FXCollections.observableArrayList();
        Map<String, Integer> quantities = new HashMap<>();
        ResultSet rs = DBMethods.getAllBlanks();
        try {
            while (rs.next()) {
                String blankType = rs.getString("BlankType");
                quantities.merge(blankType, 1, Integer::sum);
            }
            for (String blankType : quantities.keySet()) {
                int quantity = quantities.get(blankType);
                blanks.add(new Blank(0, quantity, blankType));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving blanks from database!");
            e.printStackTrace();
        }
        return blanks;
    }


    private int getBlankTypeQuantity(String blankType) {
        int quantity = 0;
        try {
            Connection connection = DBMethods.connectToDB();
            PreparedStatement statement = connection.prepareStatement("SELECT BlankType, COUNT(*) AS Quantity FROM Blanks WHERE BlankType = ? GROUP BY BlankType");
            statement.setString(1, blankType);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("Quantity");
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving blank type quantity from database!");
            e.printStackTrace();
        }
        return quantity;
    }


}

