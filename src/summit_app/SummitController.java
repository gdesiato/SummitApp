package summit_app;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SummitController {

    static Db db;

    public static void main(String[] args) throws SQLException {

        System.out.println("WELCOME TO SUMMIT" + "\n");
        System.out.println("-----------");
        System.out.println("Press ENTER");
        System.out.println("-----------");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Trail trail = new Trail();

        db = new Db();
        db.openDB();
        HikerMySql hikerSql = new HikerMySql();
        TrailMySql trailSql = new TrailMySql();

        //create user or returning user
        hikerSql.hikerProfile();

        String menu1 = "";
        boolean displayMenu = true;
        //MAIN MENU
        do {
            System.out.println("//////////");
            System.out.println("MAIN MENU:");
            System.out.println("//////////");

            System.out.println("1. explore trails");
            System.out.println("2. add a trail");
            System.out.println("3. update trail info");
            System.out.println("4. update hiker profile");
            System.out.println("5. quit");

            System.out.println("Please selece a number. (1-5)" + "\n");
            menu1 = scanner.next();

            switch(menu1) {
                case "1":
                    System.out.println("1. explore trails" + "\n");
                    System.out.println("Thank you!" + "\n");
                    System.out.println("Now, let's narrow down your preferences for today's hike." + "\n");

                    boolean trailMenu = true;
                    while (trailMenu) {
                        System.out.println("------------");
                        System.out.println("TRAILS MENU:");
                        System.out.println("------------");
                        System.out.println("Difficulty: 1 (easy) / 2 (moderate) / 3 (hard)");
                        trail.difficulty = scanner.next().toLowerCase();

                        if (!(trail.difficulty.equals("1") || trail.difficulty.equals("2") || trail.difficulty.equals("3"))) {
                            System.out.println("Wrong answer. Please type easy, moderate or hard.");

                            //easy trail selection
                        } else if (trail.isEasy()) {
                            int counter = 1;
                            db.resultSet = db.statement.executeQuery("Select * From SummitApp.trails WHERE (`trail_difficulty` = " + "'easy'" + ")");
                            while (db.resultSet.next()) {

                                // get the id, names fields from the result set and assign them to local variables
                                int trail_id = db.resultSet.getInt("trail_id");
                                String trail_name = db.resultSet.getString("trail_name");
                                double trail_miles = db.resultSet.getDouble("trail_miles");
                                String trail_difficulty = db.resultSet.getString("trail_difficulty");

                                // print out the result
                                System.out.println("Trail " + trail_id + ": " + trail_name + " -- " + trail_miles + " miles -- " + trail_difficulty);
                            }

                            System.out.println("");
                            System.out.println("Please select a trail_id number: ");
                            int trail_id_Num = scanner.nextInt();
                            System.out.println(" ");
                            System.out.println("You have selected: ");

                            trailSql.selectTrail(trail_id_Num);

                            System.out.println(" ");
                            System.out.println("Do you want to select another trail?");
                            System.out.println("y/n");
                            String answ = scanner.next();
                            if (answ.equals("n")){
                                trailMenu = false;
                            } else {
                                trailMenu = true;
                            }

                            //moderate trail selection
                        } else if (trail.isModerate()) {
                            int counter = 1;
                            db.resultSet = db.statement.executeQuery("Select * From SummitApp.trails WHERE (`trail_difficulty` = " + "'moderate'" + ")");
                            while (db.resultSet.next()) {

                                // get the id, names fields from the result set and assign them to local variables
                                int trail_id = db.resultSet.getInt("trail_id");
                                String trail_name = db.resultSet.getString("trail_name");
                                double trail_miles = db.resultSet.getDouble("trail_miles");
                                String trail_difficulty = db.resultSet.getString("trail_difficulty");

                                // print out the result
                                System.out.println("Trail " + trail_id + ": " + trail_name + " -- " + trail_miles + " miles -- " + trail_difficulty);
                            }

                            System.out.println(" ");
                            System.out.println("Please select a trail number: ");
                            int trail_id_Num = scanner.nextInt();
                            System.out.println(" ");
                            System.out.println("You have selected: ");

                            trailSql.selectTrail(trail_id_Num);

                            System.out.println(" ");
                            System.out.println("Do you want to select another trail?");
                            System.out.println("y/n");
                            String answ = scanner.next();
                            if (answ.equals("n")){
                                trailMenu = false;
                            } else {
                                trailMenu = true;
                            }

                            //hard trail selection
                        } else {
                            int counter = 1;
                            db.resultSet = db.statement.executeQuery("Select * From SummitApp.trails WHERE (`trail_difficulty` = " + "'hard'" + ")");
                            while (db.resultSet.next()) {

                                // get the id, names fields from the result set and assign them to local variables
                                int trail_id = db.resultSet.getInt("trail_id");
                                String trail_name = db.resultSet.getString("trail_name");
                                double trail_miles = db.resultSet.getDouble("trail_miles");
                                String trail_difficulty = db.resultSet.getString("trail_difficulty");

                                // print out the result
                                System.out.println("Trail " + trail_id + ": " + trail_name + " -- " + trail_miles + " miles -- " + trail_difficulty);
                            }

                            System.out.println("");
                            System.out.println("Please select a trail number: ");
                            int trail_id_Num = scanner.nextInt();
                            System.out.println(" ");
                            System.out.println("You have selected: ");

                            trailSql.selectTrail(trail_id_Num);

                            System.out.println(" ");
                            System.out.println("Do you want to select another trail?");
                            System.out.println("y/n");
                            String answ = scanner.next();
                            if (answ.equals("n")){
                                trailMenu = false;
                            } else {
                                trailMenu = true;
                            }
                        }
                    }
                    break;

                case "2":
                    System.out.println("2. add a trail");
                    String sql = trailSql.userCreateTrail();
                    db.statement.executeUpdate(sql);

                    break;
                case "3":
                    System.out.println("3. update trail info");
                    trailSql.userUpdateTrail();

                    break;
                case "4":
                    System.out.println("4. update hiker profile");
                    String sqlUpH = hikerSql.userUpdateProfile();
                    db.statement.executeUpdate(sqlUpH);

                    break;
                case "5":
                    System.out.println("5. quit");
                    displayMenu = false;
                    break;
                default:
                    System.out.println("Wrong input. Please select a number from 1 to 5");

            }
        } while(displayMenu);

        db.closeDB();
    }
}

