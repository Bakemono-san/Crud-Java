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
    protected String getInsertSql() {
        return "INSERT INTO " + getTableName() + " (montant, clientId) VALUES (?, ?)";

    }

    @Override
    protected String getUpdateSql() {
        return "UPDATE " + getTableName() + " SET montant = ?, clientId = ? WHERE id = ?";
    }

    @Override
    protected void setParametersForSave(PreparedStatement preparedStatement, DetteEntity entity) throws SQLException {
        preparedStatement.setFloat(1,entity.getMontant());
        preparedStatement.setInt(2,entity.getClientId());
    }

    @Override
    protected int setParametersForUpdate(PreparedStatement preparedStatement, DetteEntity entity) throws SQLException {
        int paramIndex = 1;
        preparedStatement.setFloat(paramIndex++, entity.getMontant());
        preparedStatement.setInt(paramIndex++, entity.getClientId());
        return paramIndex;
    }

    @Override
    public Optional<DetteEntity> findByClient(int id) {
        return Optional.empty();
    }
}
