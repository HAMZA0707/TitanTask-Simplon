package titantask;

public interface ICrudtache {
	
	public void ajouter();
	public void supprimer();
	public void modifier();
	public void afficher();
	public void filtrerCategorie(String nom_Categorie);
	public void tri(int choix);
	
}
