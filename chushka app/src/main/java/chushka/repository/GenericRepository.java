package chushka.repository;

import java.util.List;

public interface GenericRepository<E, ID> {

    void save(E entity);

    E findByName(ID id);

    List<E> findAll();
}
