package app.repository;

import java.util.Collection;

public interface BaseRepository<E, ID> {

    void save(E entity);

    int update(Collection<E> entities);

    int delete(Collection<E> entities);

    Collection<E> findAll();

    E findById(String id);

    long count();
}
