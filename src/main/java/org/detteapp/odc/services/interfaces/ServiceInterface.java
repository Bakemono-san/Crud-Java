package org.detteapp.odc.services.interfaces;

import java.util.Collection;
import java.util.Optional;

public interface ServiceInterface<T> {
    T save(T entity);
    Collection<T> findall();
    T update(T t);
    T delete(int id);
    Optional<T> find(int t);
}
