package org.detteapp.odc.views;

import org.detteapp.odc.entities.DetteArticle;
import org.detteapp.odc.services.DetteArticleService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

@Component
public class DetteArticles {
    private final DetteArticleService articleDetteService;

    public DetteArticles(DetteArticleService detteArticle) {
        this.articleDetteService = detteArticle;
    }

    public void creer(int detteID){
        Scanner scanner = new Scanner(System.in);

        DetteArticle detteArticle = new DetteArticle();

        System.out.println("veuillez saisir l'id de l'article: ");
        int articleId = scanner.nextInt();
        detteArticle.setArticleId(articleId);

        detteArticle.setDetteId(detteID);

        System.out.println("Veuillez saisir la quantite: ");
        int quantite = scanner.nextInt();
        detteArticle.setQuantite(quantite);

        System.out.println("Veuillez saisir le prix de vente: ");
        float prix = scanner.nextFloat();
        detteArticle.setPrixVente(prix);

        articleDetteService.save(detteArticle);

        System.out.println("------- DetteArticle creer ---------");
        System.out.println("ID: " + detteArticle.getId());
        System.out.println("Quantite: " + detteArticle.getQuantite());
        System.out.println("Prix vente: " + detteArticle.getPrixVente());
        System.out.println("Article id : " + detteArticle.getArticleId());
        System.out.println("Dette id : " + detteArticle.getDetteId());
    }

    public void afficher(){
        Collection<DetteArticle> articleDettes = articleDetteService.getAll();

        System.out.println("------- Liste des articles Dettes -------");
        for(DetteArticle article : articleDettes){
            System.out.println("ID: " + article.getArticleId());
            System.out.println("Quantite: " + article.getQuantite());
            System.out.println("Prix vente: " + article.getPrixVente());
            System.out.println("Article id : " + article.getArticleId());
            System.out.println("Dette id : " + article.getDetteId());
            System.out.println("-------------------------------------------");
        }
    }

    public void rechercher(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir l'id de l'article dette: ");
        int articleId = scanner.nextInt();

        Optional<DetteArticle> detteArticleOpt = articleDetteService.find(articleId);
        if(detteArticleOpt.isPresent()){
            DetteArticle detteArticle = detteArticleOpt.get();

            System.out.println("Id: " + detteArticle.getArticleId());
            System.out.println("Quantite: " + detteArticle.getQuantite());
            System.out.println("Prix vente: " + detteArticle.getPrixVente());
            System.out.println("Article id : " + detteArticle.getArticleId());
            System.out.println("Dette id : " + detteArticle.getDetteId());
            System.out.println("-------------------------------------------");
        }else{
            System.out.println("Aucun article dette trouvee avec l'id "+articleId+".");
        }
    }

    public void supprimer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir l'id de l'article dette: ");
        int articleId = scanner.nextInt();

        DetteArticle detteArticle = articleDetteService.delete(articleId);

        if(detteArticle != null){
            System.out.println("article dette supprimee avec succes");
        }else{
            System.out.println("article dette n'existe pas");
        }
    }
}
