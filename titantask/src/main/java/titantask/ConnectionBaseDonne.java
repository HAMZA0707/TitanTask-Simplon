package titantask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBaseDonne {
	 Connection connectionBD(){
		Connection con = null;
		String url= "jdbc:mysql://localhost:3306/titanschool"; // table details
		String username = "root"; // MySQL credentials
		String password = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Driver name
			con = DriverManager.getConnection(url, username, password);
			if (con != null) {
				System.out.println("Connected to the database!");
			}
		} catch (ClassNotFoundException | SQLException  e) {
			throw new RuntimeException(e);
		}
		return con;
	}

}
