package titantask;


import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.google.protobuf.Timestamp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CrudTache implements ICrudtache{
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	Scanner scanner = new Scanner(System.in);
    ConnectionBaseDonne connectionBaseDonne = new ConnectionBaseDonne();
    Connection connection = connectionBaseDonne.connectionBD();
	

		public void historique(int idUtilisateur, String methode, String name) {
			
	        try{
	        	Statement statement = connection.createStatement();
	        

	            LocalDateTime date = LocalDateTime.now();
	            date.format(formatter);
	        	
			    ResultSet resultSet1 = statement.executeQuery("select email from utilisateur where id_utilisateur ='"+idUtilisateur+"'");
		        String email = "";
		 		if (resultSet1.next()) {
		 		    // Move the cursor to the first row (if not already positioned)
		             email = resultSet1.getString("email");
		
		 		    // Now 'email' contains the value from the result set
		 		    System.out.println("User email: " + email);
		 		} else {
		 		    System.out.println("No user found with id: " + idUtilisateur);
		 		}
		         Statement statementHistorique = connection.createStatement();
		         int resultSetCat = statementHistorique.executeUpdate("INSERT INTO `historique`( `email`,`description`, `date`) VALUES ('"+email+"',' a "+methode+" une tache avec l utilisateur "+email+" le nom de ce tache : "+name+" date : "+date+" ','"+date+"')");
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
			}
		public int ajouter(int idUtilisateur) {
        LocalDateTime localdate = LocalDateTime.now();
        localdate.format(formatter);
		

		

		System.out.print("name : ");
		String name = scanner.next();
		
		System.out.print("description : ");
		String description = scanner.next();
		
		System.out.print("priorite : ");
		String priorite = scanner.next();
		

		System.out.print("liste des categories existantes :\n  ");
		String categorie = "" ;
	   try  {

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
            int numCategorie;
            do {
            	System.out.println("entrez le numero de la categorie ou tappez 0 pour ajoutez une : ");
                
                numCategorie = scanner.nextInt();
                
	            if (numCategorie == 0) {
	            	Statement statementCat = connection.createStatement();
	
	         		System.out.print("new categorie : ");
	         		String newcat = scanner.next();
	                 // Execute a query
	                 int resultSetCat = statementCat.executeUpdate("INSERT INTO `categorie`( `categorie_nom`) VALUES ('"+newcat+"')");
	         		/*System.out.println("add categorie : "+resultSetCat);*/
	                 
	         		categorie = newcat ;
	                 // Close resources
	                 statement.close();
	            }
	            else if(numCategorie<=categorieList.size()) {
	                categorie =  categorieList.get(numCategorie-1);
	            }
	            else {
	                System.out.println("not exist categorie");
	            }
            
            }while(numCategorie>categorieList.size() || numCategorie < 0);
            
            
            // Close resources
            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
           
        }
    
		
		
		
		try{

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute a query
            int resultSet = statement.executeUpdate("INSERT INTO `tache`( `name`, `description`, `date_de_creation`, `date_de_miseajour`, `priorite`, `categorie`,`id_utilisateur`) VALUES ('"+name+"','"+description+"','"+localdate+"','"+localdate+"','"+priorite+"','"+categorie+"','"+idUtilisateur+"')");
       
            // Close resources
            statement.close();
            
            historique(idUtilisateur, "ajouter",name);
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
		
	};
	public int supprimer(int idUtilisateur) {

		
		System.out.print(" entrez le nom de la tache a supprimer : ");
		String nameSup = scanner.next();
		try {

	        Statement testStatement = connection.createStatement();
	
	        ResultSet verifTache = testStatement.executeQuery("SELECT * FROM `tache` WHERE name = '"+nameSup+"' and id_utilisateur = '"+idUtilisateur+"'");
	
			if(verifTache.next()) {
        

	            // Create a statement
	            Statement statement = connection.createStatement();
	
	            // Execute a query
	            int resultSet = statement.executeUpdate("DELETE FROM `tache` WHERE name = '"+nameSup+"' and id_utilisateur = '"+idUtilisateur+"'");
	    		System.out.println("delete tache : "+resultSet);
	            
	            // Close resources
	            statement.close();

			}else {
				System.out.print("ce tache n'existe pas");
			}	
            historique(idUtilisateur, "Supprimer",nameSup);
			return 1;
		}catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
		};
	public int modifier(int idUtilisateur) {

		LocalDateTime localdate = LocalDateTime.now();
        localdate.format(formatter);
		
		System.out.print(" entrez le nom de la tache a modifier : ");
		String nameModif = scanner.next();
		try {

	        Statement testStatement = connection.createStatement();
	
	        ResultSet verifTache = testStatement.executeQuery("SELECT * FROM `tache` WHERE name = '"+nameModif+"'");
	
			if(verifTache.next()) {
			
				System.out.println("new name : ");
				String name = scanner.next();
				
				System.out.println("new description : ");
				String description = scanner.next();
				
				System.out.println("new priorite : ");
				String priorite = scanner.next();
				
				System.out.print("liste des categories existantes :\n  ");
				String categorie = "" ;

		            // Create a statement
		            Statement statementListCat = connection.createStatement();

		            // Execute a query
		            ResultSet resultSet = statementListCat.executeQuery("select * from categorie");

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
		            int numCategorie;
		            do {
		            	System.out.println("entrez le numero de la categorie ou tappez 0 pour ajoutez une : ");
		                
		                numCategorie = scanner.nextInt();
		                
			            if (numCategorie == 0) {
			            	Statement statementCat = connection.createStatement();
			
			         		System.out.print("new categorie : ");
			         		String newcat = scanner.next();
			                 // Execute a query
			                 int resultSetCat = statementCat.executeUpdate("INSERT INTO `categorie`( `categorie_nom`) VALUES ('"+newcat+"')");
			         		System.out.println("add categorie : "+resultSetCat);
			                 
			         		categorie = newcat ;
			                 // Close resources
			         		statementCat.close();
			            }
			            else if(numCategorie<=categorieList.size()) {
			                categorie =  categorieList.get(numCategorie-1);
			            }
			            else {
			                System.out.println("not exist categorie");
			            }
		            
		            }while(numCategorie>categorieList.size() || numCategorie < 0);
		            
		            // Close resources
		            resultSet.close();
		            statementListCat.close();
				
	
	            // Create a statement
	            Statement statement = connection.createStatement();
	
	            // Execute a query
	            int resultSet1 = statement.executeUpdate("UPDATE `tache` SET `name`='"+name+"',`description`='"+description+"',`date_de_miseajour`='"+localdate+"',`priorite`='"+priorite+"',`categorie`='"+categorie+"' WHERE name = '"+nameModif+"' and id_utilisateur = '"+idUtilisateur+"'");
                historique(idUtilisateur, "modifie",name);
	
	            // Close resources
	            statement.close();
	            
			}
			else {
				System.out.print("ce tache n'existe pas");
			}
			
			return 1;
		}catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
		
	};
	public int afficher(int idUtilisateur) {
		        
		try{
		
		    // Create a statement
		    Statement statement = connection.createStatement();
		
		    // Execute a query
		    ResultSet resultSet = statement.executeQuery("select * from tache WHERE id_utilisateur = '"+idUtilisateur+"' ");
		
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
		    
		    return 1;

		
		} catch (SQLException e) {
		    e.printStackTrace();
		    return 0;
		}
	};
	public int filtrerCategorie(int idUser) {

		System.out.print("categorie : ");
		String nom_Categorie = scanner.next();
		List<Tache> listTaches = listeDeTaches(idUser);
		
		Map<String, List<Tache>> mapCategorieTache = listTaches.stream().filter(c ->c.getCategorie().equals(nom_Categorie)).collect(Collectors.groupingBy(Tache::getCategorie));
		
		mapCategorieTache.forEach((categorie , listetaches)->
			System.out.println("tache pour categorie : "+categorie+" : "+listetaches)
		);
		return 1;

	};
	
	public List<Tache> listeDeTaches(int idUser) {
		List<Tache> ListeCategorie = new ArrayList<>();
		try{
		    Statement statement = connection.createStatement();
		    ResultSet resultSet = statement.executeQuery("select * from tache WHERE id_utilisateur = '"+idUser+"'");
		    while (resultSet.next()) {
		        String name = resultSet.getString("name");
		        String description = resultSet.getString("description");
		        String priorite = resultSet.getString("priorite");
		        String categorie = resultSet.getString("categorie");
		        LocalDateTime date_de_creation = resultSet.getTimestamp("date_de_creation").toLocalDateTime();
		        LocalDateTime date_de_miseajour = resultSet.getTimestamp("date_de_miseajour").toLocalDateTime();

		        Tache tache   = new Tache(name, description,date_de_creation,date_de_miseajour , priorite,categorie,idUser );
		        ListeCategorie.add(tache);
		        
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ListeCategorie;
        
	};
	
	
	@Override
	public int tri(int idUser) {
		int choix ;
		
		do {
			System.out.print("tri : par priorite entrez 1 _ par date de creation entrez 2 ");
			choix = scanner.nextInt();
		if(choix == 1) {
		List<Tache> listTaches = listeDeTaches(idUser);
		
		Map<String, List<Tache>> mapPrioriteTache = listTaches.stream().collect(Collectors.groupingBy(Tache::getPriorite));
		
		mapPrioriteTache.forEach((priorite1 , listetaches)->
			System.out.println("tache pour priorite : "+priorite1+" : "+listetaches)
		);
		}
		
		else if(choix == 2) {
			List<Tache> listTaches = listeDeTaches(idUser);
			
			Map<LocalDateTime, List<Tache>> mapDateDeCreationTache = listTaches.stream().collect(Collectors.groupingBy(Tache::getDate_de_creation));
			
			mapDateDeCreationTache.forEach((date , listetaches)->
				System.out.println("tache pour priorite : "+date+" : "+listetaches)
			);
		}
		}while (choix !=1 || choix != 2);
		
		return 1;
	}
	
	

	public static void main(String[] args) {
		CrudTache test= new CrudTache();
		test.historique(1,"test","test");

}}
