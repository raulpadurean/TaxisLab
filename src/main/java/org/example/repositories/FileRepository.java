package org.example.repositories;

import org.example.models.HasId;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileRepository<T extends HasId> implements IRepository<T> {

    private final String filePath;
    private final Class<T> type;

    public FileRepository(String filePath, Class<T> type) {
        this.filePath = filePath;
        this.type = type;
    }

    @Override
    public void create(T obj) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(obj.toString()); // Use model's toString method
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Error adding entity", e);
        }
    }

    @Override
    public T read(Integer id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines()
                    .map(this::parse) // Parse directly using the model's parse method
                    .filter(entity -> entity.getId() == id)
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            throw new RuntimeException("Error reading entity", e);
        }
    }

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

    @Override
    public void update(T obj) {
        List<T> entities = readAll();
        boolean updated = false;

        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getId() == obj.getId()) {
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

    @Override
    public void delete(Integer id) {
        List<T> entities = readAll();
        boolean removed = entities.removeIf(entity -> entity.getId() == id);

        if (!removed) {
            throw new RuntimeException("Entity with ID " + id + " not found");
        }

        writeAll(entities);
    }

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

    // Helper method to parse using reflection
    private T parse(String csv) {
        try {
            return (T) type.getMethod("parse", String.class).invoke(null, csv);
        } catch (Exception e) {
            throw new RuntimeException("Error parsing entity from CSV. Ensure the model has a valid static parse(String csv) method.", e);
        }
    }
}
