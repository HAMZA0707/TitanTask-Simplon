package titantask.view;

import titantask.controller.ManagementTache;
import titantask.model.Utilisateur;
import titantask.services.ExportImport;
import java.lang.Thread;


import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionUtilisateur {
    static int subMenuUser(Utilisateur utilisateur) throws InterruptedException {
        ManagementTache managmenetTache = new ManagementTache();
        ExportImport ei=new ExportImport();
        System.out.println("\u001b[32m============================================================================\u001b[0m");
        System.out.println("\t\t\t\t\t \033[1;34mBienvenue "+utilisateur.getNom()+" dans votre espace personnel\033[0m");
        while (true) {
            System.out.println("\u001b[32m============================================================================\u001b[0m");
            System.out.println("\033[1;34mChoisis une option\033[0m");
            Thread.sleep(500);
            System.out.println("\u001B[38;5;214m1. Ajouter une tache");
            Thread.sleep(500);
            System.out.println("\u001B[38;5;214m2. Modifier une tache");
            Thread.sleep(500);
            System.out.println("\u001B[38;5;214m3. Afficher list des taches");
            Thread.sleep(500);
            System.out.println("\u001B[38;5;214m4. Supprimer une tache");
            Thread.sleep(500);
            System.out.println("\u001B[38;5;214m5. Filter les taches par categorie");
            Thread.sleep(500);
            System.out.println("\u001B[38;5;214m6. Trier les Taches par priorite ou date creation");
            Thread.sleep(500);
            System.out.println("\u001B[38;5;214m7. Exporter vos taches");
            Thread.sleep(500);
            System.out.println("\u001B[38;5;214m8. Importer list des taches\u001B[0m");
            Thread.sleep(500);
            System.out.println("\u001b[31m9. Deconnection\u001b[0m");
            Thread.sleep(500);
            System.out.print("Entrez le numéro de votre choix: ");
            try {
                Scanner scanner = new Scanner(System.in);
                int inputChoix = scanner.nextInt();
                switch (inputChoix) {
                    case 1:
                        if (managmenetTache.ajouter(utilisateur) == 1){
                            System.out.println("\u001b[32majout success\u001b[0m");
                        }else
                            System.out.println("\u001b[31majout refusee\u001b[31m");
                        break;
                    case 2:
                        managmenetTache.modifier(utilisateur);
                        break;
                    case 3:
                        managmenetTache.afficher(utilisateur);
                        break;
                    case 4:
                        managmenetTache.supprimer(utilisateur);
                        break;
                    case 5:
                        managmenetTache.filtrerCategorie(utilisateur);
                        break;
                    case 6:
                        managmenetTache.tri(utilisateur);
                        break;
                    case 7:
                        if (ei.export_Tache(utilisateur.getId())){
                            System.out.println("\u001b[32mExport reussi\u001b[0m");
                        }else
                            System.out.println("\u001b[31mEchec Export\u001b[0m");
                        break;
                    case 8:

                        if (ei.import_tache(utilisateur.getId())){
                            System.out.println("Import reussi");
                        }else
                            System.out.println("\u001b[31mEchec Import\u001b[0m");
                        break;
                    case 9:
                        return 1;
                    default:
                        System.out.println("\u001b[31mLe nombre incorrect\u001b[0m");
                }
            } catch (InputMismatchException inputMismatchExceptioni) {
                System.out.println("\u001b[31mle entree est incorrect\u001b[0m");
            }

        }

    }
}