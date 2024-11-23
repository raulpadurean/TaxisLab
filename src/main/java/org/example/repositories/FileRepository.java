package org.example.repositories;

import org.example.mappers.EntityMapper;
import org.example.models.HasId;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileRepository<T extends HasId> implements IRepository<T> {


    private final String filePath;
    private final EntityMapper<T> mapper;

    public FileRepository(String filePath, EntityMapper<T> mapper) {
        this.filePath = filePath;
        this.mapper = mapper;
    }

    @Override
    public void create(T obj) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(mapper.toCSV(obj));
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Hinzufügen einer Entität", e);
        }
    }

    @Override
    public T read(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines()
                    .map(mapper::fromCSV)
                    .filter(entity -> entity.getId() == id)
                    .findFirst()
                    .orElse(null);
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Lesen einer Entität", e);
        }
    }

    @Override
    public T get(Integer id) {
        return null;
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
            throw new RuntimeException("Entität mit ID " + obj.getId() + " nicht gefunden");
        }

        writeAll(entities);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<T> getAll() {
        return List.of();
    }

    @Override
    public void delete(int id) {
        List<T> entities = readAll();
        boolean removed = entities.removeIf(entity -> entity.getId() == id);

        if (!removed) {
            throw new RuntimeException("Entität mit ID " + id + " nicht gefunden");
        }

        writeAll(entities);
    }

    private List<T> readAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines()
                    .map(mapper::fromCSV)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Lesen aller Entitäten", e);
        }
    }

    private void writeAll(List<T> entities) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (T entity : entities) {
                writer.write(mapper.toCSV(entity));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Schreiben in die Datei", e);
        }
    }
}

