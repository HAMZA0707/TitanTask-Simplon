package titantask;

import java.util.List;

public class Testclass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("Hello World");
		System.out.println("\nMariam Laghfiri");
		System.out.println("ana Hamza");
		System.out.println("Adil errai");
		System.out.println("Talaini");
		ManagmenetUtilisateur managmenetUtilisateur = new ManagmenetUtilisateur();
	//	managmenetUtilisateur.ajouter();
//		managmenetUtilisateur.modifier(1);
//		managmenetUtilisateur.supprimer(1);
		// pour tester l'affichege des utilisateur
		/*List<Utilisateur> utilisateurs = managmenetUtilisateur.afficher();
		System.out.println("Liste des utilisateurs :");
		for (Utilisateur utilisateur : utilisateurs) {
			System.out.println(utilisateur);
		}*/
		
		Utilisateur u=managmenetUtilisateur.login();
			if(u != null) {
				System.out.print(u.toString());
			}
			else {
				System.out.print("Informations saisie incorrect");
			}
		
	}
}