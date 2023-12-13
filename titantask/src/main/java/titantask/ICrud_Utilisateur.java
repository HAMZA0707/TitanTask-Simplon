package titantask;

import java.util.List;

public interface ICrud_Utilisateur {
	
	public void ajouter();
	public void supprimer(int id);
	public void modifier(int id);
	public Utilisateur afficher(int id);
	public Utilisateur login();
}
