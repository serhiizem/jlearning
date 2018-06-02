package telecom.tariffs.service;

import java.util.List;

public interface CrudService<T> {
    T save(T entity);

    T update(T entity);

    T findById(Long id);

    List<T> findAll();
}
