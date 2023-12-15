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
	
    public Priorite priorite() {
    	Priorite priorite = null;
        while (priorite == null) {
            System.out.println("Choisir une priorite en tapant son numero : ");
            System.out.println("1 - HAUTE ");
            System.out.println("2 - MOYENNE ");
            System.out.println("3 - BASSE ");
            int choose = scanner.nextInt();
          switch(choose) {
          case 1:
            priorite = Priorite.HAUTE;
            break;
          case 2:
              priorite = Priorite.MOYENNE;
            break;
          case 3:
              priorite = Priorite.BASSE;
            break;
            default: 
                System.out.println("Choix invalide. Utilisez les numéros 1, 2 ou 3.");
                
        }
          
        }
        return priorite;
    }

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
	
	public String categorie() {
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
	   return categorie;
	}
		
		
	public int ajouter(int idUtilisateur) {
        LocalDateTime localdate = LocalDateTime.now();
        localdate.format(formatter);

		System.out.print("name : ");
		String name = scanner.nextLine();
		
		System.out.print("description : ");
		String description = scanner.nextLine();
		
		Priorite priorite = priorite();
		
		String categorie = categorie();
		
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
	public int modifier(Utilisateur utilisateur) {

		LocalDateTime localdate = LocalDateTime.now();
        localdate.format(formatter);
		
		System.out.println(" entrez le nom de la tache a modifier : ");
		String nameModif = scanner.nextLine();
		try {

	        Statement testStatement = connection.createStatement();
	
	        ResultSet verifTache = testStatement.executeQuery("SELECT * FROM `tache` WHERE name = '"+nameModif+"'");
	
			if(verifTache.next()) {
			
				System.out.println("new name : ");
				String name = scanner.nextLine();
				
				System.out.println("new description : ");
				String description = scanner.nextLine();
				
				Priorite priorite = priorite();
				
				String categorie = categorie();		          
	
	            // Create a statement
	            Statement statement = connection.createStatement();
	
	            // Execute a query
	            int resultSet = statement.executeUpdate("UPDATE `tache` SET `name`='"+name+"',`description`='"+description+"',`date_de_miseajour`='"+localdate+"',`priorite`='"+priorite+"',`categorie`='"+categorie+"' WHERE name = '"+nameModif+"' and id_utilisateur = '"+utilisateur.getId()+"'");
                historique(utilisateur.getId(), "modifie",name);
	
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
	public int afficher(Utilisateur utilisateur) {
		        
		try{
		
		    // Create a statement
		    Statement statement = connection.createStatement();
		
		    // Execute a query
		    //ResultSet resultSet = statement.executeQuery("select * from tache WHERE id_utilisateur = '"+idUtilisateur+"' ");
		    ResultSet resultSet = statement.executeQuery("SELECT * FROM `tache` WHERE id_utilisateur='"+utilisateur.getId()+"' or tache_id IN (SELECT tache_id from affictation WHERE email='"+utilisateur.getEmail()+"');");
		    

		    // Process the results
		    while (resultSet.next()) {

		        // categories list :
		        System.out.print("name : "+resultSet.getString("name")+" description : "+resultSet.getString("description")+" priorite : "+resultSet.getString("priorite")+" categorie : "+resultSet.getString("categorie")+" date_de_creation : "+resultSet.getString("date_de_creation")+" date_de_miseajour : "+ resultSet.getString("date_de_miseajour")+"\n");
		        
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
		
		/*Map<String, List<Tache>> mapCategorieTache = listTaches.stream().filter(c ->c.getCategorie().equals(nom_Categorie)).collect(Collectors.groupingBy(Tache::getCategorie));
		
		mapCategorieTache.forEach((categorie , listetaches)->
			System.out.println("tache pour categorie : "+categorie+" : "+listetaches)
		);*/
		List<Tache> listTacheParCategorieX = listTaches.stream().filter(c ->c.getCategorie().equals(nom_Categorie)).collect(Collectors.toList());
        // Afficher les Tache de la liste(Application de la methode de reference )
        System.out.println("Liste des taches par categorie :"+nom_Categorie);
        listTacheParCategorieX.forEach(System.out::println);
		return 1;

	};
	
	public List<Tache> listeDeTaches(int idUser) {
		List<Tache> ListeCategorie = new ArrayList<>();
		try{
		    Statement statement = connection.createStatement();
		    ResultSet resultSet = statement.executeQuery("select * from tache WHERE id_utilisateur = '"+idUser+"'");
		    while (resultSet.next()) {
		        Tache tache  = new Tache(resultSet.getString("name"), resultSet.getString("description"),resultSet.getTimestamp("date_de_creation").toLocalDateTime(),resultSet.getTimestamp("date_de_miseajour").toLocalDateTime() , Priorite.valueOf(resultSet.getString("priorite")),resultSet.getString("categorie"),idUser );
		        ListeCategorie.add(tache);
		        
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ListeCategorie;
        
	};
	
	
	@Override
	public int tri(int idUser) {
		int choix =0;
		
		do {
			System.out.print("tri : par priorite entrez 1 _ par date de creation entrez 2 pour quitter entrez 0");
			choix = scanner.nextInt();
		if(choix == 1) {
		List<Tache> listTaches = listeDeTaches(idUser);
		
		Map<Priorite, List<Tache>> mapPrioriteTache = listTaches.stream().collect(Collectors.groupingBy(Tache::getPriorite));
		
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
		}while (choix !=0);
		
		return 1;
	}
	

    public static void main(String[] args) {
    	CrudTache test = new CrudTache();
    	test.filtrerCategorie(1);
    }
	
}
