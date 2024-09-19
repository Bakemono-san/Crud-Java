package org.detteapp.odc.views;

import org.detteapp.odc.entities.ArticleEntity;
import org.detteapp.odc.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Article {

    protected final ArticleService articleService;

    public Article(ArticleService articleService) {
        this.articleService = articleService;
    }


    // Méthode pour créer un nouvel article
    public void creer() {
        Scanner scanner = new Scanner(System.in);

        ArticleEntity articleEntity = new ArticleEntity();

        // Récupération des informations depuis la console
        System.out.print("Entrez l'ID de l'article : ");
        int id = scanner.nextInt();
        articleEntity.setId(id);

        scanner.nextLine(); // Consommer le retour de ligne

        System.out.print("Entrez le libellé de l'article : ");
        String libelle = scanner.nextLine();
        articleEntity.setLibelle(libelle);

        System.out.print("Entrez le prix de l'article : ");
        BigDecimal prix = scanner.nextBigDecimal();
        articleEntity.setPrix(prix);

        System.out.print("Entrez la quantité disponible : ");
        int quantity = scanner.nextInt();
        articleEntity.setQuantity(quantity);

        System.out.print("Entrez le seuil minimal de l'article : ");
        int seuil = scanner.nextInt();
        articleEntity.setSeuil(seuil);

        articleService.save(articleEntity);

        // Affichage de confirmation
        System.out.println("\n--- Article ajouté ---");
        System.out.println("ID : " + articleEntity.getId());
        System.out.println("Libellé : " + articleEntity.getLibelle());
        System.out.println("Prix : " + articleEntity.getPrix());
        System.out.println("Quantité : " + articleEntity.getQuantity());
        System.out.println("Seuil : " + articleEntity.getSeuil());
    }

    // Méthode pour afficher tous les articles
    public void afficher() {
        Collection<ArticleEntity> articles = articleService.findall();

        System.out.println("\n--- Liste des articles ---");
        for (ArticleEntity article : articles) {
            System.out.println("ID : " + article.getId());
            System.out.println("Libellé : " + article.getLibelle());
            System.out.println("Prix : " + article.getPrix());
            System.out.println("Quantité : " + article.getQuantity());
            System.out.println("Seuil : " + article.getSeuil());
            System.out.println("--------------------------");
        }
    }

    // Méthode pour rechercher un article par ID
    public void rechercher() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'ID de l'article à rechercher : ");
        int id = scanner.nextInt();

        Optional<ArticleEntity> articleOpt = articleService.find(id);
        if (articleOpt.isPresent()) {
            ArticleEntity article = articleOpt.get();
            System.out.println("\n--- Article trouvé ---");
            System.out.println("ID : " + article.getId());
            System.out.println("Libellé : " + article.getLibelle());
            System.out.println("Prix : " + article.getPrix());
            System.out.println("Quantité : " + article.getQuantity());
            System.out.println("Seuil : " + article.getSeuil());
        } else {
            System.out.println("Aucun article trouvé avec l'ID : " + id);
        }
    }

    // Méthode pour supprimer un article par ID
    public void supprimer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'ID de l'article à supprimer : ");
        int id = scanner.nextInt();

        ArticleEntity success = articleService.delete(id);
        if (success != null) {
            System.out.println("Article supprimé avec succès.");
        } else {
            System.out.println("Aucun article trouvé avec l'ID : " + id);
        }
    }

    // Méthode pour modifier un article
    public void modifier() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'ID de l'article à modifier : ");
        int id = scanner.nextInt();

        Optional<ArticleEntity> articleOpt = articleService.find(id);
        if (articleOpt.isPresent()) {
            ArticleEntity article = articleOpt.get();
            scanner.nextLine(); // Consommer le retour de ligne

            // Modifier le libellé
            System.out.print("Entrez le nouveau libellé (actuel: " + article.getLibelle() + ") : ");
            String libelle = scanner.nextLine();
            article.setLibelle(libelle);

            // Modifier le prix
            System.out.print("Entrez le nouveau prix (actuel: " + article.getPrix() + ") : ");
            BigDecimal prix = scanner.nextBigDecimal();
            article.setPrix(prix);

            // Modifier la quantité
            System.out.print("Entrez la nouvelle quantité (actuelle: " + article.getQuantity() + ") : ");
            int quantity = scanner.nextInt();
            article.setQuantity(quantity);

            // Modifier le seuil
            System.out.print("Entrez le nouveau seuil (actuel: " + article.getSeuil() + ") : ");
            int seuil = scanner.nextInt();
            article.setSeuil(seuil);

            articleService.save(article); // Sauvegarder les modifications

            System.out.println("\n--- Article modifié ---");
            System.out.println("ID : " + article.getId());
            System.out.println("Libellé : " + article.getLibelle());
            System.out.println("Prix : " + article.getPrix());
            System.out.println("Quantité : " + article.getQuantity());
            System.out.println("Seuil : " + article.getSeuil());
        } else {
            System.out.println("Aucun article trouvé avec l'ID : " + id);
        }
    }
}
