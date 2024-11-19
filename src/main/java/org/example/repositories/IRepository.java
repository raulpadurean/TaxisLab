package org.example.repositories;

import java.util.List;

/**
 * A generic repository interface that defines basic CRUD (Create, Read, Update, Delete) operations for objects
 * of type {@code T}. This interface is intended to be implemented by classes that provide data storage functionality
 * for entities that can be uniquely identified by an ID.
 *
 * @param <T> The type of objects managed by the repository, which must have a unique identifier.
 */
public interface IRepository<T> {

    /**
     * Creates a new object in the repository.
     *
     * <p>Stores the object in the repository. If an object with the same ID already exists, it should not overwrite
     * the existing object.</p>
     *
     * @param obj The object to be created and added to the repository. Must not be {@code null}.
     */
    void create(T obj);

    /**
     * Reads an object by its unique ID.
     *
     * @param id The unique identifier of the object to retrieve.
     * @return The object with the specified ID, or {@code null} if no such object exists.
     */
    T read(int id);

    /**
     * Retrieves an object by its unique ID.
     *
     * @param id The unique identifier of the object to retrieve.
     * @return The object with the specified ID, or {@code null} if no such object exists in the repository.
     */
    T get(Integer id);

    /**
     * Updates an existing object in the repository.
     *
     * <p>Replaces an object in the repository with a new version of the object, identified by its ID. If the object
     * with the specified ID does not exist, the object will be added as a new object.</p>
     *
     * @param obj The object to update. The object must not be {@code null}.
     */
    void update(T obj);

    /**
     * Deletes an object from the repository.
     *
     * @param id The unique identifier of the object to delete.
     */
    void delete(Integer id);

    /**
     * Retrieves all objects stored in the repository.
     *
     * @return A list of all objects currently in the repository.
     */
    List<T> getAll();
}
