package titantask;

import java.util.List;

public interface ICrud_Utilisateur {
	
	public boolean ajouter();
	public boolean supprimer(String email);
	public boolean modifier(String email);
	public Utilisateur afficher(String email);
	public Utilisateur login();
}
