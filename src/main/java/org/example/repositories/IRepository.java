package org.example.repositories;

import java.util.List;

/**
 * A generic repository interface that defines basic CRUD (Create, Read, Update, Delete) operations
 * for managing entities of type {@code T}. This interface is intended to be implemented by classes
 * that manage the persistence of entities, such as database repositories or in-memory repositories.
 *
 * @param <T> The type of entity that the repository manages.
 */
public interface IRepository<T> {

    /**
     * Creates a new entity and persists it.
     *
     * @param obj The entity to create and persist.
     */
    void create(T obj);

    /**
     * Retrieves an entity by its ID.
     *
     * @param id The ID of the entity to retrieve.
     * @return The entity with the specified ID, or {@code null} if not found.
     */
    T read(Integer id);

    /**
     * Updates an existing entity with the given data.
     * If the entity does not exist, an appropriate exception may be thrown.
     *
     * @param obj The entity to update.
     */
    void update(T obj);

    /**
     * Deletes an entity by its ID.
     *
     * @param id The ID of the entity to delete.
     */
    void delete(Integer id);

    /**
     * Retrieves all entities managed by the repository.
     *
     * @return A list of all entities in the repository.
     */
    List<T> readAll();
}
