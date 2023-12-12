package titantask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connectiondb {
	
	public static void main(String[] args) throws ClassNotFoundException {

	String url= "jdbc:mysql://localhost:3306/titanSchool"; // table details
    String username = "root"; // MySQL credentials
    String password = "";
    String query = "select * from client"; // query to be run
    Class.forName("com.mysql.cj.jdbc.Driver"); // Driver name
    Connection con;
	try {
		
	 con = DriverManager.getConnection(url, username, password);
    System.out.println("Connection Established successfully");
    Statement st;
	 st = con.createStatement();
	
    ResultSet rs;
	 rs = st.executeQuery(query);
	 rs.next();
	
    String name;

		name = rs.getString("Nom_client");
	
    System.out.println(name); // Print result on console
 
		st.close();
	 // close statement
   
		con.close();
	// close connection
    System.out.println("Connection Closed....");
		System.out.print("Hello World");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	}
}
