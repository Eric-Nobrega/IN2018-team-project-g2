package dbfuncs;

import interfaces.Customer;
import interfaces.ExchangeRate;
import interfaces.TravelAgent;
import interfaces.User;
import java.sql.*;

public class DBMethods {
    public static int userID;

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
        String query = "SELECT UserID FROM USER WHERE UserName = ? AND PASSWORD = ?";
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
            boolean success = false;
            if (rs.next()) {
                userID = rs.getInt("UserID");
                success = true;
            }

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

    public static ResultSet getBlanksByAdvisorID(int advisorID) {
        String sql = "SELECT * FROM Blanks WHERE `Travel AdvisorAdvisorId` = ?";
        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // create prepared statement for SQL query
            PreparedStatement stmt = databaseConnection.prepareStatement(sql);
            stmt.setInt(1, advisorID);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getFixedRate() {
        // SQL query to retrieve fixed commission rate from the commission table
        String query = "SELECT rate FROM Commision WHERE type = 'fixed'";

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

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
            Connection databaseConnection = connectToDB();

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
            Connection databaseConnection = connectToDB();

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

    public static int getCommissionRowCount(String type) throws SQLException {
        // SQL query to retrieve count of rows with the specified commission type
        String query = "SELECT COUNT(*) FROM Commision WHERE type = ?";
        int count = 0;

        // establish database connection
        try (Connection databaseConnection = connectToDB();
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

    public static void createCommissionRate(String type, int rate) throws SQLException {
        // SQL query to create a new commission rate row
        String query = "INSERT INTO Commision (type, rate) VALUES (?, ?)";

        // establish database connection
        try (Connection databaseConnection = connectToDB();
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

    public static Customer getCustomerByName(String fullName) {
        // SQL query to retrieve customer by name
        String query = "SELECT * FROM Customer WHERE CustomerName = ?";

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            statement.setString(1, fullName);

            // execute query
            ResultSet rs = statement.executeQuery();

            // retrieve customer details
            Customer customer = null;
            // public Customer(int id, String customerName, int customerPhoneNumber,  String customerEmail, String customerPaymentInfo,  String customerRelationship) {
            if (rs.next()) {
                int customerId = rs.getInt("CustomerId");
                String fName = rs.getString("CustomerName");
                int phone = rs.getInt("CustomerphoneNumber");
                String email = rs.getString("CustomerEmail");
                String payment = rs.getString("PaymentInfo");
                String relationship = rs.getString("Relationship");

                customer = new Customer(customerId, fName, phone, email, payment, relationship);
            }

            // close database resources
            rs.close();
            statement.close();
            databaseConnection.close();

            return customer;
        } catch (SQLException e) {
            System.out.println("Error getting customer by name!");
            e.printStackTrace();
            return null;
        }
    }

    public static boolean updateCustomerRelationship(String fullName, String relationship) {

        String query = "UPDATE `Customer` SET `Relationship` = ? WHERE `CustomerName` = ?";

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            statement.setString(1, relationship);
            statement.setString(2, fullName);

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

    public static ResultSet getAllExchangeRates() {

        // SQL query to retrieve all user names
        String query = "SELECT * FROM ExchangeRate";

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);

            // execute query
            ResultSet rs = statement.executeQuery();

            return rs;
        } catch (SQLException e) {
            System.out.println("Error getting all exchange rates!");
            e.printStackTrace();
            return null;
        }
    }

    public static ExchangeRate getExchangeRate(String currencyName) {
        // SQL query to retrieve customer by name
        String query = "SELECT * FROM ExchangeRate WHERE CurrencyName = ?";

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            statement.setString(1, currencyName);

            // execute query
            ResultSet rs = statement.executeQuery();

            // retrieve customer details
            ExchangeRate exchangeRate = null;
            //     public ExchangeRate(int Amount, String CurrencyName, int amountUSD) {
            if (rs.next()) {
                int Amount = rs.getInt("Amount");
                String CurrencyName = rs.getString("CurrencyName");
                int amountUSD = rs.getInt("amountUSD");
                exchangeRate = new ExchangeRate(Amount, CurrencyName, amountUSD);
            }

            // close database resources
            rs.close();
            statement.close();
            databaseConnection.close();

            return exchangeRate;
        } catch (SQLException e) {
            System.out.println("Error getting customer by name!");
            e.printStackTrace();
            return null;
        }
    }

    public static void updateExchangeRate(String currencyName, double newRate) {
        // SQL query to update exchange rate
        String query = "UPDATE ExchangeRate SET AmountUSD = ? WHERE CurrencyName = ?";

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            statement.setDouble(1, newRate);
            statement.setString(2, currencyName);

            // execute update query
            statement.executeUpdate();

            // close database resources
            statement.close();
            databaseConnection.close();

            System.out.println("Exchange rate updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating exchange rate!");
            e.printStackTrace();
        }
    }

    public static boolean applyDiscount(String customerName, String fixedDiscount, String flexibleDiscount) {
        String query = "UPDATE Customer SET FixedDiscount=?, FlexibleDiscount=? WHERE CustomerName=?";
        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // prepare the update statement
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            statement.setString(1, fixedDiscount);
            statement.setString(2, flexibleDiscount);
            statement.setString(3, customerName);

            // execute the update and return true if successful
            int rowsAffected = statement.executeUpdate();

            // close database resources
            statement.close();
            databaseConnection.close();

            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean addBlanks(String blankType, String dateReceived){

        // SQL query to insert new user
        String query = "INSERT INTO Blanks (SerialNumber, BlankType, isValid, isVoid, isAssigned, DateReceived) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            statement.setString(1, String.valueOf(1));
            statement.setString(2, blankType);
            statement.setString(3, String.valueOf(false));
            statement.setString(4, String.valueOf(false));
            statement.setString(5, String.valueOf(false));
            statement.setString(6,  dateReceived);

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
    };

    public static ResultSet getAllBlanks() {

        // SQL query to retrieve all user names
        String query = "SELECT * FROM Blanks";

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);

            // execute query
            ResultSet rs = statement.executeQuery();

            return rs;
        } catch (SQLException e) {
            System.out.println("Error getting all exchange rates!");
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getBlanksByType(String blankType) {
        String sql = "SELECT BlankType, COUNT(*) AS Quantity FROM Blanks WHERE BlankType = ? GROUP BY BlankType";
        try {
            Connection databaseConnection = connectToDB();
            PreparedStatement statement = databaseConnection.prepareStatement(sql);
            statement.setString(1, blankType);
            ResultSet rs = statement.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("Error retrieving blanks from database!");
            e.printStackTrace();
            return null;
        }
    }
}