package pages;

import dbfuncs.DBQuery;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ModifyCommisionRate extends VBox {

    private TextField fixedCommissionRateField;
    private TextField variableCommissionRateField;
    private Button fixedCommissionRateButton;
    private Button variableCommissionRateButton;

    private int currentFixedCommissionRate;
    private int currentVariableCommissionRate;

    public ModifyCommisionRate(Stage stage) {
        // query db here to update the rate figures
        currentFixedCommissionRate = DBQuery.getFixedRate();
        currentVariableCommissionRate = DBQuery.getVariableRate();

        System.out.println(currentFixedCommissionRate);
        System.out.println(currentVariableCommissionRate);

        // Set spacing and padding for the VBox
        setSpacing(10);
        setPadding(new Insets(10));

        // Create labels for the fixed and variable commission rates
        Label fixedCommissionRateLabel = new Label("Fixed Commission Rate:");
        Label variableCommissionRateLabel = new Label("Variable Commission Rate:");

        // Create labels to display the current rates
        Label currentFixedCommissionRateLabel = new Label(String.format("%d%%", currentFixedCommissionRate));
        Label currentVariableCommissionRateLabel = new Label(String.format("%d%%", currentVariableCommissionRate));

        // Create text fields for inputting the fixed and variable commission rates
        fixedCommissionRateField = new TextField();
        variableCommissionRateField = new TextField();

        // Create buttons for confirming the fixed and variable commission rates
        fixedCommissionRateButton = new Button("Confirm Fixed Commission Rate");
        variableCommissionRateButton = new Button("Confirm Variable Commission Rate");

        // Add event handlers to the buttons to handle confirmation actions
        fixedCommissionRateButton.setOnAction(event -> {
            // Get the input value from the fixed commission rate field
            String fixedCommissionRate = fixedCommissionRateField.getText();

            try {
                // check if the fixed commission rate row exists
                int count = DBQuery.getCommissionRowCount("fixed");
                if (count > 0) {
                    // update the fixed commission rate row
                    DBQuery.updateCommissionRate("fixed", Integer.parseInt(fixedCommissionRate));
                } else {
                    // create a new row for the fixed commission rate
                    DBQuery.createCommissionRate("fixed", Integer.parseInt(fixedCommissionRate));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        variableCommissionRateButton.setOnAction(event -> {
            // Get the input value from the variable commission rate field
            String variableCommissionRate = variableCommissionRateField.getText();

            try {
                // check if the variable commission rate row exists
                int count = DBQuery.getCommissionRowCount("variable");
                if (count > 0) {
                    // update the variable commission rate row
                    DBQuery.updateCommissionRate("variable", Integer.parseInt(variableCommissionRate));
                } else {
                    // create a new row for the variable commission rate
                    DBQuery.createCommissionRate("variable", Integer.parseInt(variableCommissionRate));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        // Add the labels, current rate labels, text fields, and buttons to the VBox
        getChildren().addAll(
                fixedCommissionRateLabel, currentFixedCommissionRateLabel, fixedCommissionRateField, fixedCommissionRateButton,
                variableCommissionRateLabel, currentVariableCommissionRateLabel, variableCommissionRateField, variableCommissionRateButton
        );
    }
}
