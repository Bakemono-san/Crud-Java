package org.detteapp.odc.services;

import org.detteapp.odc.entities.DetteArticle;
import org.detteapp.odc.repositories.interfaces.DetteArticleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DetteArticleService {
    public static DetteArticleInterface detteRepository;

    @Autowired
    public DetteArticleService(DetteArticleInterface detteRepository) {
        this.detteRepository = detteRepository;
    }

    public DetteArticle save(DetteArticle detteArticle) {
        return  detteRepository.save(detteArticle);
    }

    public Collection<DetteArticle> getAll() {
        return detteRepository.findall();
    }

    public DetteArticle update(DetteArticle detteArticle) {
        return  detteRepository.save(detteArticle);
    }

    public DetteArticle delete(int id) {
        return detteRepository.delete(id);
    }

    public Optional<DetteArticle> find(int id) {
        return detteRepository.find(id);
    }
}
