package dbfuncs;

import java.sql.*;

public class DBQuery {

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

    public static String getRole(String username) {

        // SQL query to retrieve user role
        String query = "SELECT ROLE FROM USER WHERE UserName = ?";

        try {
            // establish database connection
            Connection databaseConnection = DBConnection.connectDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            statement.setString(1, username);

            // execute query
            ResultSet rs = statement.executeQuery();

            // retrieve user role
            String role = null;
            if (rs.next()) {
                role = rs.getString("ROLE");
            }

            // close database resources
            rs.close();
            statement.close();
            databaseConnection.close();

            return role;
        } catch (SQLException e) {
            System.out.println("Error getting user role!");
            e.printStackTrace();
            return null;
        }
    }

    public static int getFixedRate() {
        // SQL query to retrieve fixed commission rate from the commission table
        String query = "SELECT rate FROM Commision WHERE type = 'fixed'";

        try {
            // establish database connection
            Connection databaseConnection = DBConnection.connectDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);

            // execute query
            ResultSet rs = statement.executeQuery();

            // retrieve fixed commission rate
            int rate = 0;
            if (rs.next()) {
                rate = rs.getInt("rate");
            }

            // close database resources
            rs.close();
            statement.close();
            databaseConnection.close();

            return rate;
        } catch (SQLException e) {
            System.out.println("Error getting fixed commission rate!");
            e.printStackTrace();
            return 0;
        }
    }

    public static int getVariableRate() {
        // SQL query to retrieve variable commission rate from the commission table
        String query = "SELECT rate FROM Commision WHERE type = 'variable'";

        try {
            // establish database connection
            Connection databaseConnection = DBConnection.connectDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);

            // execute query
            ResultSet rs = statement.executeQuery();

            // retrieve variable commission rate
            int rate = 0;
            if (rs.next()) {
                rate = rs.getInt("rate");
            }

            // close database resources
            rs.close();
            statement.close();
            databaseConnection.close();

            return rate;
        } catch (SQLException e) {
            System.out.println("Error getting variable commission rate!");
            e.printStackTrace();
            return 0;
        }
    }

    public static void updateCommissionRate(String type, int rate) {

        try {
            // establish database connection
            Connection databaseConnection = DBConnection.connectDB();

            // check if row exists
            String query = "SELECT * FROM Commision WHERE type = ?";
            PreparedStatement checkStatement = databaseConnection.prepareStatement(query);
            checkStatement.setString(1, type);
            ResultSet checkRs = checkStatement.executeQuery();

            // if row exists, update rate
            if (checkRs.next()) {
                query = "UPDATE Commision SET rate = ? WHERE type = ?";
                PreparedStatement updateStatement = databaseConnection.prepareStatement(query);
                updateStatement.setInt(1, rate);
                updateStatement.setString(2, type);
                updateStatement.executeUpdate();
                updateStatement.close();
            } else { // if row doesn't exist, create new row
                query = "INSERT INTO Commision (type, rate) VALUES (?, ?)";
                PreparedStatement insertStatement = databaseConnection.prepareStatement(query);
                insertStatement.setString(1, type);
                insertStatement.setInt(2, rate);
                insertStatement.executeUpdate();
                insertStatement.close();
            }

            // close database resources
            checkRs.close();
            checkStatement.close();
            databaseConnection.close();

        } catch (SQLException e) {
            System.out.println("Error updating commission rate!");
            e.printStackTrace();
        }
    }

    //getCommissionRowCount method here
    public static int getCommissionRowCount(String type) throws SQLException {
        // SQL query to retrieve count of rows with the specified commission type
        String query = "SELECT COUNT(*) FROM Commision WHERE type = ?";
        int count = 0;

        // establish database connection
        try (Connection databaseConnection = DBConnection.connectDB();
             PreparedStatement statement = databaseConnection.prepareStatement(query)) {
            statement.setString(1, type);

            // execute query
            ResultSet rs = statement.executeQuery();

            // retrieve count of rows
            if (rs.next()) {
                count = rs.getInt(1);
            }

            // close database resources
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error getting commission row count!");
            throw e;
        }

        return count;
    }
    //createCommissionRate method here
    public static void createCommissionRate(String type, int rate) throws SQLException {
        // SQL query to create a new commission rate row
        String query = "INSERT INTO Commision (type, rate) VALUES (?, ?)";

        // establish database connection
        try (Connection databaseConnection = DBConnection.connectDB();
             PreparedStatement statement = databaseConnection.prepareStatement(query)) {
            statement.setString(1, type);
            statement.setInt(2, rate);

            // execute query
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creating commission rate row!");
            throw e;
        }
    }
}