package titantask;

public interface ICrudtache {
	
	public int ajouter(int idUtilisateur);
	public int supprimer(int idUtilisateur);
	public int modifier(Utilisateur utilisateur);
	public int afficher(Utilisateur idUtilisateur);
	public int filtrerCategorie(int idUser);
	public int tri(int idUser);
	
}
