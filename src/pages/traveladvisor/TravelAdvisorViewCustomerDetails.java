package pages.traveladvisor;

import java.sql.ResultSet;
import java.sql.SQLException;

import dbfuncs.DBMethods;
import interfaces.Customer;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class TravelAdvisorViewCustomerDetails extends BorderPane {

    public TravelAdvisorViewCustomerDetails(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("View Customer Details");
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

        // Create table view to display customer details
        TableView<Customer> customerTable = new TableView<>();
        customerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Customer, String> nameColumn = new TableColumn<>("CustomerName");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));

        TableColumn<Customer, String> phoneColumn = new TableColumn<>("Phone Number");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerphoneNumber"));

        TableColumn<Customer, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerEmail"));

        TableColumn<Customer, String> cardNumberColumn = new TableColumn<>("Card Number");
        cardNumberColumn.setCellValueFactory(new PropertyValueFactory<>("CardNumber"));

        TableColumn<Customer, String> cvvColumn = new TableColumn<>("CVV");
        cvvColumn.setCellValueFactory(new PropertyValueFactory<>("cvv"));

        TableColumn<Customer, String> relationshipColumn = new TableColumn<>("Relationship");
        relationshipColumn.setCellValueFactory(new PropertyValueFactory<>("relationship"));

        customerTable.getColumns().addAll(nameColumn, phoneColumn, emailColumn, cardNumberColumn, cvvColumn, relationshipColumn);

        // Populate table view with customer details from database
        ObservableList<Customer> customerData = FXCollections.observableArrayList();

        try {
            ResultSet resultSet = DBMethods.getAllCustomers();
            while (resultSet.next()) {
                String name = resultSet.getString("CustomerName");
                int phoneNumber = Integer.parseInt(resultSet.getString("CustomerphoneNumber"));
                String email = resultSet.getString("CustomerEmail");
                String cardNumber = resultSet.getString("CardNumber");
                int cvv = Integer.parseInt(resultSet.getString("CVV"));
                String relationship = resultSet.getString("Relationship");
                Customer customer = new Customer(1, name, phoneNumber, email, cardNumber, cvv, relationship, 1);
                customerData.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        customerTable.setItems(customerData);

        // Create VBox to hold table view
        VBox tableContainer = new VBox();
        tableContainer.setPadding(new Insets(20));
        tableContainer.getChildren().add(customerTable);

        // Set the center of the BorderPane to hold the table view
        this.setCenter(tableContainer);

        // Set the top and bottom of the BorderPane as before
        this.setTop(pageTitle);
        this.setBottom(backToHomePage);
    }
}
