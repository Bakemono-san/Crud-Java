package org.detteapp.odc.views;

import org.detteapp.odc.entities.DetteArticle;
import org.detteapp.odc.entities.DetteEntity;
import org.detteapp.odc.services.ClientService;
import org.detteapp.odc.services.DetteArticleService;
import org.detteapp.odc.services.DetteService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Dette {

    public final DetteService detteService;
    public final DetteArticles detteArticlesView;

    public Dette(DetteService detteService, DetteArticles detteArticlesView) {
        this.detteService = detteService;
        this.detteArticlesView = detteArticlesView;
    }

    public void creer(){
        Scanner scanner = new Scanner(System.in);
        DetteEntity detteEntity = new DetteEntity();
        System.out.print("Entrez l'ID de cette dette : ");
        int id = scanner.nextInt();
        detteEntity.setId(id);

        System.out.println("Entre le montant de la dette : ");
        float montant = scanner.nextFloat();
        detteEntity.setMontant(montant);

        System.out.println("Entre l'id du client : ");
        int clientId = scanner.nextInt();
        detteEntity.setClientId(clientId);
        detteService.save(detteEntity);

        int response = 0;
        do{
            detteArticlesView.creer(id);

            System.out.println("Voulez vous ajouter un article a la dette 1 pour ajouter et tout autre caractere pour arreter : ");
            response = scanner.nextInt();
        }while(response == 1);


        System.out.println("\n---- Dette ajoutee ----");
        System.out.println("Id : " + detteEntity.getId());
        System.out.println("Montant de la dette : "+montant);
        System.out.println("Id du client : "+clientId);
        System.out.println("\n-----------------------");
    }

    public void afficher(){
        Collection<DetteEntity> dettes = detteService.findall();

        System.out.println("\n---- Liste des Dettes ----");
        for (DetteEntity detteEntity : dettes) {
            System.out.println("Id dette : "+detteEntity.getId());
            System.out.println("Montant de la dette : "+detteEntity.getMontant());
            System.out.println("Id du client : "+detteEntity.getClientId());
            System.out.println("------------------------------");
        }
    }

    public void rechercher(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entre l'id de la dette : ");
        int detteId = scanner.nextInt();

        Optional<DetteEntity> detteOpt = detteService.find(detteId);
        if(detteOpt.isPresent()){
            DetteEntity detteEntity = detteOpt.get();
            System.out.println("Id dette : "+detteEntity.getId());
            System.out.println("Montant de la dette : "+detteEntity.getMontant());
            System.out.println("Id du client : "+detteEntity.getClientId());
            System.out.println("------------------------------");
        }else{
            System.out.println("Aucune dette avec l'id : "+detteId);
        }
    }

    public void supprimer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entre l'id de la dette : ");
        int detteId = scanner.nextInt();

        DetteEntity dette = detteService.delete(detteId);

        if(dette != null){
            System.out.println("Dette supprimee avec succes id : "+detteId);
        }else {
            System.out.println("Aucune dette avec l'id : "+detteId);
        }
    }

    public void modifier(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entre l'id de la dette : ");
        int detteId = scanner.nextInt();

        Optional<DetteEntity> detteOpt = detteService.find(detteId);
        if(detteOpt.isPresent()){
            DetteEntity detteEntity = detteOpt.get();
            scanner.nextLine();


            System.out.println("Entrez le nouveau montant : "+detteEntity.getMontant());
            float montant = scanner.nextFloat();
            detteEntity.setMontant(montant);

            detteService.save(detteEntity);

            System.out.println("\n---- Dette modifie ----");
            System.out.println("Id dette : "+detteEntity.getId());
            System.out.println("Montant de la dette : "+detteEntity.getMontant());
            System.out.println("Id du client : "+detteEntity.getClientId());
        }else {
            System.out.println("Aucune dette avec l'id : "+detteId);
        }
    }
}
