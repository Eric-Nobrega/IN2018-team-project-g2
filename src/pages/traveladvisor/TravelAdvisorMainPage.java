package pages.traveladvisor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import pages.general.LoginPage;

public class TravelAdvisorMainPage extends BorderPane {

    public TravelAdvisorMainPage(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("Travel Advisor Homepage");
        pageTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 36));
        pageTitle.setUnderline(true);
        pageTitle.setTextAlignment(TextAlignment.CENTER);

        // Create Functionality Buttons
        Button generateSalesReportButton = new Button("Generate Sales Report");
        Button viewOwnBlankStockButton = new Button("View Blank Stock (Own)");
        Button recordSaleButton = new Button("Record A Sale");
        Button recordRefundButton = new Button("Record A Refund");
        Button viewCustomerDetailsButton = new Button("View Customer Details");
        Button addNewCustomerDetailsButton = new Button("Add New Customer Details");

        // Create Logout Button
        Button logoutButton = new Button("Logout");

        // Logout Button Functionality
        logoutButton.setOnAction(event -> {
            LoginPage loginPage = new LoginPage();
            loginPage.setStage((Stage) getScene().getWindow());
            loginPage.createLoginPage();
        });

        // Set the same width for all buttons
        int buttonWidth = 200;
        generateSalesReportButton.setPrefWidth(buttonWidth);
        viewOwnBlankStockButton.setPrefWidth(buttonWidth);
        recordSaleButton.setPrefWidth(buttonWidth);
        recordRefundButton.setPrefWidth(buttonWidth);
        viewCustomerDetailsButton.setPrefWidth(buttonWidth);
        addNewCustomerDetailsButton.setPrefWidth(buttonWidth);

        // Center the Items
        VBox centerBox = new VBox(20, generateSalesReportButton, viewOwnBlankStockButton, recordSaleButton, recordRefundButton , viewCustomerDetailsButton, addNewCustomerDetailsButton);
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