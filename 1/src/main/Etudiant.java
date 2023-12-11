package gestionnaire_Notes;

public class Etudiant {
	
	private String Nom;
	private String Date_Naissance;
	private String Niveau;

	public Etudiant() {}
	public Etudiant(String nom, String date_Naissance, String niveau) {
		super();
		Nom = nom;
		Date_Naissance = date_Naissance;
		Niveau = niveau;
	}

	@Override
	public String toString() {
		return "Etudiant [Nom=" + Nom + ", Date_Naissance=" + Date_Naissance + ", Niveau=" + Niveau + "]";
	}
	
	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getDate_Naissance() {
		return Date_Naissance;
	}

	public void setDate_Naissance(String date_Naissance) {
		Date_Naissance = date_Naissance;
	}

	public String getNiveau() {
		return Niveau;
	}

	public void setNiveau(String niveau) {
		Niveau = niveau;
	}
	
	

}
