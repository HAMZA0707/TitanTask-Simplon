package titantask;

import java.util.List;

public interface ICrud_Utilisateur {
	
	public boolean ajouter();
	public boolean supprimer(int id);
	public boolean modifier(int id);
	public Utilisateur afficher(int id);
	public Utilisateur login();
}
