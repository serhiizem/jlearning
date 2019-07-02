package jlearning.words.service;

public interface BaseService<T> {
    Long save(T entity, String ref);
}
