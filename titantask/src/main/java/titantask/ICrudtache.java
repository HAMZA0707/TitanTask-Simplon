package titantask;

public interface ICrudtache {
	
	public int ajouter();
	public int supprimer();
	public int modifier();
	public int afficher();
	public int filtrerCategorie(String nom_Categorie);
	public int tri(int choix);
	
}
