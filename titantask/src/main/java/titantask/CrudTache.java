package titantask;

import titantask.ConnectionBaseDonne;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CrudTache implements ICrudtache{
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public void ajouter() {
        LocalDateTime localdate = LocalDateTime.now();
        localdate.format(formatter);
		
        ConnectionBaseDonne connectionBaseDonne = new ConnectionBaseDonne();

		Scanner scanner = new Scanner(System.in);
		

		System.out.print("name : ");
		String name = scanner.next();
		
		System.out.print("description : ");
		String description = scanner.next();
		
		System.out.print("priorite : ");
		String priorite = scanner.next();
		

		System.out.print("liste des categories existantes :\n  ");
		
	   /* try (Connection connection = connectionBaseDonne.connectionBD()) {

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute a query
            ResultSet resultSet = statement.executeQuery("select * from categorie");

            // Process the results
            while (resultSet.next()) {
                // Retrieve data using resultSet.getString() methods
                String column1 = resultSet.getString("column1");
                // categories list :
                System.out.print(column1);
            }

            // Close resources
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    
		String categorie = scanner.next();
		
		
		try (Connection connection = connectionBaseDonne.connectionBD()) {

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute a query
            int resultSet = statement.executeUpdate("INSERT INTO `tache`( `name`, `description`, `date_de_creation`, `date_de_miseajour`, `priorite`, `categorie`) VALUES ('"+name+"','"+description+"','"+localdate+"','"+localdate+"','"+priorite+"','"+categorie+"')");
    		System.out.println("add : "+resultSet);
            

            // Close resources
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	};
	public void supprimer() {
		
	};
	public void modifier() {
		
	};
	public void afficher() {
		
	};
	public void filtrerCategorie(String nom_Categorie) {
		
	};
	public void tri(int choix) {
		
	};
	
	
	public static void main(String[] args) {
		
		CrudTache test = new CrudTache();
		test.ajouter();
		
	}
}
