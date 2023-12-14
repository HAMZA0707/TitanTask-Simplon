package titantask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ManagmenetUtilisateur implements ICrud_Utilisateur {
    ConnectionBaseDonne cn = new ConnectionBaseDonne();
    Connection con = cn.connectionBD();
    Scanner scanner = new Scanner(System.in);
    PreparedStatement statement = null;
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
        boolean role= scanner.nextBoolean();

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
                    statement.setBoolean(5, role);
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


    @Override
    public boolean supprimer(String email) {
        try{
            String query = "delete from utilisateur where email = ?";
            statement = con.prepareStatement(query);
            statement.setString(1,email);
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean modifier(String email) {
        System.out.println("Entre votre Nom : ");
        String nom = scanner.next();
        System.out.println("Entre votre Fonction : ");
        String fonction = scanner.next();
        System.out.println("Entre votre email : ");
        String emaill = scanner.next();
        System.out.println("Entre votre password : ");
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

    @Override
    public Utilisateur afficher(String email) {
        Utilisateur utilisateur = null;
        try{
            String query = "select * from utilisateur where email = ?";
            statement = con.prepareCall(query);
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id_utilisateurs = resultSet.getInt("id_utilisateur");
                String noms = resultSet.getString("nom");
                String fonctions = resultSet.getString("fonction");
                String emails = resultSet.getString("email");
                String passwords = resultSet.getString("PASSWORD");
                boolean role = resultSet.getBoolean("role");
                utilisateur=new Utilisateur(noms,fonctions,emails,passwords,id_utilisateurs,role);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return utilisateur;
    }

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
		con.close();
		return u;
		}catch (Exception e) {
				System.out.print(e);
			}
		return null;

    }
}
