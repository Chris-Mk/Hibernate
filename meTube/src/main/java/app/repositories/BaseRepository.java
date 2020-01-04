package app.repositories;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<E, ID> {

    void save(E entity);

    Optional<E> findById(String id);

    void delete(E entity);

    List<E> findAll();

    void update(E entity);
}
