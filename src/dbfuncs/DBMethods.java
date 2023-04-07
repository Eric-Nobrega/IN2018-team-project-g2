package dbfuncs;

import interfaces.TravelAgent;
import interfaces.User;
import java.sql.*;

public class DBMethods {
    public static Connection connectToDB() {

        // database url
        String url = "jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g02";

        // admin user, with DDL statement rights
        String username = "in2018g02_a";
        String password = "GoGn45dL";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean authenticate(String username, String password) {

        // SQL query to check user credentials
        String query = "SELECT * FROM USER WHERE UserName = ? AND PASSWORD = ?";

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

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
            Connection databaseConnection = connectToDB();

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

    public static boolean addUser(String username, String password, String role, String name) {

        // SQL query to insert new user
        String query = "INSERT INTO USER (UserName, PASSWORD, Role, Name) VALUES (?, ?, ?, ?)";

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, role);
            statement.setString(4, name);

            // execute query
            int rowsInserted = statement.executeUpdate();

            // close database resources
            statement.close();
            databaseConnection.close();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ResultSet getAllUsers() {

        // SQL query to retrieve all user names
        String query = "SELECT * FROM USER";

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);

            // execute query
            ResultSet rs = statement.executeQuery();

            return rs;
        } catch (SQLException e) {
            System.out.println("Error getting all users!");
            e.printStackTrace();
            return null;
        }
    }

    public static boolean deleteUser(String username) {

        // SQL query to delete user
        String query = "DELETE FROM USER WHERE UserName = ?";

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            statement.setString(1, username);

            // execute query
            int rowsDeleted = statement.executeUpdate();

            // close database resources
            statement.close();
            databaseConnection.close();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User getUserByUsername(String username) {
        // SQL query to retrieve user by username
        String query = "SELECT * FROM USER WHERE UserName = ?";

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            statement.setString(1, username);

            // execute query
            ResultSet rs = statement.executeQuery();

            // retrieve user details
            User user = null;
            if (rs.next()) {
                String role = rs.getString("ROLE");
                String name = rs.getString("Name");
                user = new User(username, role, name);
            }

            // close database resources
            rs.close();
            statement.close();
            databaseConnection.close();

            return user;
        } catch (SQLException e) {
            System.out.println("Error getting user by username!");
            e.printStackTrace();
            return null;
        }
    }

    public static boolean updateUser(String username, String password, String name) {

        // SQL query to update user details
        String query = "UPDATE USER SET PASSWORD = ?, Name = ? WHERE UserName = ?";

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            statement.setString(1, password);
            statement.setString(2, name);
            statement.setString(3, username);

            // execute query
            int rowsUpdated = statement.executeUpdate();

            // close database resources
            statement.close();
            databaseConnection.close();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static TravelAgent getTravelAgentByFullName(String fullname) {
        // SQL query to retrieve user by username
        String query = "SELECT * FROM `Travel Agent` WHERE FullName = ?";

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            statement.setString(1, fullname);

            // execute query
            ResultSet rs = statement.executeQuery();

            // retrieve user details
            TravelAgent travelAgent = null;
            if (rs.next()) {
                String fullname1 = rs.getString("Fullname");
                String address = rs.getString("Address");
                String phonenumber = rs.getString("Phonenumber");
                travelAgent = new TravelAgent(fullname1, address, phonenumber);
            }

            // close database resources
            rs.close();
            statement.close();
            databaseConnection.close();

            return travelAgent;
        } catch (SQLException e) {
            System.out.println("Error getting travel agent by full name!");
            e.printStackTrace();
            return null;
        }
    }

    public static boolean updateTravelAgent(String fullname, String address, String phonenumber) {

        String query = "UPDATE `Travel Agent`  SET FullName = ?, Address = ?, PhoneNumber = ? WHERE FullName = ?";

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            statement.setString(1, fullname);
            statement.setString(2, address);
            statement.setString(3, phonenumber);
            statement.setString(4, fullname);

            // execute query
            int rowsUpdated = statement.executeUpdate();

            // close database resources
            statement.close();
            databaseConnection.close();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}