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

public class AgencyDetailsPage extends BorderPane{

    public AgencyDetailsPage (Stage stage){

        //Label creation:

        Label welcomeLabel  = new Label("Agency Details");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label nameLabel = new Label("Name: ");
        Label locationLabel = new Label("Location: ");
        Label emailAddressLabel = new Label("Email Address: ");
        Label contactNumberLabel = new Label("Contact Number: ");
        Label blankLabel = new Label("Blank: ");

        //Creating text fields:

        TextField nameTextField = new TextField();
        TextField locationField = new TextField();
        TextField emailField = new TextField();
        TextField contactField = new TextField();



        //Creating Save Button

        Button saveButton = new Button("Save");

        //Creating Return to Home Button

        Button returnToHome = new Button("Return to HOME");


    }
}
