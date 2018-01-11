package ua.goit.servlets.model.dao;

import java.util.List;
import java.util.Optional;

/**
 * Created by Роман on 11.01.2018.
 */
public interface GenericDao<T, K> {

    Optional<T> read(K key);

    void create(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> getAll();
}