package jlearning.words.dao;

public interface BaseDao<T> {
    Long save(T entity);

    void deleteAll();
}
