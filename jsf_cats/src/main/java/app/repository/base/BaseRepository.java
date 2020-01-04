package app.repository.base;

import java.util.List;

public interface BaseRepository<E, ID> {

    void save(E entity);

    List<E> findAll();
}
