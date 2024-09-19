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
    protected String getInsertSql() {
        return "INSERT INTO " + getTableName() + " (surname, email, phone, userId,addresse,montant_max,categorie_id) VALUES (?, ?, ?, ?, ?,?,?)";
    }

    @Override
    protected String getUpdateSql() {
        return "UPDATE " + getTableName() + " SET surname = ?, email = ?,phone = ? , addresse = ? , montant_max = ? , categorie_id = ? WHERE id = ?";
    }

    @Override
    protected void setParametersForSave(PreparedStatement preparedStatement, ClientEntity entity) throws SQLException {
        preparedStatement.setString(1,entity.getSurname());
        preparedStatement.setString(2,entity.getEmail());
        preparedStatement.setString(3,entity.getPhone());
        preparedStatement.setInt(4,entity.getUserId());
        preparedStatement.setString(5,entity.getAddresse());
        preparedStatement.setInt(6,entity.getMontant_max());
        preparedStatement.setInt(7,entity.getCategorie_id());
    }

    @Override
    protected int setParametersForUpdate(PreparedStatement preparedStatement, ClientEntity entity) throws SQLException {
        int paramIndex = 1;
        preparedStatement.setString(paramIndex++, entity.getSurname());
        preparedStatement.setString(paramIndex++, entity.getEmail());
        preparedStatement.setString(paramIndex++, entity.getPhone());
        preparedStatement.setString(paramIndex++, entity.getAddresse());
        preparedStatement.setInt(paramIndex++, entity.getMontant_max());
        preparedStatement.setInt(paramIndex++, entity.getCategorie_id());
        return paramIndex;
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
