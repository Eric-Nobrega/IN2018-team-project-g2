import java.sql.*;

public class DBConnection {
    public static void main(String[] args) {

        String url = "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g02";

        // admin user, with DDL statement rights
        String username = "in2018g02_a";
        String password = "GoGn45dL";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }
}



