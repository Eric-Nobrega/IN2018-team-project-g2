package dbfuncs;

import interfaces.Customer;
import interfaces.ExchangeRate;
import interfaces.TravelAgent;
import interfaces.User;
import java.sql.*;
import java.time.LocalDate;

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

            System.out.println(userID);

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
        String query = "INSERT INTO USER (UserName, PASSWORD, Role) VALUES (?, ?, ?)";

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, role);
         //   statement.setString(4, name);

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
        String query = "INSERT INTO `Travel Agent` (FullName, Address, PhoneNumber) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE Address = VALUES(Address), PhoneNumber = VALUES(PhoneNumber)";

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            statement.setString(1, fullname);
            statement.setString(2, address);
            statement.setString(3, phonenumber);

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

    public static ResultSet getAllTravelAgents(){
        String sql2 = "SELECT * FROM `Travel Agent`";

        // establish database connection
        Connection databaseConnection = connectToDB();

        try {
            // create prepared statement for SQL query
            PreparedStatement stmt2 = databaseConnection.prepareStatement(sql2);
            ResultSet rs2 = stmt2.executeQuery();

            return rs2;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getAllBlanksCodes(){
        String sql2 = "SELECT * FROM BlankCodes";

        // establish database connection
        Connection databaseConnection = connectToDB();

        try {
            // create prepared statement for SQL query
            PreparedStatement stmt2 = databaseConnection.prepareStatement(sql2);
            ResultSet rs2 = stmt2.executeQuery();

            return rs2;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getAllCustomers(){
        String sql2 = "SELECT * FROM Customer";

        // establish database connection
        Connection databaseConnection = connectToDB();

        try {
            // create prepared statement for SQL query
            PreparedStatement stmt2 = databaseConnection.prepareStatement(sql2);
            ResultSet rs2 = stmt2.executeQuery();

            return rs2;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getAllTravelAdvisors(){
        String sql2 = "SELECT * FROM `Travel Advisor`";

        // establish database connection
        Connection databaseConnection = connectToDB();

        try {
            // create prepared statement for SQL query
            PreparedStatement stmt2 = databaseConnection.prepareStatement(sql2);
            ResultSet rs2 = stmt2.executeQuery();

            return rs2;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getTravelAgentIDByName(String travelAgentName){
        String sql = "SELECT `AgentID` FROM `Travel Agent` WHERE `FullName` = ?";

        // establish database connection
        Connection databaseConnection = connectToDB();

        try {
            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(sql);
            statement.setString(1, travelAgentName);
            ResultSet resultSet = statement.executeQuery();

            // get the ID value from the ResultSet and return it
            if (resultSet.next()) {
                int travelAgentID = resultSet.getInt("AgentID");
                return travelAgentID;
            } else {
                return -1; // return -1 if the travel agent name was not found
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // return -1 in case of any exception
        } finally {
            // close the database resources
            try {
                if (databaseConnection != null) {
                    databaseConnection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ResultSet getBlanksByAdvisorID() {
        String sql2 = "SELECT Type, COUNT(*) AS qty FROM BlanksTB WHERE `Travel AdvisorAdvisorId` = 1 GROUP BY Type;";

        // establish database connection
        Connection databaseConnection = connectToDB();

        try {
            // create prepared statement for SQL query
            PreparedStatement stmt2 = databaseConnection.prepareStatement(sql2);
            ResultSet rs2 = stmt2.executeQuery();

            return rs2;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getFixedRate() {
        // SQL query to retrieve fixed commission rate from the commission table
        String query = "SELECT rate FROM Discount WHERE type = 'fixed'";

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
        String query = "SELECT rate FROM Discount WHERE type = 'variable'";

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

    public static void updateDiscountRate(String type, int rate) {

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // check if row exists
            String query = "SELECT * FROM Discount WHERE type = ?";
            PreparedStatement checkStatement = databaseConnection.prepareStatement(query);
            checkStatement.setString(1, type);
            ResultSet checkRs = checkStatement.executeQuery();

            // if row exists, update rate
            if (checkRs.next()) {
                query = "UPDATE Discount SET rate = ? WHERE type = ?";
                PreparedStatement updateStatement = databaseConnection.prepareStatement(query);
                updateStatement.setInt(1, rate);
                updateStatement.setString(2, type);
                updateStatement.executeUpdate();
                updateStatement.close();
            } else { // if row doesn't exist, create new row
                query = "INSERT INTO Discount (type, rate) VALUES (?, ?)";
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

    public static int getDiscountRowCount(String type) throws SQLException {
        // SQL query to retrieve count of rows with the specified commission type
        String query = "SELECT COUNT(*) FROM Discount WHERE type = ?";
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

    public static void createDiscountRate(String type, int rate) throws SQLException {
        // SQL query to create a new commission rate row
        String query = "INSERT INTO Discount (type, rate) VALUES (?, ?)";

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
            if (rs.next()) {

                int customerId = rs.getInt("CustomerId");
                String customerName = rs.getString("CustomerName");
                int customerPhoneNumber = rs.getInt("CustomerphoneNumber");
                String customerEmail = rs.getString("CustomerEmail");
                String cardNumber = rs.getString("CardNumber");
                int cardCVV = rs.getInt("CVV");
                String customerRelationship = rs.getString("Relationship");
                int travelAgentID = rs.getInt("Travel AgentAgentID");

                customer = new Customer(customerId, customerName, customerPhoneNumber, customerEmail, cardNumber, cardCVV, customerRelationship, travelAgentID);
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

    public static void updateExchangeRate(String currencyName, int newRate) {
        // SQL query to update or insert exchange rate
        String querySelect = "SELECT COUNT(*) FROM ExchangeRate WHERE CurrencyName = ?";
        String queryUpdate = "UPDATE ExchangeRate SET AmountUSD = ? WHERE CurrencyName = ?";
        String queryInsert = "INSERT INTO ExchangeRate (CurrencyName, AmountUSD) VALUES (?, ?)";

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // create prepared statement for select query
            PreparedStatement statementSelect = databaseConnection.prepareStatement(querySelect);
            statementSelect.setString(1, currencyName);

            // execute select query
            ResultSet rs = statementSelect.executeQuery();
            rs.next();
            int rowCount = rs.getInt(1);

            // close select statement and result set
            rs.close();
            statementSelect.close();

            // create prepared statement for update or insert query
            PreparedStatement statement;
            if (rowCount > 0) {
                statement = databaseConnection.prepareStatement(queryUpdate);
                statement.setDouble(1, newRate);
                statement.setString(2, currencyName);
            } else {
                statement = databaseConnection.prepareStatement(queryInsert);
                statement.setString(1, currencyName);
                statement.setDouble(2, newRate);
            }

            // execute update or insert query
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

    public static boolean modifyCommissionRate(int newCommissionRate) {
        try (Connection connection = connectToDB();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Commision (Rate, DateSet) VALUES (?, ?)")) {
            statement.setInt(1, newCommissionRate);
            statement.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addBlanks(String blankType, String dateReceived, int amountReceived, int agentID) {

        // SQL query to insert new blank
        String query = "INSERT INTO BlanksTB (SerialNumber, TYPE, isValid, isVoid, isAssigned, DateReceived, TravelAgentID) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            // establish database connection
            Connection databaseConnection = connectToDB();

            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);

            // query the database to get the highest serial number for the given blank type
            String maxSerialNumberQuery = "SELECT MAX(SUBSTR(SerialNumber, LENGTH(?)+1)) AS max_serial_number FROM BlanksTB WHERE TYPE = ?";
            PreparedStatement maxSerialNumberStatement = databaseConnection.prepareStatement(maxSerialNumberQuery);
            maxSerialNumberStatement.setString(1, blankType);
            maxSerialNumberStatement.setString(2, blankType);
            ResultSet rs = maxSerialNumberStatement.executeQuery();

            // generate and set serial number for each blank
            int sequenceNumber;
            if (rs.next() && rs.getString("max_serial_number") != null) {
                sequenceNumber = Integer.parseInt(rs.getString("max_serial_number")) + 1;
            } else {
                sequenceNumber = 1;
            }
            for (int i = 0; i < amountReceived; i++) {
                String serialNumber = String.format("%s%08d", blankType, sequenceNumber);
                statement.setString(1, serialNumber);
                statement.setString(2, blankType);
                statement.setString(3, String.valueOf(false));
                statement.setString(4, String.valueOf(false));
                statement.setString(5, String.valueOf(false));
                statement.setString(6, dateReceived);
                statement.setString(7, String.valueOf(agentID));
                statement.addBatch();
                sequenceNumber++;
            }

            // execute batch insert
            int[] rowsInserted = statement.executeBatch();

            // close resources
            rs.close();
            maxSerialNumberStatement.close();
            statement.close();
            databaseConnection.close();

            return rowsInserted.length > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean removeBlanks(String blankCode, int amountToRemove, int travelAgentId) {
        String query = "DELETE FROM BlanksTB WHERE TYPE = ? AND `TravelAgentId` = ? AND isAssigned = ? LIMIT ?";

        try {
            Connection databaseConnection = connectToDB();
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            statement.setString(1, blankCode);
            statement.setInt(2, travelAgentId);
            statement.setBoolean(3, false);
            statement.setInt(4, amountToRemove);

            int rowsDeleted = statement.executeUpdate();
            statement.close();
            databaseConnection.close();

            if (rowsDeleted >= amountToRemove) {
                return true;
            } else {
                System.out.println("Error: Not enough blank rows to delete.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

        public static ResultSet getAllBlanks() {

        // SQL query to retrieve all user names
        String query = "SELECT * FROM BlanksTB";

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
        String sql = "SELECT BlankType, COUNT(*) AS Quantity FROM Blank WHERE BlankType = ? GROUP BY BlankType";
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
    public static void assignBlanks(String blankType, String advisorName, int amount) {
        try {
            // Connect to the database
            Connection conn = connectToDB();

            // Get the ID of the selected travel advisor
            String[] names = advisorName.split(" ");
            String advisorFirstName = names[0];
            String advisorLastName = names[1];

            System.out.println(advisorFirstName);
            System.out.println(advisorLastName);

            String getAdvisorIdQuery = "SELECT AdvisorId FROM `Travel Advisor` WHERE AdvisorFirstName=? AND AdvisorLastName=?";
            PreparedStatement getAdvisorIdStmt = conn.prepareStatement(getAdvisorIdQuery);
            getAdvisorIdStmt.setString(1, advisorFirstName);
            getAdvisorIdStmt.setString(2, advisorLastName);
            ResultSet advisorIdRs = getAdvisorIdStmt.executeQuery();
            advisorIdRs.next();
            int advisorId = advisorIdRs.getInt("AdvisorId");

            // Select the required number of blank rows from the BlanksTB table
            String selectBlanksQuery = "SELECT SerialNumber FROM BlanksTB WHERE TYPE=? AND isAssigned=0 LIMIT ?";
            PreparedStatement selectBlanksStmt = conn.prepareStatement(selectBlanksQuery);
            selectBlanksStmt.setString(1, blankType);
            selectBlanksStmt.setInt(2, amount);
            ResultSet blankSerialNumbers = selectBlanksStmt.executeQuery();

            // Update the Travel Advisor table with the ID of the selected travel advisor
            String updateTravelAdvisorQuery = "UPDATE `Travel Advisor` SET AdvisorId=? WHERE AdvisorFirstName=? AND AdvisorLastName=?";
            PreparedStatement updateTravelAdvisorStmt = conn.prepareStatement(updateTravelAdvisorQuery);
            updateTravelAdvisorStmt.setInt(1, advisorId);
            updateTravelAdvisorStmt.setString(2, advisorFirstName);
            updateTravelAdvisorStmt.setString(3, advisorLastName);
            updateTravelAdvisorStmt.executeUpdate();

            // Mark the selected blanks as assigned
            String markBlanksAssignedQuery = "UPDATE BlanksTB SET isAssigned=1, `Travel AdvisorAdvisorId`=? WHERE SerialNumber=?";
            PreparedStatement markBlanksAssignedStmt = conn.prepareStatement(markBlanksAssignedQuery);
            while (blankSerialNumbers.next()) {
                String blankSerialNumber = blankSerialNumbers.getString("SerialNumber");
                markBlanksAssignedStmt.setInt(1, advisorId);
                markBlanksAssignedStmt.setString(2, blankSerialNumber);
                markBlanksAssignedStmt.executeUpdate();
            }

            // Close the database connection and statements
            blankSerialNumbers.close();
            getAdvisorIdStmt.close();
            selectBlanksStmt.close();
            updateTravelAdvisorStmt.close();
            markBlanksAssignedStmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getTravelAdvisorID(){
        String query = "SELECT TravelAdvisorId FROM USER WHERE UserId = ?";

        // establish database connection
        Connection databaseConnection = connectToDB();

        try {
            // create prepared statement for SQL query
            PreparedStatement statement = databaseConnection.prepareStatement(query);
            statement.setString(1, String.valueOf(userID));
            ResultSet resultSet = statement.executeQuery();

            // get the ID value from the ResultSet and return it
            if (resultSet.next()) {
                int travelAgentID = resultSet.getInt("TravelAdvisorId");
                return travelAgentID;
            } else {
                return -1; // return -1 if the travel agent name was not found
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // return -1 in case of any exception
        }
    }

    public static void recordSale(String customerName, String customerAddress, String customerEmail, String customerPhoneNumber, String date, String destination, String payNowOrLater, String paymentMethod, String currencyName, int priceAmount, String cardNumber, String cardCVV, String discountGiven) {

        try {
            // Open a connection to the database
            Connection conn = connectToDB();

            // Insert data into the customer table
            String customerSql = "INSERT INTO Customer (CustomerName, Address, CustomerphoneNumber, CustomerEmail, CardNumber, CVV) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement customerStmt = conn.prepareStatement(customerSql, Statement.RETURN_GENERATED_KEYS);
            customerStmt.setString(1, customerName);
            customerStmt.setString(2, customerAddress);
            customerStmt.setString(3, customerPhoneNumber);
            customerStmt.setString(4, customerEmail);
            customerStmt.setString(5, cardNumber);
            customerStmt.setString(6, cardCVV);
            int customerId = -1;
            int affectedRows = customerStmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Insert into Customer table failed, no rows affected.");
            }
            try (ResultSet generatedKeys = customerStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customerId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Insert into Customer table failed, no ID obtained.");
                }
            }

            // Insert data into the sale table
            String saleSql = "INSERT INTO Sale (CustomerName, isPayNow, Amount, isCash, isCard, isDomestic, isInterline, Currency, Date, DiscountApplied, `Travel AdvisorAdvisorId`,`Customer_CustomerId`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?)";
            PreparedStatement saleStmt = conn.prepareStatement(saleSql);
            saleStmt.setString(1, customerName);
            saleStmt.setString(2, payNowOrLater);
            saleStmt.setInt(3, priceAmount);
            if (paymentMethod.equalsIgnoreCase("cash")) {
                saleStmt.setInt(4, 1);
                saleStmt.setInt(5, 0);
            } else {
                saleStmt.setInt(4, 0);
                saleStmt.setInt(5, 1);
            }
            if (destination.equalsIgnoreCase("domestic")) {
                saleStmt.setInt(6,1);
                saleStmt.setInt(7, 0);
            } else {
                saleStmt.setInt(6, 0);
                saleStmt.setInt(7, 1);
            }
            saleStmt.setString(8, currencyName);
            saleStmt.setString(9, date);
            saleStmt.setString(10,discountGiven);
            saleStmt.setString(11, String.valueOf(getTravelAdvisorID()));
            saleStmt.setString(12, String.valueOf(customerId));
            saleStmt.executeUpdate();

            // Close the database connection
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addBlankCode(String newBlankCode) throws SQLException {
        String query = "SELECT * FROM BlankCodes WHERE BlankCode = ?";
        PreparedStatement statement = connectToDB().prepareStatement(query);
        statement.setString(1, newBlankCode);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            // The blank code already exists, so return an error
            throw new SQLException("Blank code already exists");
        } else {
            // The blank code doesn't exist, so add a new row to the table
            String insertQuery = "INSERT INTO BlankCodes (BlankCode) VALUES (?)";
            PreparedStatement insertStatement = connectToDB().prepareStatement(insertQuery);
            insertStatement.setString(1, newBlankCode);
            insertStatement.executeUpdate();
    };

 //   public static void removeBlankCode(){};
//    public static ResultSet getAllBlankCodes(){};
}

    public static void removeBlankCode(String codeToRemove) {
        // create a connection to the database
        Connection connection = connectToDB();

        try {
            // check if the code exists in the table
            String query = "SELECT * FROM BlankCodes WHERE BlankCode = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, codeToRemove);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // if the code exists, remove the corresponding row from the table
                query = "DELETE FROM BlankCodes WHERE BlankCode = ?";
                statement = connection.prepareStatement(query);
                statement.setString(1, codeToRemove);
                statement.executeUpdate();
                System.out.println("Code removed successfully");
            } else {
                // if the code doesn't exist, return an error message
                System.out.println("Error: code not found");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}