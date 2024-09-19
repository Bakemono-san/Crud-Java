package org.detteapp.odc.collections;

import org.detteapp.odc.entities.DetteEntity;
import org.detteapp.odc.repositories.interfaces.DetteRepositoryInterface;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Profile("collection")
@Repository("collection")
public class DetteRepositoryCollection extends CollectionRepository<DetteEntity> implements DetteRepositoryInterface {
    public DetteRepositoryCollection(Collection<DetteEntity> t) {
        super(t);
    }

    @Override
    public Optional<DetteEntity> findByClient(int id) {
        return Optional.empty();
    }
}
