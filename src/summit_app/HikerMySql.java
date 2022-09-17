package summit_app;

import java.sql.SQLException;
import java.util.Scanner;

import static summit_app.SummitController.db;

public class HikerMySql {

        //hiker object

        String first_name = null;
        String last_name = null;
        String email = null;

        Scanner scanner = new Scanner(System.in);
        String input = "";

        public String createHiker(String first_name, String last_name, String email){
            String sql = "INSERT INTO SummitApp.hikers (first_name, last_name, email) VALUES ( '" + first_name + "'" + "," + "'" + last_name + "'" + "," + "'" + email + "')";
            return sql;
        }

        public String deleteHiker(String email){
            String sql = "DELETE FROM SummitApp.hikers WHERE (`email` = " + "'" + email + "'" + ")";
            return sql;
        }

        public String updateHiker(String first_name, String last_name, String email, int id){
            String sql = "UPDATE SummitApp.hikers SET `first_name` = '" + first_name  + "',`last_name` = '" + last_name + "',`email` = '" + email + "' WHERE (id = '" + id + "');";
            return sql;
        }


        public String hikerProfile() throws SQLException {

            System.out.println("1. Create a new user profile.");
            System.out.println("2. Returning user");
            input = scanner.next();

            switch (input) {
                case "1":
                    System.out.println("New User");

                    System.out.println("First Name: ");
                    String first_name = scanner.next();

                    System.out.println("Last Name: ");
                    String last_name = scanner.next();

                    System.out.println("email: ");
                    String email = scanner.next();

                    System.out.println("Great, thank you " +  first_name + "!");
                    System.out.println(" ");

                    String sqlUser = createHiker(first_name, last_name, email);
                    return String.valueOf(db.statement.executeUpdate(sqlUser));

                case "2":
                    System.out.println("Returning User");

                    System.out.println("Name: ");
                    first_name = scanner.next();

                    System.out.println("Hi " +  first_name + "!" + " Welcome back!" + "\n");

                    break;

                default:
                    System.out.println("Please choose between option 1 or 2.");
            }
            return null;
        }

        public String userUpdateProfile() throws SQLException {

            System.out.println("Please insert the USER ID: ");
            int id = scanner.nextInt();
            System.out.println("Thank you");

            db.resultSet = db.statement.executeQuery("Select * From SummitApp.hikers WHERE (id = " + "'" + id + "');");
            while (db.resultSet.next()) {
                String first_name = db.resultSet.getString("first_name");
                String last_name = db.resultSet.getString("last_name");

                // print out the result
                System.out.println("You have selected " + first_name + " " + last_name);
            }
            System.out.println("please update the following values:");
            System.out.println(" ");
            System.out.println("First Name: ");
            String first_name = scanner.next();

            System.out.println("Last Name: ");
            String last_name = scanner.next();

            System.out.println("email: ");
            String email = scanner.next();

            System.out.println("Thank you! The user was updated successfully." + "\n");

            return updateHiker(first_name, last_name, email, id);
        }
    }

