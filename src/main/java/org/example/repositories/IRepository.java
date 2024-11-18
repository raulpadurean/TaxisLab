package org.example.repositories;

import java.util.List;

public interface IRepository<T> {
    void create(T obj);

    T read(int id);

    T get(Integer id);

    void update(T obj);

    void delete(Integer id);

    List<T> getAll();

}
