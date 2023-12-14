package titantask;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AffecterTask {
	
	public AffecterTask() {}
    public int affecterTache() {
        ConnectionBaseDonne connectionBaseDonne = new ConnectionBaseDonne();
     
        try (Connection connection = connectionBaseDonne.connectionBD()) {
            // Check if the user with the provided email exists
        	Scanner scanner=new Scanner(System.in);
        	System.out.println("Entre le nom de la tache a affecter : ");
            String tache = scanner.next();
            System.out.println("Entre l'email de l'utilisateur a affecter : ");
            String email = scanner.next();
            if (getUserByEmail(email , connection)) {
                // Check if the task with the provided ID exists
                if (isTaskExists(tache, connection)!=-1) {
                    // Assign the task to the user
                    Statement statement = connection.createStatement();
                    String query = ("insert into affictation (tache_id , email ) values ('"+isTaskExists(tache, connection)+"' , '"+email+"')  ");
                    statement.execute(query);
                    System.out.println("Task assigned successfully to user with email: " + email);
                    statement.close();
                    return 1;
                } else {
                    System.out.println("Task with ID " + isTaskExists(tache, connection) + " not found.");
                    return 0;
                }
            } else {
                System.out.println("User with email " + email + " not found.");
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    //  method to check if the task with the provided ID exists
    private int isTaskExists(String taskname, Connection connection) throws SQLException {
    	int i=-1;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT tache_id FROM tache WHERE name = '" + taskname + "'");
        resultSet.next();
        i= resultSet.getInt(1);
        return i;
    }

    // Helper method to retrieve a user by email
    private boolean getUserByEmail(String email, Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM utilisateur WHERE email = '" + email + "'");

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
}
}
