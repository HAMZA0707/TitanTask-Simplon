package titantask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagmenetUtilisateur implements ICrud_Utilisateur {
    ConnectionBaseDonne cn = new ConnectionBaseDonne();
    Connection con = cn.connectionBD();
    Scanner scanner = new Scanner(System.in);
    PreparedStatement statement = null;
    @Override
    public void ajouter() {
        System.out.println("Entre votre Nom : ");
        String nom = scanner.next();
        System.out.println("Entre votre Fonction : ");
        String fonction = scanner.next();
        System.out.println("Entre votre email : ");
        String email = scanner.next();
        System.out.println("Entre votre password : ");
        String password = scanner.next();
        try{
            String query = "insert into utilisateur( nom, fonction, email , PASSWORD) values ( ?, ?, ?, ?)";
            statement =con.prepareCall(query);
            statement.setString(1, nom);
            statement.setString(2, fonction);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void supprimer(int id) {
        try{
            String query = "delete from utilisateur where id_utilisateur = ?";
            statement = con.prepareStatement(query);
            statement.setInt(1,id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifier(int id) {


        System.out.println("Entre votre Nom : ");
        String nom = scanner.next();
        System.out.println("Entre votre Fonction : ");
        String fonction = scanner.next();
        System.out.println("Entre votre email : ");
        String email = scanner.next();
        System.out.println("Entre votre password : ");
        String password = scanner.next();
        try{
            String query = "update utilisateur set nom = ?, fonction = ?, email = ?, PASSWORD = ? where id_utilisateur = ?";
            statement =con.prepareCall(query);
            statement.setString(1, nom);
            statement.setString(2, fonction);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setInt(5,id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Utilisateur> afficher(int id) {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        try{
            String query = "select * from utilisateur where id_utilisateur = ?";
            statement = con.prepareCall(query);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                int id_utilisateurs = resultSet.getInt("id_utilisateur");
                String noms = resultSet.getString("nom");
                String fonctions = resultSet.getString("fonction");
                String emails = resultSet.getNString("email");
                String passwords = resultSet.getNString("PASSWORD");
                Utilisateur utilisateur=new Utilisateur(noms,fonctions,emails,passwords,id_utilisateurs);
                utilisateurs.add(utilisateur);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return utilisateurs;
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
			 u =new Utilisateur(rs.getString("nom"),rs.getString("fonction"),rs.getString("email"),rs.getString("password"),rs.getInt("id_utilisateur"));
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
