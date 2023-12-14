package titantask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.File;
public class Export_Import {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	ConnectionBaseDonne cn = new ConnectionBaseDonne();
    Connection con = cn.connectionBD();
    Scanner scanner = new Scanner(System.in);
    PreparedStatement statement = null;
    private String FilePath="C:\\Users\\21260\\Desktop\\Taches.csv";
	BufferedWriter bw;
	String CSV_SEPARATOR=",";
	
	public boolean export_Tache(int id) {
		try{
            String query = "select * from tache where id_utilisateur = ?";
            statement = con.prepareCall(query);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FilePath), "UTF-8"));
            while (resultSet.next()){
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(resultSet.getString("name"));
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(resultSet.getString("description"));
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(resultSet.getNString("priorite"));
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(resultSet.getNString("categorie"));
                bw.write(oneLine.toString());
                bw.newLine();
            }
            statement.close();
            bw.flush();
            bw.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
		return false;
	}
	
	public boolean import_tache(int id,String path) {
		String line;
		try {
			File f = new File(path);
			if(f.exists() && !f.isDirectory()) { 
				BufferedReader bf= new BufferedReader(new FileReader(path));
				while((line=bf.readLine())!=null) {
					String [] s=line.split(CSV_SEPARATOR);
					LocalDateTime localdate = LocalDateTime.now();
			        localdate.format(formatter);
					Statement statement = con.createStatement();
			        statement.executeUpdate("INSERT INTO `tache`( `name`, `description`, `date_de_creation`, `date_de_miseajour`, `priorite`, `categorie`,`id_utilisateur`) VALUES ('"+s[0]+"','"+s[1]+"','"+localdate+"','"+localdate+"','"+s[2]+"','"+s[3]+"','"+id+"')");
			        String query = "select tache_id from tache where categorie='"+s[3]+"'";
		            ResultSet resultSet = statement.executeQuery(query);
		            resultSet.next();
		            int id_tache=resultSet.getInt("tache_id");
			        statement.executeUpdate("INSERT INTO `categorie`( `categorie_nom`,`tache_id`) VALUES ('"+s[3]+"','"+id_tache+"')");
			        statement.close();
				}
				bf.close();
				return true;
			}
			else {
				System.out.print("File n'existe pas");
				return false;
			}
			
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
}
