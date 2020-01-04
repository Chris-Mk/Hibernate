package app.repositories;

import app.domain.entities.Status;

import java.util.List;

public interface BaseRepository<E, ID> {

    E findById(ID id);

    List<E> findAll();

    void save(E entity);

    void updateStatus(Long id, Status status);

    void delete(E entity);

    long count();
}
