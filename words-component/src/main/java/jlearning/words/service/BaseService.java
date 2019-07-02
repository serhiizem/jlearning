package telecom.service;

public interface BaseService<T> {
    Long save(T entity, String ref);
}
