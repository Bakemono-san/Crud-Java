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
    public Optional<ArticleEntity> findByLibelle(String title) {
        return Optional.empty();
    }
}
