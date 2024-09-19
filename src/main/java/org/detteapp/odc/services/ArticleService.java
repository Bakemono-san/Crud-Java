package org.detteapp.odc.services;

import org.detteapp.odc.entities.ArticleEntity;
import org.detteapp.odc.repositories.interfaces.ArticleRepositoryInterface;
import org.detteapp.odc.services.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ArticleService implements ServiceInterface<ArticleEntity> {

    public final ArticleRepositoryInterface repository;

    @Autowired
    public ArticleService(ArticleRepositoryInterface paramRepository) {
        repository = paramRepository;
    }

    @Override
    public ArticleEntity save(ArticleEntity entity) {
        return repository.save(entity);
    }

    @Override
    public Collection<ArticleEntity> findall() {
        return repository.findall();
    }

    @Override
    public ArticleEntity update(ArticleEntity articleEntity) {
        return repository.update(articleEntity);
    }

    @Override
    public ArticleEntity delete(int id) {
        return repository.delete(id);
    }

    @Override
    public Optional<ArticleEntity> find(int articleEntity) {
        return repository.find(articleEntity);
    }
}
