package telecom.dao;

public interface BaseDao<T> {
    Long save(T entity);
}
