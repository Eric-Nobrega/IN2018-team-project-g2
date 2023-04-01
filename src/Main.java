import javafx.application.Application;
import javafx.stage.Stage;
import pages.LoginPage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // set the title of the application
        primaryStage.setTitle("AirVia LTD");

        // create an instance of the login page, set the stage to first equal the login page
        LoginPage loginPage = new LoginPage();
        loginPage.setStage(primaryStage);

        // show login page
        loginPage.createLoginPage();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
