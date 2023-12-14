package titantask.controller;

import titantask.ManagmenetTache;
import titantask.ManagmenetUtilisateur;
import titantask.Utilisateur;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        ManagmenetUtilisateur managmenetUtilisateur = new ManagmenetUtilisateur();
        Utilisateur utilisateur = new Utilisateur();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\033[1;32mBienvenue dans votre TaskManager ! \033[0m ");
        System.out.println("========== Connectez-vous ==========");
        while (true) {
            try {
                utilisateur = managmenetUtilisateur.login();
                if (utilisateur != null) {
                    System.out.println("\u001b[32mVous etes connecté avec succès\u001b[0m");
                    //
                    if (utilisateur.isRole()) {
                        subMenuAdmin(utilisateur);
                    } else {
                        subMenuUser(utilisateur);
                    }
                    //
                } else {
                    System.out.println("\u001b[31mLe mail ou le mot de passe est incorrect || le compte n'existe pas \\u001b[0m");
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Les données que vous avez saisie sont incorrecte ");
                scanner.nextLine();
            }
        }
    }


    static void subMenuAdmin(Utilisateur utilisateur) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\u001b[32m============================================================================\u001b[0m");
        System.out.println("\t\t\t\t\t \033[1;34mBienvenue "+utilisateur.getNom()+" dans votre espace personnel\033[0m");
        while (true) {
            System.out.println("\u001b[32m============================================================================\u001b[0m");
            System.out.println("choisis une option :");
            System.out.println("1. Gestion des tâches");
            System.out.println("2. Gestion des utilisateurs");
            System.out.println("3. Gestion de compte");
            System.out.println("4. Deconnection");
            System.out.print("Entrez le numéro de votre choix: ");
            try {
                int inputChoix = scanner.nextInt();
                switch (inputChoix) {
                    case 1:
                        System.out.println("Gestion des tâches");
                        break;
                    case 2:
                        System.out.println("Gestion des utilisateurs");
                        break;
                    case 3:
                        System.out.println("Gestion de compte");
                        break;
                    case 4:
                        System.out.println("M3a Salama");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\u001b[31mLe nombre incorrect\u001b[0m");
                }
            } catch (NumberFormatException | InputMismatchException exception) {
                System.out.println("\u001b[31mle entree est incorrect\u001b[0m");
                scanner.nextLine();
            }
        }


    }

    static void subMenuUser(Utilisateur utilisateur) {
        ManagmenetTache managmenetTache = new ManagmenetTache();
        System.out.println("\u001b[32m============================================================================\u001b[0m");
        System.out.println("\t\t\t\t\t \033[1;34mBienvenue "+utilisateur.getNom()+" dans votre espace personnel\033[0m");
        while (true) {
            System.out.println("\u001b[32m============================================================================\u001b[0m");
            System.out.println("Choisis une option");
            System.out.println("1. Ajouter une tache");
            System.out.println("2. Modifier une tache");
            System.out.println("3. Afficher une tache");
            System.out.println("4. Supprimer liste des taches");
            System.out.println("5. Deconnection");
            System.out.print("Entrez le numéro de votre choix: ");
            try {
                Scanner scanner = new Scanner(System.in);
                int inputChoix = scanner.nextInt();
                switch (inputChoix) {
                    case 1:
                        if (managmenetTache.ajouter(utilisateur.getId()) == 1){
                            System.out.println("ajout success");
                        }else
                            System.out.println("ajout refusee");
                        break;
                    case 2:
                        managmenetTache.modifier(utilisateur.getId());
                        break;
                    case 3:
                        managmenetTache.afficher(utilisateur.getId());
                        break;
                    case 4:
                        managmenetTache.supprimer(utilisateur.getId());
                        break;
                    case 5:
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("\u001b[31mLe nombre incorrect\u001b[0m");
                }
            } catch (InputMismatchException inputMismatchExceptioni) {
                System.out.println("\u001b[31mle entree est incorrect\u001b[0m");
            }

        }

    }
}
