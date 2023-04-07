import javafx.application.Application;
import javafx.stage.Stage;
import pages.general.LoginPage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Launch The Login Page
        LoginPage loginPage = new LoginPage();
        loginPage.setStage(primaryStage);
        loginPage.createLoginPage();

        // Set The Application Title
        primaryStage.setTitle("AirVia LTD");
        primaryStage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}