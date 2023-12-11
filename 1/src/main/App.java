package gestionnaire_Notes;

import java.util.Scanner;

public class App {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner cl=new Scanner(System.in);
		GestManager gm=new GestManager();
		
		/****recuperer les donnees a partir fichier csv****/
		Dbcsv db=new Dbcsv();
		db.setFilePath("etudiants.csv");
		gm.etudiant_list=db.get_Data_Etudiant();
		db.setFilePath("Notes.csv");
		gm.note_List=db.get_Data_Notes();
		/**************************************/
		System.out.println("Merci de Saisir Votre Nom:");
		String username= cl.next();
		int c=0;
		do {
			System.out.println("***********| "+username+" |Bienvenu a votre Gestionnaire des Notes***********");
			System.out.println("Choisie L'operation que vous voulez faire");
			System.out.println("Pour Ajouter un Etudiant taper 1");
			System.out.println("Pour Ajouter Des Notes taper 2");
			System.out.println("Pour Afficher les Note d'un etudiant taper 3");
			System.out.println("Pour Calculer la Moyenne pour chaque Matiere taper 4");
			System.out.println("Pour Calculer la Moyenne Generale taper 5");
			System.out.println("Pour Supprimer Etudiant taper 6");
			System.out.println("Pour Quitter taper 0");
			c= cl.nextInt();
			switch (c) {
			case 1: {
				gm.add_Etudiant();
				break;
			}
			case 2: {
				System.out.println("Nom Etudiant a chercher:");
				String search= cl.next();
				if(gm.addNotes(search)) {
					System.out.println("Ajout Success");
				}else if (gm.addNotes(search)== false) {
					System.out.print("Etudiant N'existe pas , Voulez vous l'ajouter? ( 'y' pour oui 'n' pour non)");
					String s=cl.next();
					if(s.equals("y")) {
						gm.add_Etudiant();
					}
				}
				break;
			}
			case 3:{
				System.out.println("Nom Etudiant a chercher:");
				String search= cl.next();
				gm.afficher_Notes(search);
				break;
			}
			case 4:{
				System.out.println("Nom Etudiant a chercher:");
				String name_etudiant= cl.next();
				System.out.println("Matiere a Calculer:");
				String search_Matiere= cl.next();
				if(gm.calculer_Moyenne_Matiere(name_etudiant, search_Matiere)!=-1) {
					System.out.println("La Moyenne de Note de la Matiere "+search_Matiere+" est :");
					System.out.println(gm.calculer_Moyenne_Matiere(name_etudiant, search_Matiere));
				}else {
					System.out.println("Etudiant ou Matiere n'existe pas");
				}
				break;
			}
			case 5:{
				System.out.println("Nom Etudiant a chercher:");
				String name_etudiant= cl.next();
				System.out.println("La Moyenne Generale de l'Etudiant "+name_etudiant+" est :");
				System.out.println(gm.calculer_Moyenne_Generale(name_etudiant));
				break;
			}
			case 6:{
				System.out.println("Nom Etudiant a Supprimer:");
				String name_etudiant= cl.next();
				if(gm.supprimer_Etudiant(name_etudiant)) {
					System.out.println("Suppression reussi");
				}
				else {
					System.out.println("Etudiant n'existe pas");
				}
				break;
			}
			}
			/********Sauvegarder des donnees*************/
			if(c==0) {
				db.setFilePath("etudiants.csv");
				db.save_Data_Etudiant(gm.etudiant_list);
				db.setFilePath("Notes.csv");
				db.save_Data_Notes(gm.note_List);
			}
			/****************************************/
		}while(c!=0);
		
	}

}
