package gestionnaire_Notes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Dbcsv {
	private String FilePath;
	BufferedWriter bw;
	String CSV_SEPARATOR=",";
	GestManager gm=new GestManager();
	
	//Getter and Setter
	public String getFilePath() {
		return FilePath;
	}

	public void setFilePath(String filePath) {
		FilePath = filePath;
	}
	
	//Methode pour sauvegarder list de Etudiant dans fichier csv
	public void save_Data_Etudiant(ArrayList<Etudiant> list ) {
		try
        {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FilePath), "UTF-8"));
            for (Etudiant et : list)
            {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(et.getNom());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(et.getDate_Naissance());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(et.getNiveau());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
	}
	
	//Methode pour recuperer list des etudiants a partir fichier csv
	public ArrayList<Etudiant> get_Data_Etudiant() {
		String line;
			try {
				BufferedReader bf= new BufferedReader(new FileReader(FilePath));
				while((line=bf.readLine())!=null) {
					String [] s=line.split(CSV_SEPARATOR);
					Etudiant et=new Etudiant(s[0],s[1],s[2]);
					gm.etudiant_list.add(et);
				}
				bf.close();
				return gm.etudiant_list;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
			
	}
	
	//Methode pour sauvegarder list des notes dans fichier csv
	public void save_Data_Notes(ArrayList<Note> list ) {
		try
        {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FilePath), "UTF-8"));
            for (Note n : list)
            {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(n.getId_Etudiant());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(n.getMatiere());
                oneLine.append(CSV_SEPARATOR);
                for(int i=0; i< n.getNotes().size();i++) {
                    oneLine.append(n.getNotes().get(i));
                    oneLine.append(CSV_SEPARATOR);
                }
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
	}
	
	//Methode pour recuperer list des notes a partir fichier csv
	public ArrayList<Note> get_Data_Notes() {
		String line;
			try {
				BufferedReader bf= new BufferedReader(new FileReader(FilePath));
				while((line=bf.readLine())!=null) {
					String [] s=line.split(CSV_SEPARATOR);
					Note et=new Note(s[1],Float.parseFloat(s[2]),Integer.parseInt(s[0]));
					if(s.length>3) {
						for(int i=3; i<s.length;i++) {
							et.setNotes(Float.parseFloat(s[i]));
						}
					}
					gm.note_List.add(et);
				}
				bf.close();
				return gm.note_List;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
			
	}

}
