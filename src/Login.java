import java.sql.*;

public class Login {

    public static boolean authenticate(String username, String password) {

        // SQL query to check user credentials
        String query = "SELECT * FROM USER WHERE UserName = ? AND PASSWORD = ?";

        try {
            // establish database connection
            Connection databaseConnection = DBConnection.connectDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            // execute query
            ResultSet rs = statement.executeQuery();

            // check if user exists in database
            boolean success = rs.next();

            // close database resources
            rs.close();
            statement.close();
            databaseConnection.close();

            return success;
        } catch (SQLException e) {
            System.out.println("Login Error!");
            e.printStackTrace();
            return false;
        }
    }
}