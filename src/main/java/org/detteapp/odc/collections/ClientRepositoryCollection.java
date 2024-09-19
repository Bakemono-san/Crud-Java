package org.detteapp.odc.collections;

import org.detteapp.odc.entities.ArticleEntity;
import org.detteapp.odc.entities.ClientEntity;
import org.detteapp.odc.repositories.interfaces.ArticleRepositoryInterface;
import org.detteapp.odc.repositories.interfaces.ClientRepositoryInterface;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Profile("collection")
@Repository("collection")
public class ClientRepositoryCollection extends CollectionRepository<ClientEntity> implements ClientRepositoryInterface {
    public ClientRepositoryCollection(Collection<ClientEntity> t) {
        super(t);
    }


    @Override
    public Optional<ClientEntity> findBySurname(String surname) {
        return Optional.empty();
    }

    @Override
    public Optional<ClientEntity> findByTelephone(String telephone) {
        return Optional.empty();
    }
}
