package repository.base;

import java.util.List;

public interface BaseRepository<E, ID> {

    E findById(ID id);

    List<E> findAll();

    void save(E entity);

    void update(E entity);

    void delete(Long id);
}
