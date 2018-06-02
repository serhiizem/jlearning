package telecom.tariffs.dao;

import java.util.List;

public interface CrudDao<T> {
    T save(T entity);
    T update(T entity);
    T findOne(Long id);
    List<T> findAll();
}
