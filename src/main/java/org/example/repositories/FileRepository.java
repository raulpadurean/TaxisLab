package org.example.repositories;

import org.example.models.HasId;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A repository implementation that persists and retrieves entities to/from a file.
 * This repository works with entities that implement the {@link HasId} interface.
 * It provides methods for creating, reading, updating, and deleting entities in a file-based storage system.
 * Entities are stored in a text file and are serialized using their {@code toString()} and deserialized using a static {@code parse(String csv)} method in the model class.
 *
 * @param <T> The type of the entity that extends {@link HasId}.
 */
public class FileRepository<T extends HasId> implements IRepository<T> {

    private final String filePath;
    private final Class<T> type;

    /**
     * Constructs a FileRepository for managing entities of type {@code T}.
     * The repository will store the entities in the specified file path.
     * If the file does not exist, it will be created.
     *
     * @param filePath The file path where the entities will be stored.
     * @param type     The class type of the entity, used to parse the data into entities.
     */
    public FileRepository(String filePath, Class<T> type) {
        this.filePath = filePath;
        this.type = type;

        // Check if the file exists; if not, create it
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create directories if needed
                file.createNewFile(); // Create the file
            }
        } catch (IOException e) {
            throw new RuntimeException("Error creating file: " + filePath, e);
        }
    }

    /**
     * Creates a new entity and writes it to the file.
     * The entity is serialized using its {@code toString()} method.
     *
     * @param obj The entity to create.
     */
    @Override
    public void create(T obj) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(obj.toString()); // Use model's toString method
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Error adding entity", e);
        }
    }

    /**
     * Retrieves an entity by its ID.
     * The entity is parsed from the file using its {@code parse(String csv)} method.
     *
     * @param id The ID of the entity to retrieve.
     * @return The entity with the specified ID, or null if not found.
     */
    @Override
    public T read(Integer id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines()
                    .map(this::parse) // Parse directly using the model's parse method
                    .filter(entity -> Objects.equals(entity.getId(), id))
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            throw new RuntimeException("Error reading entity", e);
        }
    }

    /**
     * Retrieves all entities from the file.
     * All entities are parsed from the file using their {@code parse(String csv)} method.
     *
     * @return A list of all entities stored in the file.
     */
    @Override
    public List<T> readAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines()
                    .map(this::parse) // Parse directly using the model's parse method
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Error reading all entities", e);
        }
    }

    /**
     * Updates an existing entity in the file.
     * The entity is replaced based on its ID, and the updated list of entities is written back to the file.
     * If the entity with the given ID is not found, an exception is thrown.
     *
     * @param obj The entity to update.
     */
    @Override
    public void update(T obj) {
        List<T> entities = readAll();
        boolean updated = false;

        for (int i = 0; i < entities.size(); i++) {
            if (Objects.equals(entities.get(i).getId(), obj.getId())) {
                entities.set(i, obj);
                updated = true;
                break;
            }
        }

        if (!updated) {
            throw new RuntimeException("Entity with ID " + obj.getId() + " not found");
        }

        writeAll(entities);
    }

    /**
     * Deletes an entity by its ID from the file.
     * If no entity with the given ID is found, an exception is thrown.
     *
     * @param id The ID of the entity to delete.
     */
    @Override
    public void delete(Integer id) {
        List<T> entities = readAll();
        boolean removed = entities.removeIf(entity -> Objects.equals(entity.getId(), id));

        if (!removed) {
            throw new RuntimeException("Entity with ID " + id + " not found");
        }

        writeAll(entities);
    }

    /**
     * Helper method to write all entities to the file after an update or deletion.
     *
     * @param entities The list of entities to write to the file.
     */
    private void writeAll(List<T> entities) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (T entity : entities) {
                writer.write(entity.toString()); // Use model's toString method
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing to file", e);
        }
    }

    /**
     * Helper method to parse a line from the file into an entity.
     * It uses reflection to invoke the static {@code parse(String csv)} method of the entity class.
     *
     * @param csv The CSV string representing an entity.
     * @return The parsed entity.
     * @throws RuntimeException If there is an error during parsing.
     */
    private T parse(String csv) {
        try {
            return (T) type.getMethod("parse", String.class).invoke(null, csv);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing entity from CSV. Ensure the model has a valid static parse(String csv) method.", e);
        }
    }
}
