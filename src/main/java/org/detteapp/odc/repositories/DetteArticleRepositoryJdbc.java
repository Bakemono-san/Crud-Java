package org.detteapp.odc.repositories;

import org.detteapp.odc.config.DatabaseConfig;
import org.detteapp.odc.entities.DetteArticle;
import org.detteapp.odc.repositories.interfaces.DetteArticleInterface;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class DetteArticleRepositoryJdbc extends JdbcRepository<DetteArticle> implements DetteArticleInterface {
    protected DetteArticleRepositoryJdbc(DatabaseConfig databaseConfig) {
        super(databaseConfig, "DetteArticles", DetteArticle.class);
    }

    @Override
    protected String getInsertSql() {
        return "INSERT INTO " + getTableName() + " (articleId, detteId, quantite, prixVente) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateSql() {
        return "UPDATE " + getTableName() + " SET articleId = ?, detteId = ?,quantite = ? , prixVente = ?  WHERE id = ?";
    }

    @Override
    protected void setParametersForSave(PreparedStatement preparedStatement, DetteArticle entity) throws SQLException {
        preparedStatement.setInt(1,entity.getArticleId());
        preparedStatement.setInt(2,entity.getDetteId());
        preparedStatement.setInt(3,entity.getQuantite());
        preparedStatement.setFloat(4,entity.getPrixVente());
    }

    @Override
    protected int setParametersForUpdate(PreparedStatement preparedStatement, DetteArticle entity) throws SQLException {
        int paramIndex = 1;
        preparedStatement.setInt(paramIndex++, entity.getArticleId());
        preparedStatement.setInt(paramIndex++, entity.getDetteId());
        preparedStatement.setInt(paramIndex++, entity.getQuantite());
        preparedStatement.setFloat(paramIndex++, entity.getPrixVente());
        return paramIndex;
    }

    @Override
    public Optional<DetteArticle> findByDette(int id) {
        return Optional.empty();
    }
}
