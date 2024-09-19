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
    public Optional<DetteArticle> findByDette(int id) {
        return Optional.empty();
    }
}
