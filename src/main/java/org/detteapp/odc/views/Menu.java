package org.detteapp.odc.views;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {

    protected final Article article;
    protected final Client client;
    protected final Dette dette;
    public Menu(Article article, Client client, Dette dette) {
        this.article = article;
        this.client = client;
        this.dette = dette;
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
        System.out.println("6 - Ajouter Client");
        System.out.println("7 - Lister Clients");
        System.out.println("8 - rechercher Clients");
        System.out.println("9 - supprimer Clients");
        System.out.println("10 - modifier Clients");
        System.out.println("11 - Ajouter Dette");
        System.out.println("12 - Lister Dettes");
        System.out.println("13 - rechercher Dettes");
        System.out.println("14 - supprimer Dettes");
        System.out.println("15 - modifier Dettes");
        System.out.println("16 - Quitter");
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
                client.creer();
                break;
            case 7:
                client.afficher();
                break;
            case 8:
                client.rechercher();
                break;
            case 9:
                client.supprimer();
                break;
            case 10:
                client.modifier();
                break;
            case 11:
                dette.creer();
                break;
            case 12:
                dette.afficher();
                break;
            case 13:
                dette.rechercher();
                break;
            case 14:
                dette.supprimer();
                break;
            case 15:
                dette.modifier();
                break;
            case 16:
                System.out.println("Au revoir !");
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
                break;
        }
    } while (choice != 11);

    scanner.close();
    }
}
