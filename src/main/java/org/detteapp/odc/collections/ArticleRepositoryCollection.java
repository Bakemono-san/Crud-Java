package org.detteapp.odc.collections;

import org.detteapp.odc.entities.ArticleEntity;
import org.detteapp.odc.repositories.interfaces.ArticleRepositoryInterface;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Profile("collection")
@Repository("collection")
public class ArticleRepositoryCollection extends CollectionRepository<ArticleEntity> implements ArticleRepositoryInterface {
    public ArticleRepositoryCollection(Collection<ArticleEntity> t) {
        super(t);
    }

    @Override
    public Optional<ArticleEntity> findByLibelle(String title) {
        return  findall().stream().filter(article -> article.getLibelle().equals(title)).findFirst();
    }

}
