package titantask.view;

import titantask.controller.ManagmenetUtilisateur;
import titantask.model.Utilisateur;

import java.util.InputMismatchException;
import java.util.Scanner;

import static titantask.view.GestionAdmin.subMenuAdmin;

public class MainClass {
    public static void main(String[] args) {
        ManagmenetUtilisateur managmenetUtilisateur = new ManagmenetUtilisateur();
        Utilisateur utilisateur;
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("$$$$$$$$\\ $$\\   $$\\                                $$$$$$\\            $$\\                           $$\\       \n" +
                "\\__$$  __|\\__|  $$ |                              $$  __$$\\           $$ |                          $$ |      \n" +
                "   $$ |   $$\\ $$$$$$\\    $$$$$$\\  $$$$$$$\\        $$ /  \\__| $$$$$$$\\ $$$$$$$\\   $$$$$$\\   $$$$$$\\  $$ |      \n" +
                "   $$ |   $$ |\\_$$  _|   \\____$$\\ $$  __$$\\       \\$$$$$$\\  $$  _____|$$  __$$\\ $$  __$$\\ $$  __$$\\ $$ |      \n" +
                "   $$ |   $$ |  $$ |     $$$$$$$ |$$ |  $$ |       \\____$$\\ $$ /      $$ |  $$ |$$ /  $$ |$$ /  $$ |$$ |      \n" +
                "   $$ |   $$ |  $$ |$$\\ $$  __$$ |$$ |  $$ |      $$\\   $$ |$$ |      $$ |  $$ |$$ |  $$ |$$ |  $$ |$$ |      \n" +
                "   $$ |   $$ |  \\$$$$  |\\$$$$$$$ |$$ |  $$ |      \\$$$$$$  |\\$$$$$$$\\ $$ |  $$ |\\$$$$$$  |\\$$$$$$  |$$ |      \n" +
                "   \\__|   \\__|   \\____/  \\_______|\\__|  \\__|       \\______/  \\_______|\\__|  \\__| \\______/  \\______/ \\__| ");
        System.out.println();
        System.out.println("\033[1;32m\033[1mBienvenue dans votre TaskManager !\033[0m ");
        int i = 0;
        while (i != 2) {
            System.out.println("\u001b[32m=============================== Connectez-vous ==============================\u001b[0m");
            try {
                utilisateur = managmenetUtilisateur.login();
                if (utilisateur != null) {
                    System.out.println("\u001b[32mVous etes connecté avec succès\u001b[0m");
                    //
                    if (utilisateur.isRole()) {
                        if(subMenuAdmin(utilisateur)==1) {
                            i=1;
                        }
                    } else {
                        if(GestionUtilisateur.subMenuUser(utilisateur)==1) {
                            i=1;
                        }
                    }
                    //
                } else {
                    System.out.println("\u001b[31mLe mail ou le mot de passe est incorrect || le compte n'existe pas \u001b[0m");
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Les données que vous avez saisie sont incorrecte ");
                scanner.nextLine();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}