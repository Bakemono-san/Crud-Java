package org.detteapp.odc.views;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {

    protected final Article article;

    public Menu(Article article) {
        this.article = article;
    }

    public void getMenu(){

    Scanner scanner = new Scanner(System.in);
    int choice;

    do {
        System.out.println("Welcome to the dette app");
        System.out.println("\n--- Menu Principal ---");
        System.out.println("1 - Ajouter Article");
        System.out.println("2 - Lister Articles");
        System.out.println("3 - rechercher Articles");
        System.out.println("4 - supprimer Articles");
        System.out.println("5 - modifier Articles");
        System.out.println("6 - Quitter");
        System.out.print("Choisissez une option : ");

        choice = scanner.nextInt();
        scanner.nextLine();

        switch(choice){
            case 1:
                article.creer();
                break;
            case 2:
                article.afficher();
                break;
            case 3:
                article.rechercher();
                break;
            case 4:
                article.supprimer();
                break;
            case 5:
                article.modifier();
                break;
            case 6:
                System.out.println("Au revoir !");
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
                break;
        }
    } while (choice != 6);

    scanner.close();
    }
}
