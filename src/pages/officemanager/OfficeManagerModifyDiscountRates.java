package pages.officemanager;

import dbfuncs.DBMethods;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class OfficeManagerModifyDiscountRates extends VBox {

    private TextField fixedDiscountRateField;
    private TextField variableDiscountRateField;
    private Button fixedDiscountRateButton;
    private Button variableDiscountRateButton;

    private int currentFixedDiscountRate;
    private int currentVariableDiscountRate;

    public OfficeManagerModifyDiscountRates(Stage stage) {
        // query db here to update the rate figures
        currentFixedDiscountRate = DBMethods.getFixedRate();
        currentVariableDiscountRate = DBMethods.getVariableRate();

        System.out.println(currentFixedDiscountRate);
        System.out.println(currentVariableDiscountRate);

        // Set spacing and padding for the VBox
        setSpacing(10);
        setPadding(new Insets(10));

        // Create labels for the fixed and variable Discount rates
        Label fixedDiscountRateLabel = new Label("Fixed Discount Rate:");
        Label variableDiscountRateLabel = new Label("Variable Discount Rate:");

        // Create labels to display the current rates
        Label currentFixedDiscountRateLabel = new Label(String.format("%d%%", currentFixedDiscountRate));
        Label currentVariableDiscountRateLabel = new Label(String.format("%d%%", currentVariableDiscountRate));

        // Create text fields for inputting the fixed and variable Discount rates
        fixedDiscountRateField = new TextField();
        variableDiscountRateField = new TextField();

        // Create buttons for confirming the fixed and variable Discount rates
        fixedDiscountRateButton = new Button("Confirm Fixed Discount Rate");
        variableDiscountRateButton = new Button("Confirm Variable Discount Rate");

        // Add event handlers to the buttons to handle confirmation actions
        fixedDiscountRateButton.setOnAction(event -> {
            // Get the input value from the fixed Discount rate field
            String fixedDiscountRate = fixedDiscountRateField.getText();

            if(fixedDiscountRate.isEmpty()){
                fixedDiscountRate = "0";
            }

            try {
                // check if the fixed Discount rate row exists
                int count = DBMethods.getDiscountRowCount("fixed");
                if (count > 0) {
                    // update the fixed Discount rate row
                    DBMethods.updateDiscountRate("fixed", Integer.parseInt(fixedDiscountRate));
                } else {
                    // create a new row for the fixed Discount rate
                    DBMethods.createDiscountRate("fixed", Integer.parseInt(fixedDiscountRate));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        variableDiscountRateButton.setOnAction(event -> {
            // Get the input value from the variable Discount rate field
            String variableDiscountRate = variableDiscountRateField.getText();

            if(variableDiscountRate.isEmpty()){
                variableDiscountRate = "0";
            }

            try {
                // check if the variable Discount rate row exists
                int count = DBMethods.getDiscountRowCount("variable");
                if (count > 0) {
                    // update the variable Discount rate row
                    DBMethods.updateDiscountRate("variable", Integer.parseInt(variableDiscountRate));
                } else {
                    // create a new row for the variable Discount rate
                    DBMethods.createDiscountRate("variable", Integer.parseInt(variableDiscountRate));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        // Add the labels, current rate labels, text fields, and buttons to the VBox
        getChildren().addAll(
                fixedDiscountRateLabel, currentFixedDiscountRateLabel, fixedDiscountRateField, fixedDiscountRateButton,
                variableDiscountRateLabel, currentVariableDiscountRateLabel, variableDiscountRateField, variableDiscountRateButton
        );
    }
}