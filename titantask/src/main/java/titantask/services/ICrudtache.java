package titantask.services;

import titantask.model.Utilisateur;

public interface ICrudtache {
	
	public int ajouter(Utilisateur utilisateur);
	public int supprimer(Utilisateur utilisateur);
	public int modifier(Utilisateur utilisateur);
	public int afficher(Utilisateur utilisateur);
	public int filtrerCategorie(Utilisateur user);
	public int tri(Utilisateur user);
	
}
