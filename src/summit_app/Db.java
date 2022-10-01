package summit_app;

import java.sql.*;

class Db {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;


    public void openDB(){
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            String connectionString = "jdbc:mysql://localhost:3306/SummitApp?"
                    + "user=<USER>&password=<PASSWORD>"
                    + "&useSSL=false&allowPublicKeyRetrieval=true";

            // Setup the connection with the DB
            connection = DriverManager.getConnection(connectionString);

            // Statements allow to issue SQL queries to the database
            statement = connection.createStatement();

        } catch (SQLException exc) {
            System.out.println("Exception occurred");
            exc.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Exception occured - driver not found on classpath");
            e.printStackTrace();
        }
    }

    public void closeDB(){
        try {
            // close all JDBC elements
            statement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
