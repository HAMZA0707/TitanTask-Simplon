package gestionnaire_Notes;

import java.util.ArrayList;

public class Note {
	//class pour ajouter la matiere et ces notes
	private String libelle;
	private int Id_Etudiant;
	private ArrayList<Float> Notes= new ArrayList<>();
	
	public Note() {}
	
	public Note(String matiere, float note,int id_etudiant) {
		this.libelle = matiere;
		this.Id_Etudiant=id_etudiant;
		this.Notes.add(note);
	}
	
	public ArrayList<Float> getNotes() {
		return Notes;
	}

	public void setNotes(float n) {
		this.Notes.add(n);
	}

	public String getMatiere() {
		return libelle;
	}
	public void setMatiere(String matiere) {
		libelle = matiere;
	}
	

	public int getId_Etudiant() {
		return Id_Etudiant;
	}

	public void setId_Etudiant(int id_Etudiant) {
		Id_Etudiant = id_Etudiant;
	}
	public String display_Notes() {
		String s="Matiere=" + libelle;
		for(int j =1; j<=Notes.size();j++) {
			s+= "| Note "+j +": "+ Notes.get(j-1).toString();
		}
		return s;
	}
	
	public float moyenne_Notes() {
		float s=0;
		for(int j =0; j< Notes.size();j++) {
			s = s + Notes.get(j).floatValue();
		}
		s=s/Notes.size();
		return s;
	}

	@Override
	public String toString() {
		return "Note [Matiere=" + libelle + ", Id_Etudiant=" + Id_Etudiant + "]";
	}

	
}
