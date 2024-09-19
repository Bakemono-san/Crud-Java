package org.detteapp.odc.repositories.interfaces;

import org.detteapp.odc.entities.DetteEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetteRepositoryInterface extends  RepositoryInt<DetteEntity>{
    Optional<DetteEntity> findByClient(int id);

}
