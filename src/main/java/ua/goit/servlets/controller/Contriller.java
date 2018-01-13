package ua.goit.servlets.controller;

import java.util.List;
import java.util.Optional;

public interface Contriller<T, K> {

    Optional<T> read(K key);

    void create(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> getAll();
}
