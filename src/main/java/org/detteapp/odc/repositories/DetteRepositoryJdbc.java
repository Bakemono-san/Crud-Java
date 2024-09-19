package org.detteapp.odc.repositories;

import org.detteapp.odc.config.DatabaseConfig;
import org.detteapp.odc.entities.DetteEntity;
import org.detteapp.odc.repositories.interfaces.DetteRepositoryInterface;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class DetteRepositoryJdbc extends JdbcRepository<DetteEntity> implements DetteRepositoryInterface {
    public DetteRepositoryJdbc(DatabaseConfig databaseConfig) {
        super(databaseConfig, "Dettes", DetteEntity.class);
    }
    
    @Override
    public Optional<DetteEntity> findByClient(int id) {
        return Optional.empty();
    }
}
