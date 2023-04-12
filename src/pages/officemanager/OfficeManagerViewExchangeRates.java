package pages.officemanager;

import java.sql.ResultSet;
import java.sql.SQLException;

import dbfuncs.DBMethods;
import interfaces.ExchangeRate;
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

public class OfficeManagerViewExchangeRates extends BorderPane {

    public OfficeManagerViewExchangeRates(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("View Exchange Rates");
        pageTitle.setFont(Font.font("Karla", FontWeight.BOLD, 36));
        pageTitle.setUnderline(true);
        pageTitle.setTextAlignment(TextAlignment.CENTER);

        // Create Back To Home Navigation Button
        Button backToHomePage = new Button("Back To Home");

        // Page Routes
        backToHomePage.setOnAction(event -> {
            OfficeManagerMainPage officeManagerMainPage = new OfficeManagerMainPage(stage);
            Scene scene = new Scene(officeManagerMainPage, 850, 500);
            stage.setScene(scene);
        });

        // Create table view to display exchange rates
        TableView<ExchangeRate> exchangeRateTable = new TableView<>();

        TableColumn<ExchangeRate, String> currencyColumn = new TableColumn<>("Currency");
        currencyColumn.setCellValueFactory(new PropertyValueFactory<>("CurrencyName"));

        TableColumn<ExchangeRate, Double> rateColumn = new TableColumn<>("Local Currency Value In USD");
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("AmountUSD"));

        exchangeRateTable.getColumns().addAll(currencyColumn, rateColumn);

        // Fetch all exchange rates from database
        ResultSet rs = DBMethods.getAllExchangeRates();
        try {
            // Populate table view with exchange rates
            while (rs.next()) {
                ExchangeRate exchangeRate = new ExchangeRate(1, rs.getString("CurrencyName"), rs.getInt("AmountUSD"));
                exchangeRate.setAmountUSD(rs.getInt("AmountUSD"));
                exchangeRateTable.getItems().add(exchangeRate);
            }
        } catch (SQLException e) {
            System.out.println("Error populating exchange rate table!");
            e.printStackTrace();
        }

        BorderPane.setMargin(pageTitle, new Insets(65));
        BorderPane.setAlignment(pageTitle, Pos.CENTER);
        BorderPane.setMargin(backToHomePage, new Insets(10));
        BorderPane.setMargin(exchangeRateTable, new Insets(10));

        this.setTop(pageTitle);
        this.setCenter(exchangeRateTable);
        this.setBottom(backToHomePage);
    }
}
