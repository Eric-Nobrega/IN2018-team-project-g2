package pages;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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


        //Button creation:

        Button confirmBlank = new Button("Confirm Blank ");
        Button returnToHome = new Button("Return to HOME ");

        //Text fields:

        TextField blankField = new TextField();

    }
}
