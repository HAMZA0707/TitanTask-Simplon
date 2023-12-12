package titantask;

public interface ICrud_Utilisateur {
	
	public void ajouter();
	public void supprimer();
	public void modifier();
	public void afficher();
	public Utilisateur login(String email,String password);
}
