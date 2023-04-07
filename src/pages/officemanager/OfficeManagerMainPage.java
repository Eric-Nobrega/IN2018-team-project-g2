package pages.officemanager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import pages.general.LoginPage;

public class OfficeManagerMainPage extends BorderPane {

    public OfficeManagerMainPage(Stage stage) {
        // Create Title Text
        Label pageTitle = new Label("Office Manager Homepage");
        pageTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 36));
        pageTitle.setUnderline(true);
        pageTitle.setTextAlignment(TextAlignment.CENTER);

        // Create Functionality Buttons
        Button createReport1Button = new Button("Create Report 1");
        Button createReport2Button = new Button("Create Report 2");
        Button createReport3Button = new Button("Create Report 3");
        Button addCurrencyRateButton = new Button("Add Currency Conversion Rate");
        Button modifyCurrencyRateButton = new Button("Modify Currency Conversion Rate");
        Button viewBlankStockButton = new Button("View Total Blank Stock");
        Button modifyCustomerButton = new Button("Modify Customer Relationship");
        Button addCommissionButton = new Button("Add Commission Rate");
        Button modifyCommissionButton = new Button("Modify Commission Rate");
        Button setCustomerDiscountButton = new Button("Set Customer Discount Rate");

        // Create Logout Button
        Button logoutButton = new Button("Logout");

        // Logout Button Logic
        logoutButton.setOnAction(event -> {
            LoginPage loginPage = new LoginPage();
            loginPage.setStage((Stage) getScene().getWindow());
            loginPage.createLoginPage();
        });

        // Set the same width for all buttons
        int buttonWidth = 200;
        createReport1Button.setPrefWidth(buttonWidth);
        createReport2Button.setPrefWidth(buttonWidth);
        createReport3Button.setPrefWidth(buttonWidth);
        addCurrencyRateButton.setPrefWidth(buttonWidth);
        modifyCurrencyRateButton.setPrefWidth(buttonWidth);
        viewBlankStockButton.setPrefWidth(buttonWidth);
        modifyCustomerButton.setPrefWidth(buttonWidth);
        addCommissionButton.setPrefWidth(buttonWidth);
        modifyCommissionButton.setPrefWidth(buttonWidth);
        setCustomerDiscountButton.setPrefWidth(buttonWidth);

        // Create two containers to hold the buttons
        VBox leftBox = new VBox(20, createReport1Button, createReport2Button, createReport3Button, addCurrencyRateButton, modifyCurrencyRateButton);
        leftBox.setAlignment(Pos.CENTER);
        VBox rightBox = new VBox(20, viewBlankStockButton, modifyCustomerButton, addCommissionButton, modifyCommissionButton, setCustomerDiscountButton);
        rightBox.setAlignment(Pos.CENTER);

        // Create a container to hold the two containers
        HBox centerBox = new HBox(20, leftBox, rightBox);
        centerBox.setAlignment(Pos.CENTER);

        BorderPane.setMargin(pageTitle, new Insets(65));
        BorderPane.setAlignment(pageTitle, Pos.CENTER);
        BorderPane.setMargin(logoutButton, new Insets(10));
        BorderPane.setMargin(centerBox, new Insets(-150, 10, 10, 10));

        this.setTop(pageTitle);
        this.setCenter(centerBox);
        this.setBottom(logoutButton);
    }
}
