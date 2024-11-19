package org.example.repositories;

import org.example.models.HasId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An in-memory implementation of the {@link IRepository} interface, providing basic CRUD (Create, Read, Update, Delete)
 * operations for objects that implement the {@link HasId} interface.
 *
 * <p>This class stores the entities in memory using a {@link Map} where the keys are the unique identifiers
 * (IDs) of the objects, and the values are the objects themselves.</p>
 *
 * <p>Instances of this class are intended for use cases where persistent storage (e.g., databases) is not required,
 * and the objects are managed only during the runtime of the application.</p>
 *
 * @param <T> The type of objects stored in the repository, which must extend {@link HasId}.
 */
public class InMemoryRepository<T extends HasId> implements IRepository<T> {

    // In-memory storage for entities, indexed by their unique ID.
    private final Map<Integer, T> dataStore = new HashMap<>();

    /**
     * Creates and stores a new object in the repository.
     *
     * <p>If the object with the same ID already exists, it will not be added again.</p>
     *
     * @param obj The object to be created and added to the repository. Must not be {@code null}.
     */
    @Override
    public void create(T obj) {
        dataStore.putIfAbsent(obj.getId(), obj);
    }

    /**
     * Retrieves an object by its unique ID.
     *
     * @param id The unique identifier of the object to retrieve.
     * @return The object with the specified ID, or {@code null} if no such object exists in the repository.
     */
    @Override
    public T get(Integer id) {
        return dataStore.get(id);
    }

    /**
     * Reads an object by its unique ID.
     *
     * <p>This is an alias for {@link #get(Integer)}, providing an alternative method name.</p>
     *
     * @param id The unique identifier of the object to read.
     * @return The object with the specified ID, or {@code null} if no such object exists.
     */
    @Override
    public T read(int id) {
        return dataStore.get(id);
    }

    /**
     * Updates an existing object in the repository.
     *
     * <p>If the object with the specified ID does not exist, it will be added as a new object.</p>
     *
     * @param obj The object to be updated. The object must not be {@code null}.
     */
    @Override
    public void update(T obj) {
        dataStore.replace(obj.getId(), obj);
    }

    /**
     * Deletes an object from the repository by its unique ID.
     *
     * @param id The unique identifier of the object to delete.
     */
    @Override
    public void delete(Integer id) {
        dataStore.remove(id);
    }

    /**
     * Retrieves all objects stored in the repository.
     *
     * @return A list of all objects currently in the repository.
     */
    @Override
    public List<T> getAll() {
        return new ArrayList<>(dataStore.values());
    }
}
