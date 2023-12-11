package gestionnaire_Notes;

import java.util.ArrayList;
import java.util.Scanner;

public class GestManager {
	
	ArrayList<Etudiant> etudiant_list= new ArrayList<>();
	ArrayList<Note> note_List= new ArrayList<>();
	
	public int searchMatiere(String mat,int et_index) {
		for(int i =0; i<note_List.size();i++) {
			if(note_List.get(i).getMatiere().equals(mat) && note_List.get(i).getId_Etudiant()==et_index) {
				return i;
			}
		}
		return -1;
	}
	public int search_Etudiant(String nom) {
		for(int i =0; i<etudiant_list.size();i++) {
			if(etudiant_list.get(i).getNom().equals(nom)) {
				return i;
			}
		}
		return -1;
	}
	
	public void add_Etudiant() {
		Scanner cl=new Scanner(System.in);
		System.out.println("Nom Etudiant:");
		String n= cl.next();
		System.out.println("Date Naissance Etudiant:");
		String date= cl.next();
		System.out.println("Niveau:");
		String niv= cl.next();
		Etudiant e=new Etudiant(n, date, niv);
		etudiant_list.add(e);
	}
	public boolean supprimer_Etudiant(String nom) {
		int i=search_Etudiant(nom);
		if(i!=-1) {
				for(int j =0; j<note_List.size();j++) {
					if(note_List.get(j).getId_Etudiant()==i) {
						note_List.remove(j);
					}
				}
				etudiant_list.remove(i);
				return true;
			}
		return false;
	}

	public boolean addNotes(String nom) {
		Scanner cl=new Scanner(System.in);
		String s;
		int i=search_Etudiant(nom);
			if(i!=-1) {
				do {
				System.out.println("Etudiant : "+nom );
				System.out.println("Saisie La Matiere:");
				String mat=cl.next();
				System.out.println("Saisie La Note:");
				float note=cl.nextFloat();
				if(note>=0 && note <=20) {
					int j=searchMatiere(mat, i);
					if(j!=-1) {
							Note n =note_List.get(j);
							n.setNotes(note);
						}
						else {
							Note n =new Note(mat,note,i);
							note_List.add(n);
						}
				}
				else {System.out.println("La note doit etre entre 0 et 20");}
				System.out.print("Voulez vous l'ajouter autre Matiere ( 'y' pour oui 'n' pour non)");
				 s=cl.next();
				}while(s.equals("y"));
				return true;	
			}
		return false;	
	}
	
	public void afficher_Notes(String nom) {
		int i=search_Etudiant(nom);
		if(i!=-1) {
				for(int j =0; j<note_List.size();j++) {
					if(note_List.get(j).getId_Etudiant()==i) {
						System.out.println(note_List.get(j).display_Notes());
					}
				}
			}
	}
	
	public float calculer_Moyenne_Matiere(String nom,String mat) {
		int i=search_Etudiant(nom);
		if(i!=-1) {
				for(int j =0; j<note_List.size();j++) {
					if(note_List.get(j).getId_Etudiant()==i) {
						if(note_List.get(j).getMatiere().equals(mat)) {
							return note_List.get(j).moyenne_Notes();
						}
					}
				}
			}
		return -1;
	}
	public float calculer_Moyenne_Generale(String nom) {
		float s=0;
		int count=0;
		int i=search_Etudiant(nom);
		if(i!=-1) {
				for(int j =0; j<note_List.size();j++) {
					if(note_List.get(j).getId_Etudiant()==i) {
							s+=note_List.get(j).moyenne_Notes();
							count++;
					}
				}
		}
		return s/count;
	}
	
}
