package titantask.services;

import titantask.model.Utilisateur;

public interface ICrudUtilisateur {
	
	public boolean ajouter();
	public boolean supprimer();
	public boolean modifier();
	public Utilisateur afficher();
	public Utilisateur login();
}
