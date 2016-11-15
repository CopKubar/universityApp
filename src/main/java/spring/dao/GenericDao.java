package spring.dao;

import java.util.List;

public interface GenericDao<T> {

    void save(T object);

    void update(T object);

    void delete(T object);

    List<T> getAll();

    T getById(Long id);
}
