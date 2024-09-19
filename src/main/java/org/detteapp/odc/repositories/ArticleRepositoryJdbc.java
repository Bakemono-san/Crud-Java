package org.detteapp.odc.repositories;

import org.detteapp.odc.config.DatabaseConfig;
import org.detteapp.odc.entities.ArticleEntity;
import org.detteapp.odc.repositories.interfaces.ArticleRepositoryInterface;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

@Profile("jdbc")
@Repository
public class ArticleRepositoryJdbc extends JdbcRepository<ArticleEntity> implements ArticleRepositoryInterface {
    public ArticleRepositoryJdbc(DatabaseConfig databaseConfig) {
        super(databaseConfig, "Articles",  ArticleEntity.class);
    }

    @Override
    protected String getInsertSql() {
        return "INSERT INTO " + getTableName() + " (libelle, prix, quantity, seuil) VALUES (?, ?, ?, ?)";
    }


    @Override
    protected String getUpdateSql() {
        return "UPDATE " + getTableName() + " SET libelle = ?, prix = ?,quantity = ? , seuil = ? WHERE id = ?";
    }

    @Override
    protected void setParametersForSave(PreparedStatement preparedStatement, ArticleEntity entity) throws SQLException {
        preparedStatement.setString(1,entity.getLibelle());
        preparedStatement.setBigDecimal(2,entity.getPrix());
        preparedStatement.setInt(3,entity.getQuantity());
        preparedStatement.setInt(4,entity.getSeuil());
    }

    @Override
    protected int setParametersForUpdate(PreparedStatement preparedStatement, ArticleEntity entity) throws SQLException {
        int paramIndex = 1;
        preparedStatement.setString(paramIndex++, entity.getLibelle());
        preparedStatement.setBigDecimal(paramIndex++, entity.getPrix());
        preparedStatement.setInt(paramIndex++, entity.getQuantity());
        preparedStatement.setInt(paramIndex++, entity.getSeuil());
        return paramIndex;
    }

    @Override
    public Optional<ArticleEntity> findByLibelle(String title) {
        return Optional.empty();
    }
}
