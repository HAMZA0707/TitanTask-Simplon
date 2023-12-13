package titantask;

import titantask.ConnectionBaseDonne;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
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
		String categorie = "" ;
	   try (Connection connection = connectionBaseDonne.connectionBD()) {

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute a query
            ResultSet resultSet = statement.executeQuery("select * from categorie");

            ArrayList<String> categorieList = new ArrayList<>() ;
            // Process the results
            int i=1;
            while (resultSet.next()) {
                // Retrieve data using resultSet.getString() methods
                String column = resultSet.getString("categorie_nom");
                // categories list :
                System.out.println(i+" - "+column);
                i++;
                //add to list
                categorieList.add(column);
            }
            System.out.println("entrez le numero de la categorie ou tappez 0 pour ajoutez une : ");
            
            int numCategorie = scanner.nextInt();
            
            categorie =  categorieList.get(numCategorie-1);
            // Close resources
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    
		
		
		
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
        ConnectionBaseDonne connectionBaseDonne = new ConnectionBaseDonne();

		Scanner scanner = new Scanner(System.in);
		
		System.out.print(" entrez le nom de la tache a supprimer : ");
		String nameSup = scanner.next();
		try (Connection connection = connectionBaseDonne.connectionBD()) {

	        Statement testStatement = connection.createStatement();
	
	        ResultSet verifTache = testStatement.executeQuery("SELECT * FROM `tache` WHERE name = '"+nameSup+"'");
	
			if(verifTache.next()) {
        

	            // Create a statement
	            Statement statement = connection.createStatement();
	
	            // Execute a query
	            int resultSet = statement.executeUpdate("DELETE FROM `tache` WHERE name = '"+nameSup+"'");
	    		System.out.println("delete : "+resultSet);
	            
	            // Close resources
	            statement.close();

			}else {
				System.out.print("ce tache n'existe pas");
			}	
		}catch (SQLException e) {
            e.printStackTrace();
        }
		};
	public void modifier() {
		ConnectionBaseDonne connectionBaseDonne = new ConnectionBaseDonne();

		LocalDateTime localdate = LocalDateTime.now();
        localdate.format(formatter);
		Scanner scanner = new Scanner(System.in);
		
		System.out.print(" entrez le nom de la tache a modifier : ");
		String nameModif = scanner.next();
		try (Connection connection = connectionBaseDonne.connectionBD()) {

	        Statement testStatement = connection.createStatement();
	
	        ResultSet verifTache = testStatement.executeQuery("SELECT * FROM `tache` WHERE name = '"+nameModif+"'");
	
			if(verifTache.next()) {
			
				System.out.println("new name : ");
				String name = scanner.next();
				
				System.out.println("new description : ");
				String description = scanner.next();
				
				System.out.println("new priorite : ");
				String priorite = scanner.next();
				
				System.out.println("new categorie : ");
				String categorie = scanner.next();
				
	
	            // Create a statement
	            Statement statement = connection.createStatement();
	
	            // Execute a query
	            int resultSet = statement.executeUpdate("UPDATE `tache` SET `name`='"+name+"',`description`='"+description+"',`date_de_miseajour`='"+localdate+"',`priorite`='"+priorite+"',`categorie`='"+categorie+"' WHERE name = '"+nameModif+"'");
	    		System.out.println("updated ");
	            
	
	            // Close resources
	            statement.close();
			}
			else {
				System.out.print("ce tache n'existe pas");
			}
		}catch (SQLException e) {
            e.printStackTrace();
        }
	};
	public void afficher() {
		ConnectionBaseDonne connectionBaseDonne = new ConnectionBaseDonne();
        
		try (Connection connection = connectionBaseDonne.connectionBD()) {
		
		    // Create a statement
		    Statement statement = connection.createStatement();
		
		    // Execute a query
		    ResultSet resultSet = statement.executeQuery("select * from tache");
		
		    // Process the results
		    while (resultSet.next()) {
		        // Retrieve data using resultSet.getString() methods
		        String name = resultSet.getString("name");
		        String description = resultSet.getString("description");
		        String priorite = resultSet.getString("priorite");
		        String categorie = resultSet.getString("categorie");
		        String date_de_creation = resultSet.getString("date_de_creation");
		        String date_de_miseajour = resultSet.getString("date_de_miseajour");

		        // categories list :
		        System.out.print("name : "+name+" description : "+description+" priorite : "+priorite+" categorie : "+categorie+" date_de_creation : "+date_de_creation+" date_de_miseajour : "+date_de_miseajour+"\n");
		    }
		
		    // Close resources
		    resultSet.close();
		    statement.close();
		
		} catch (SQLException e) {
		    e.printStackTrace();
		}
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
