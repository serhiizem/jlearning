package jlearning.words.service;

import java.util.List;

public interface BaseService<T> {
    Long save(T entity, String ref);

    List<T> findAll(String ref);

    void clearDb();
}
