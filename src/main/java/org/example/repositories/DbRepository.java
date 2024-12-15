package org.example.repositories;

import org.example.exceptions.DatabaseException;
import org.example.exceptions.EntityNotFoundException;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DbRepository<T> implements IRepository<T> {

    private final Connection connection;
    private final String tableName;
    private final Function<ResultSet, T> parser;

    public DbRepository(Connection connection, String tableName, Function<ResultSet, T> parser) {
        this.connection = connection;
        this.tableName = tableName.toLowerCase(); // ensure consistency if needed
        this.parser = parser;
    }

    @Override
    public void create(T obj) {
        String sql = generateInsertQuery(obj);
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            populateInsertParameters(stmt, obj);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to insert into " + tableName, e);
        }
    }

    @Override
    public T read(Integer id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return parser.apply(rs);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to read from " + tableName, e);
        }
        throw new EntityNotFoundException("Entity not found in " + tableName + " with ID: " + id);
    }

    @Override
    public void update(T obj) {
        String sql = generateUpdateQuery(obj);
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            populateUpdateParameters(stmt, obj);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to update " + tableName, e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete from " + tableName, e);
        }
    }

    @Override
    public List<T> readAll() {
        String sql = "SELECT * FROM " + tableName;
        List<T> results = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(parser.apply(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to read all from " + tableName, e);
        }
        return results;
    }

    private String generateInsertQuery(T obj) {
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            // If 'id' is auto-generated, skip it in insert.
            if (field.getName().equals("id")) {
                continue;
            }
            if (columns.length() > 0) {
                columns.append(", ");
                values.append(", ");
            }
            columns.append(convertToSnakeCase(field.getName()));
            values.append("?");
        }

        return "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ")";
    }

    private String generateUpdateQuery(T obj) {
        StringBuilder setClause = new StringBuilder();
        boolean hasId = false;

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getName().equals("id")) {
                hasId = true;
                continue; // skip id field for SET clause
            }
            if (setClause.length() > 0) {
                setClause.append(", ");
            }
            setClause.append(convertToSnakeCase(field.getName())).append(" = ?");
        }

        if (!hasId) {
            throw new DatabaseException("Cannot update entity without 'id' field", new Exception(""));
        }

        return "UPDATE " + tableName + " SET " + setClause + " WHERE id = ?";
    }

    private void populateInsertParameters(PreparedStatement stmt, T obj) throws SQLException {
        int index = 1;
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getName().equals("id")) {
                    // Skip id if it's auto-generated
                    continue;
                }
                stmt.setObject(index++, field.get(obj));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to populate prepared statement for insert", e);
        }
    }

    private void populateUpdateParameters(PreparedStatement stmt, T obj) throws SQLException {
        int index = 1;
        Integer idValue = null;
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getName().equals("id")) {
                    idValue = (Integer) field.get(obj);
                } else {
                    stmt.setObject(index++, field.get(obj));
                }
            }
            if (idValue == null) {
                throw new DatabaseException("Object does not have an 'id' field set, cannot update", new Exception(""));
            }
            stmt.setInt(index, idValue);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to populate prepared statement for update", e);
        }
    }

    /**
     * Converts a camelCase field name to snake_case column name.
     * e.g. "plateNr" -> "plate_nr"
     */
    private String convertToSnakeCase(String fieldName) {
        StringBuilder sb = new StringBuilder();
        for (char c : fieldName.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append("_").append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
