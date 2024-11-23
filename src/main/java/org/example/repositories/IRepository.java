package org.example.repositories;

import java.util.List;

public interface IRepository<T> {
    void create(T obj);

    T read(Integer id);

    void update(T obj);

    void delete(Integer id);

    List<T> readAll();

}
