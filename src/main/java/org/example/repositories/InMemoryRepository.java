package org.example.repositories;

import org.example.models.HasId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A repository implementation that stores entities in memory.
 * This repository works with entities that implement the {@link HasId} interface.
 * It provides basic CRUD (Create, Read, Update, Delete) operations on a collection of entities stored in memory.
 *
 * @param <T> The type of the entity that extends {@link HasId}.
 */
public class InMemoryRepository<T extends HasId> implements IRepository<T> {

    private final Map<Integer, T> dataStore = new HashMap<>();

    /**
     * Creates a new entity and adds it to the in-memory data store.
     * If an entity with the same ID already exists, it will not be added again.
     *
     * @param obj The entity to create.
     */
    @Override
    public void create(T obj) {
        dataStore.putIfAbsent(obj.getId(), obj);
    }

    /**
     * Retrieves an entity by its ID from the in-memory data store.
     *
     * @param id The ID of the entity to retrieve.
     * @return The entity with the specified ID, or {@code null} if not found.
     */
    @Override
    public T read(Integer id) {
        return dataStore.get(id);
    }

    /**
     * Updates an existing entity in the in-memory data store.
     * If an entity with the specified ID exists, it will be replaced with the new entity.
     *
     * @param obj The entity to update.
     */
    @Override
    public void update(T obj) {
        dataStore.replace(obj.getId(), obj);
    }

    /**
     * Deletes an entity by its ID from the in-memory data store.
     *
     * @param id The ID of the entity to delete.
     */
    @Override
    public void delete(Integer id) {
        dataStore.remove(id);
    }

    /**
     * Retrieves all entities stored in the in-memory data store.
     *
     * @return A list of all entities in the data store.
     */
    @Override
    public List<T> readAll() {
        return new ArrayList<>(dataStore.values());
    }
}
