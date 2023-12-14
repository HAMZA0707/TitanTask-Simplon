package titantask;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AffecterTask {
    public int affecterTache(String userEmail, int idTache) {
        ConnectionBaseDonne connectionBaseDonne = new ConnectionBaseDonne();

        try (Connection connection = connectionBaseDonne.connectionBD()) {
            // Check if the user with the provided email exists

            if (getUserByEmail(userEmail , connection)) {
                // Check if the task with the provided ID exists
                if (isTaskExists(idTache, connection)) {
                    // Assign the task to the user
                    Statement statement = connection.createStatement();
                    String query = ("insert into affictation (tache_id , email ) values ('"+idTache+"' , '"+userEmail+"')  ");
                    statement = connection.prepareStatement(query);
                    System.out.println("Task assigned successfully to user with email: " + userEmail);
                    statement.close();
                    return 1;
                } else {
                    System.out.println("Task with ID " + idTache + " not found.");
                    return 0;
                }
            } else {
                System.out.println("User with email " + userEmail + " not found.");
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    //  method to check if the task with the provided ID exists
    private boolean isTaskExists(int taskId, Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM tache WHERE id_tache = '" + taskId + "'");
        return resultSet.next();
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
