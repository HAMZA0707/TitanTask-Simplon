package titantask;

public interface ICrud {
	
	public void ajouter();
	public void supprimer();
	public void modifier();
	public void afficher();
	public Utilisateur login(String email,String password);
	public void filtrerCategorie(String nom_Categorie);
	public void tri(int choix);
	
}
