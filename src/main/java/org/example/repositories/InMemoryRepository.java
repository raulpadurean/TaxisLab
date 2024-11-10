package org.example.repositories;

import java.util.*;

public class InMemoryRepository<T> implements IRepository<T> {
    private final Map<Integer, T> dataStore = new HashMap<>();
    private int idCounter = 1;

    @Override
    public void create(T obj) {
        dataStore.put(idCounter++, obj);
    }

    @Override
    public T read(int id) {
        return dataStore.get(id);
    }

    @Override
    public void update(T obj) {
    }

    @Override
    public void delete(int id) {
        dataStore.remove(id);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(dataStore.values());
    }
}
