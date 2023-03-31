import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        // Capture User Information (Username + Password)
        Scanner userInfoScanner = new Scanner(System.in);

        System.out.println("Enter username");
        String userName = userInfoScanner.nextLine();
        System.out.println("Enter password");
        String userPassword = userInfoScanner.nextLine();

        if (Login.authenticate(userName, userPassword)) {
            System.out.println("Login successful!");
        }else{
            System.out.println("Login Failed!");
        }
    }
}