package pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox; // import the ComboBox class
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import javax.swing.*;

public class BlanksPage extends BorderPane{

    public BlanksPage (Stage stage){

        //Label creation:
        Label welcomeLabel = new Label("Blanks ");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label numberOfBlanksLabel = new Label("No. of Blanks: ");
        Label blankTypeLabel = new Label("Blank Type: ");

        //TextField for number of blanks:
        TextField numberOfBlanksField = new TextField();

        //ComboBox for blank type:
        ComboBox<String> blankTypeComboBox = new ComboBox<>();
        blankTypeComboBox.getItems().addAll("Type 1", "Type 2", "Type 3"); // Add the blank type options to the ComboBox

        //Button creation:
        Button confirmBlank = new Button("Confirm Blank ");
        Button returnToHome = new Button("Return to HOME ");

        //Layout for the labels and text fields:
        VBox labelFieldLayout = new VBox(10);
        labelFieldLayout.setAlignment(Pos.CENTER_LEFT);
        labelFieldLayout.getChildren().addAll(numberOfBlanksLabel, numberOfBlanksField, blankTypeLabel, blankTypeComboBox);

        //Layout for the buttons:
        VBox buttonLayout = new VBox(10);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.getChildren().addAll(confirmBlank, returnToHome);

        //Layout for the entire page:
        VBox pageLayout = new VBox(20);
        pageLayout.setAlignment(Pos.CENTER);
        pageLayout.setPadding(new Insets(50, 50, 50, 50));
        pageLayout.getChildren().addAll(welcomeLabel, labelFieldLayout, buttonLayout);

        //Set the layout for the page:
        setCenter(pageLayout);
    }
}
