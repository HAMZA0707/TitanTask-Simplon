package titantask;

public interface ICrudtache {
	
	public int ajouter(int idUtilisateur);
	public int supprimer(int idUtilisateur);
	public int modifier(int idUtilisateur);
	public int afficher(int idUtilisateur);
	public int filtrerCategorie(String nom_Categorie);
	public int tri(int choix);
	
}
