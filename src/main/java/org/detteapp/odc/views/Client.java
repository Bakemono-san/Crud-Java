package org.detteapp.odc.views;

import org.detteapp.odc.entities.ClientEntity;
import org.detteapp.odc.services.ClientService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Client {

    protected final ClientService clientService;

    public Client(ClientService clientService) {
        this.clientService = clientService;
    }

    public void creer() {
        Scanner scanner = new Scanner(System.in);

        ClientEntity clientEntity = new ClientEntity();

        // Récupération des informations depuis la console
        System.out.print("Entrez l'ID du client : ");
        int id = scanner.nextInt();
        clientEntity.setId(id);

        scanner.nextLine();

        System.out.print("Entrez le surnom de l'article : ");
        String surnom = scanner.nextLine();
        clientEntity.setSurname(surnom);

        System.out.print("Entrez l'email du client : ");
        String email = scanner.nextLine();
        clientEntity.setEmail(email);

        System.out.print("Entrez le numero de telephone : ");
        String phone = scanner.nextLine();
        clientEntity.setPhone(phone);

        System.out.print("Entrez l'addresse du client : ");
        String addresse = scanner.nextLine();
        clientEntity.setAddresse(addresse);

        System.out.print("Entrez l'id de l'utilisateur rattachee au client : ");
        int userId = scanner.nextInt();
        clientEntity.setUserId(userId);

        System.out.print("Entrez le montant max des dettes du client : ");
        int montant_max = scanner.nextInt();
        clientEntity.setMontant_max(montant_max);

        System.out.print("Entrez l'id de la Categorie du client : ");
        int categorie = scanner.nextInt();
        clientEntity.setCategorie_id(categorie);

        clientService.save(clientEntity);

        // Affichage de confirmation
        System.out.println("\n--- Client ajouté ---");
        System.out.println("ID : " + clientEntity.getId());
        System.out.println("surname : " + clientEntity.getSurname());
        System.out.println("addresse : " + clientEntity.getAddresse());
        System.out.println("Telephone : " + clientEntity.getPhone());
        System.out.println("userId : " + clientEntity.getUserId());
        System.out.println("email : " + clientEntity.getEmail());
        System.out.println("montant_max : " + clientEntity.getMontant_max());
        System.out.println("categorie_id : " + clientEntity.getCategorie_id());

    }

    // Méthode pour afficher tous les articles
    public void afficher() {
        Collection<ClientEntity> clients = clientService.findall();

        System.out.println("\n--- Liste des clients ---");
        for (ClientEntity client : clients) {
            System.out.println("ID : " + client.getId());
            System.out.println("surname : " + client.getSurname());
            System.out.println("addresse : " + client.getAddresse());
            System.out.println("Telephone : " + client.getPhone());
            System.out.println("userId : " + client.getUserId());
            System.out.println("email : " + client.getEmail());
            System.out.println("montant_max : " + client.getMontant_max());
            System.out.println("categorie_id : " + client.getCategorie_id());
            System.out.println("--------------------------");
        }
    }

    // Méthode pour rechercher un article par ID
    public void rechercher() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'ID du client : ");
        int id = scanner.nextInt();

        Optional<ClientEntity> clientOpt = clientService.find(id);
        if (clientOpt.isPresent()) {
            ClientEntity client = clientOpt.get();
            System.out.println("\n--- Article trouvé ---");
            System.out.println("ID : " + client.getId());
            System.out.println("surname : " + client.getSurname());
            System.out.println("addresse : " + client.getAddresse());
            System.out.println("Telephone : " + client.getPhone());
            System.out.println("userId : " + client.getUserId());
            System.out.println("email : " + client.getEmail());
            System.out.println("montant_max : " + client.getMontant_max());
            System.out.println("categorie_id : " + client.getCategorie_id());
        } else {
            System.out.println("Aucun Client trouvé avec l'ID : " + id);
        }
    }

    // Méthode pour supprimer un article par ID
    public void supprimer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'ID du client à supprimer : ");
        int id = scanner.nextInt();

        ClientEntity success = clientService.delete(id);
        if (success != null) {
            System.out.println("Client supprimé avec succès.");
        } else {
            System.out.println("Aucun Client trouvé avec l'ID : " + id);
        }
    }

    // Méthode pour modifier un article
    public void modifier() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'ID de l'article à modifier : ");
        int id = scanner.nextInt();

        Optional<ClientEntity> clientOpt = clientService.find(id);
        if (clientOpt.isPresent()) {
            ClientEntity client = clientOpt.get();
            scanner.nextLine(); // Consommer le retour de ligne

            // Modifier le libellé
            System.out.print("Entrez le nouveau surname (actuel: " + client.getSurname() + ") : ");
            String surname = scanner.nextLine();
            client.setSurname(surname);

            // Modifier le prix
            System.out.print("Entrez la nouvelle addresse (actuel: " + client.getAddresse() + ") : ");
            String addresse = scanner.nextLine();
            client.setAddresse(addresse);

            // Modifier la quantité
            System.out.print("Entrez le nouveau numero de telephone (actuelle: " + client.getPhone() + ") : ");
            String telephone = scanner.nextLine();
            client.setPhone(telephone);

            // Modifier le seuil
            System.out.print("Entrez le nouveau email (actuel: " + client.getEmail() + ") : ");
            String email = scanner.nextLine();
            client.setEmail(email);

            System.out.print("Entrez le nouveau montant_max (actuel: " + client.getMontant_max() + ") : ");
            int montant_max = scanner.nextInt();
            client.setMontant_max(montant_max);

            System.out.print("Entrez le nouveau categorie_id (actuel: " + client.getCategorie_id() + ") : ");
            int categorie_id = scanner.nextInt();
            client.setCategorie_id(categorie_id);




            clientService.save(client); // Sauvegarder les modifications

            System.out.println("ID : " + client.getId());
            System.out.println("surname : " + client.getSurname());
            System.out.println("addresse : " + client.getAddresse());
            System.out.println("Telephone : " + client.getPhone());
            System.out.println("userId : " + client.getUserId());
            System.out.println("email : " + client.getEmail());
            System.out.println("montant_max : " + client.getMontant_max());
            System.out.println("categorie_id : " + client.getCategorie_id());
        } else {
            System.out.println("Aucun article trouvé avec l'ID : " + id);
        }
    }
}
