package org.detteapp.odc.repositories.interfaces;

import java.util.Optional;
import org.detteapp.odc.entities.ArticleEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepositoryInterface extends RepositoryInt<ArticleEntity> {
    public Optional<ArticleEntity> findByLibelle(String title);

}
