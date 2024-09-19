package org.detteapp.odc.repositories.interfaces;

import org.detteapp.odc.entities.DetteArticle;

import java.util.Optional;

public interface DetteArticleInterface extends RepositoryInt<DetteArticle> {
    Optional<DetteArticle> findByDette(int id);
}
