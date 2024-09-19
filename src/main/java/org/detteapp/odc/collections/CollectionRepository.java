package org.detteapp.odc.collections;

import org.detteapp.odc.Identifiable;
import org.detteapp.odc.repositories.interfaces.RepositoryInt;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Profile("collection")
@Repository
public class CollectionRepository<T extends Identifiable> implements RepositoryInt<T> {
    private final Collection<T> collection;

    public CollectionRepository(Collection<T> t) {
        collection = t;
    }

    @Override
    public T save(T entity) {
        collection.add(entity);
        return entity;
    }

    @Override
    public Collection<T> findall() {
        return collection;
    }

    @Override
    public T update(T t) {
        int id = t.getId();
        delete(id);
        save(t);
        return t;
    }

    @Override
    public T delete(int id) {
        collection.removeIf(entity -> entity.getId() == id);
        return null;
    }

    @Override
    public Optional<T> find(int t) {
        int id = t;
        collection.stream().filter(entity -> entity.getId() == id).findFirst();
        return null;
    }
}
