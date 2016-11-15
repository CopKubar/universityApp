package spring.service;

import java.util.List;

public interface GenericService<T> {

    void save(T object);

    void update(T object);

    void delete(T object);

    @SuppressWarnings("unchecked")
    List<T> getAll();

    T findById(Long id);
}
