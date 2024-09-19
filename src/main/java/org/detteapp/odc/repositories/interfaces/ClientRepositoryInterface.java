package org.detteapp.odc.repositories.interfaces;

import org.detteapp.odc.entities.ClientEntity;

import java.util.Optional;

public interface ClientRepositoryInterface extends  RepositoryInt<ClientEntity>{
    Optional<ClientEntity> findBySurname(String surname);
    Optional<ClientEntity> findByTelephone(String telephone);

}
