package org.detteapp.odc.services;

import org.detteapp.odc.entities.ClientEntity;
import org.detteapp.odc.repositories.interfaces.ClientRepositoryInterface;
import org.detteapp.odc.services.interfaces.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ClientService implements ServiceInterface<ClientEntity> {
    public final ClientRepositoryInterface repository;

    @Autowired
    public ClientService(ClientRepositoryInterface repository) {
        this.repository = repository;
    }

    @Override
    public ClientEntity save(ClientEntity entity) {
        return repository.save(entity);
    }

    @Override
    public Collection<ClientEntity> findall() {
        return repository.findall();
    }

    @Override
    public ClientEntity update(ClientEntity clientEntity) {
        return repository.update(clientEntity);
    }

    @Override
    public ClientEntity delete(int id) {
        return repository.delete(id);
    }

    @Override
    public Optional<ClientEntity> find(int t) {
        return repository.find(t);
    }
}
