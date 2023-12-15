package titantask.controller;

import titantask.services.ICrudUtilisateur;
import titantask.model.Utilisateur;
import titantask.services.ConnectionBaseDonne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ManagmenetUtilisateur implements ICrudUtilisateur {
    ConnectionBaseDonne cn = new ConnectionBaseDonne();
    Connection con = cn.connectionBD();
    Scanner scanner = new Scanner(System.in);
    PreparedStatement statement = null;

    //methode pour ajouter utilisateur
    @Override
    public boolean ajouter() {
        System.out.println("Entre votre Nom : ");
        String nom = scanner.next();
        System.out.println("Entre votre Fonction : ");
        String fonction = scanner.next();
        System.out.println("Entre votre email : ");
        String email = scanner.next();
        System.out.println("Entre votre password : ");
        String password = scanner.next();
        System.out.println("Entre votre role (admin/user) ");
        String test=scanner.next();
        int role=1;
        if(test.equals("user")) {
        	role=0;
        }
        // Vérifier si l'utilisateur existe déjà
        try {
            String checkQuery = "SELECT COUNT(*) FROM utilisateur WHERE email = ?";
             statement = con.prepareStatement(checkQuery);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    System.out.println("L'utilisateur avec l'email '" + email + "' existe déjà.");
                    return false;
                } else {
                    // Ajouter l'utilisateur
                    String insertQuery = "INSERT INTO utilisateur(nom, fonction, email, PASSWORD, role) VALUES (?, ?, ?, ?, ?)";
                    statement = con.prepareCall(insertQuery);
                    statement.setString(1, nom);
                    statement.setString(2, fonction);
                    statement.setString(3, email);
                    statement.setString(4, password);
                    statement.setInt(5, role);
                    statement.execute();
                    System.out.println("Utilisateur ajouté avec succès.");
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    //methode pour supp utilisateur
    @Override
    public boolean supprimer() {
        try{
        	System.out.println("Entre l'email de l'utilisateur a Supprimer : ");
            String email = scanner.next();
            String query = "delete from tache where id_utilisateur IN (select id_utilisateur from utilisateur where email = ?)";
            statement = con.prepareStatement(query);
            statement.setString(1,email);
            statement.execute();
            String query1 = "delete from utilisateur where email = ?";
            statement = con.prepareStatement(query1);
            statement.setString(1,email);
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //methode pour modification utilisateur
    @Override
    public boolean modifier() {
    	System.out.println("Entre l'email de l'utilisateur a modifier : ");
        String email = scanner.next();
        System.out.println("Entre Nouveau Nom : ");
        String nom = scanner.next();
        System.out.println("Entre Nouveau Fonction : ");
        String fonction = scanner.next();
        System.out.println("Entre Nouveau email : ");
        String emaill = scanner.next();
        System.out.println("Entre Nouveau password : ");
        String password = scanner.next();
        System.out.println("Entre le role admin = true et autre = false : ");
        boolean role = scanner.nextBoolean();
        try{
            String query = "update utilisateur set nom = ?, fonction = ?, email = ?, PASSWORD = ? , role = ? , where email = ?";
            statement =con.prepareCall(query);
            statement.setString(1, nom);
            statement.setString(2, fonction);
            statement.setString(3, emaill);
            statement.setString(4, password);
            statement.setBoolean(5,role);
            statement.setString(6,email);
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //methode pour affichege l'utilisateur
    @Override
    public Utilisateur afficher() {
        Utilisateur utilisateur = null;
        System.out.println("Entre l'email de l'utilisateur a Afficher : ");
        String email = scanner.next();
        try {
            String checkQuery = "SELECT * FROM utilisateur WHERE email = ?";
            statement = con.prepareStatement(checkQuery);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id_utilisateurs = resultSet.getInt("id_utilisateur");
                String noms = resultSet.getString("nom");
                String fonctions = resultSet.getString("fonction");
                String emails = resultSet.getString("email");
                String passwords = resultSet.getString("PASSWORD");
                boolean role = resultSet.getBoolean("role");
                System.out.print("test");
                utilisateur = new Utilisateur(noms, fonctions, emails, passwords, id_utilisateurs, role);
            }

            return utilisateur;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Methode pour login

    @Override
    public Utilisateur login() {
    	Utilisateur u = null;
    	int i=0;
		Scanner cl =new Scanner(System.in);
		System.out.println("Entre votre email:");
		String email =cl.next();
		System.out.println("Entre votre password:");
		String password =cl.next();
		try {
		Statement st = con.createStatement();
	    ResultSet rs;
		rs = st.executeQuery("select * from utilisateur where email='"+email+"' and password='"+password+"'");
	
		 while(rs.next()) {
			 u =new Utilisateur(rs.getString("nom"),rs.getString("fonction"),rs.getString("email"),rs.getString("password"),rs.getInt("id_utilisateur"),rs.getBoolean("role"));
		 }
		st.close();
		return u;
		}catch (Exception e) {
				System.out.print(e);
			}
		return null;

    }
}
