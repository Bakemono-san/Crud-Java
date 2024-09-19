package org.detteapp.odc.repositories;

import org.detteapp.odc.config.DatabaseConfig;
import org.detteapp.odc.entities.ClientEntity;
import org.detteapp.odc.repositories.interfaces.ClientRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class ClientRepositoryJdbc extends JdbcRepository<ClientEntity> implements ClientRepositoryInterface {
    public ClientRepositoryJdbc(DatabaseConfig databaseConfig) {
        super(databaseConfig, "Clients", ClientEntity.class);
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
