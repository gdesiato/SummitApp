package summit_app;

import java.sql.*;

class Db {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public Db() {

        TrailMySql trailMysql = new TrailMySql();


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
//
//            String sql = trailMysql.updateTrail(0, 8);
//            statement.executeUpdate(sql);
//
//            // Result set get the result of the SQL query
//            resultSet = statement.executeQuery("select * from SummitApp.trails");
//
//            // loop through the result set while there are more records
//            while (resultSet.next()) {
//
//                // get the id, names fields from the result set and assign them to local variables
//                int trail_id = resultSet.getInt("trail_id");
//                String trail_name = resultSet.getString("trail_name");
//                double trail_miles = resultSet.getDouble("trail_miles");
//                String trail_difficulty = resultSet.getString("trail_difficulty");
//
//                // print out the result
//                System.out.println("Trail " + trail_id + ": " + trail_name + " -- " + trail_miles + " miles -- " + trail_difficulty);
//            }

        } catch (SQLException exc) {
            System.out.println("Exception occurred");
            exc.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Exception occured - driver not found on classpath");
            e.printStackTrace();
        }
//        finally {
//            try {
//                // close all JDBC elements
//                statement.close();
//                resultSet.close();
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }
}

