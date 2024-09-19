package org.detteapp.odc.services;

import org.detteapp.odc.entities.DetteEntity;
import org.detteapp.odc.repositories.interfaces.DetteRepositoryInterface;
import org.detteapp.odc.services.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class DetteService implements ServiceInterface<DetteEntity> {

    private final DetteRepositoryInterface detteRepository;

    @Autowired
    public DetteService(DetteRepositoryInterface detteRepository) {
        this.detteRepository = detteRepository;
    }

    @Override
    public DetteEntity save(DetteEntity entity) {
        return detteRepository.save(entity);
    }

    @Override
    public Collection<DetteEntity> findall() {
        return detteRepository.findall();
    }

    @Override
    public DetteEntity update(DetteEntity clientEntity) {
        return detteRepository.update(clientEntity);
    }

    @Override
    public DetteEntity delete(int id) {
        return detteRepository.delete(id);
    }

    @Override
    public Optional<DetteEntity> find(int t) {
        return Optional.empty();
    }
}
