package org.detteapp.odc.collections;

import org.detteapp.odc.entities.DetteArticle;
import org.detteapp.odc.repositories.interfaces.DetteArticleInterface;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Profile("collection")
@Repository("collection")
public class DetteArticleRepositoryCollection extends CollectionRepository<DetteArticle> implements DetteArticleInterface {
    public DetteArticleRepositoryCollection(Collection<DetteArticle> t) {
        super(t);
    }

    @Override
    public Optional<DetteArticle> findByDette(int id) {
        return Optional.empty();
    }
}
