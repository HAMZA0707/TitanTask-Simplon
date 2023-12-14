package titantask;

import java.util.List;

public interface ICrud_Utilisateur {
	
	public boolean ajouter();
	public boolean supprimer();
	public boolean modifier();
	public Utilisateur afficher();
	public Utilisateur login();
}
