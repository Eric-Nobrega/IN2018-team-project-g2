package pages;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CreateTicketPage extends BorderPane {

    public CreateTicketPage(Stage stage) {
        Label welcomeLabel = new Label("Create Ticket");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        ComboBox<String> fromCombo = new ComboBox<>(FXCollections.observableArrayList("placeholderoption1", "placeholderoption2", "placeholderoption3"));
        fromCombo.setPrefWidth(200);
        fromCombo.setMaxWidth(Double.MAX_VALUE);
        fromCombo.setPromptText("From");

        ComboBox<String> toCombo = new ComboBox<>(FXCollections.observableArrayList("placeholderoption1", "placeholderoption2", "placeholderoption3"));
        toCombo.setPrefWidth(200);
        toCombo.setMaxWidth(Double.MAX_VALUE);
        toCombo.setPromptText("To");

        DatePicker datePicker1 = new DatePicker();
        datePicker1.setPrefWidth(200);
        datePicker1.setMaxWidth(Double.MAX_VALUE);

        DatePicker datePicker2 = new DatePicker();
        datePicker2.setPrefWidth(200);
        datePicker2.setMaxWidth(Double.MAX_VALUE);

        Label label1 = new Label("Blank NO:");
        TextField textfield1 = new TextField();
        textfield1.setMaxWidth(200);

        Label label2 = new Label("Price Per Passenger");
        TextField textfield2 = new TextField();
        textfield2.setMaxWidth(200);

        Label label3 = new Label("No of Passengers");
        TextField textfield3 = new TextField();
        textfield3.setMaxWidth(200);

        VBox centerBox = new VBox(5, fromCombo, toCombo, datePicker1, datePicker2, label1, textfield1, label2, textfield2, label3, textfield3);
        centerBox.setAlignment(Pos.CENTER);

        BorderPane.setMargin(welcomeLabel, new Insets(45));
        BorderPane.setMargin(centerBox, new Insets(-50, 10, 10, 10));

        // Create the HBox for the buttons and add them
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        Button button1 = new Button("Continue To Payment");
        Button button2 = new Button("Return To Home");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        buttonBox.getChildren().addAll(button1, spacer, button2);

        // Add margin to the bottom and left of the buttonBox
        BorderPane.setMargin(buttonBox, new Insets(0, 10, 10, 10));

        // Add the HBox to the bottom of the BorderPane
        this.setBottom(buttonBox);

        this.setTop(welcomeLabel);
        this.setCenter(centerBox);
    }
}
