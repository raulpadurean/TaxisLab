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
        this.tableName = tableName.toLowerCase(); // Ensure consistency
        this.parser = parser;
    }

    @Override
    public void create(T obj) {
        String sql = generateInsertQuery(obj);
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            populateInsertParameters(stmt, obj);
            stmt.executeUpdate();
            System.out.println("Insert successful! SQL: " + sql);
        } catch (SQLException e) {
            throw new DatabaseException("Failed to insert into " + tableName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public T read(Integer id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return parser.apply(rs);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to read from " + tableName + ": " + e.getMessage(), e);
        }
        throw new EntityNotFoundException("Entity not found in " + tableName + " with ID: " + id);
    }

    @Override
    public void update(T obj) {
        String sql = generateUpdateQuery(obj);
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            populateUpdateParameters(stmt, obj);
            stmt.executeUpdate();
            System.out.println("Update successful! SQL: " + sql);
        } catch (SQLException e) {
            throw new DatabaseException("Failed to update " + tableName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Delete successful! SQL: " + sql);
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete from " + tableName + ": " + e.getMessage(), e);
        }
    }

    @Override
    public List<T> readAll() {
        String sql = "SELECT * FROM " + tableName;
        List<T> results = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                results.add(parser.apply(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to read all from " + tableName + ": " + e.getMessage(), e);
        }
        return results;
    }

    private String generateInsertQuery(T obj) {
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        for (Field field : getAllFields(obj.getClass())) {
            field.setAccessible(true);

            if (field.getName().equals("id")) continue; // Skip auto-generated ID

            if (columns.length() > 0) {
                columns.append(", ");
                values.append(", ");
            }

            String columnName = isNestedEntity(field) ? convertToSnakeCase(field.getName()) + "_id" : convertToSnakeCase(field.getName());
            columns.append(columnName);
            values.append("?");
        }

        return "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ")";
    }

    private String generateUpdateQuery(T obj) {
        StringBuilder setClause = new StringBuilder();

        for (Field field : getAllFields(obj.getClass())) {
            field.setAccessible(true);

            if (field.getName().equals("id")) continue; // Skip ID in SET clause

            if (setClause.length() > 0) setClause.append(", ");

            String columnName = isNestedEntity(field) ? convertToSnakeCase(field.getName()) + "_id" : convertToSnakeCase(field.getName());
            setClause.append(columnName).append(" = ?");
        }

        return "UPDATE " + tableName + " SET " + setClause + " WHERE id = ?";
    }

    private void populateInsertParameters(PreparedStatement stmt, T obj) throws SQLException {
        populateParameters(stmt, obj, false);
    }

    private void populateUpdateParameters(PreparedStatement stmt, T obj) throws SQLException {
        populateParameters(stmt, obj, true);
    }

    private void populateParameters(PreparedStatement stmt, T obj, boolean includeId) throws SQLException {
        int index = 1;

        try {
            for (Field field : getAllFields(obj.getClass())) {
                field.setAccessible(true);
                if (!includeId && field.getName().equals("id")) continue;

                Object value = isNestedEntity(field) ? getNestedId(field.get(obj)) : field.get(obj);
                if (value instanceof Enum) value = ((Enum<?>) value).name();

                System.out.println("Binding parameter " + index + ": [" + field.getName() + "] = " + value);
                stmt.setObject(index++, value);
            }

            if (includeId) {
                Field idField = obj.getClass().getDeclaredField("id");
                idField.setAccessible(true);
                stmt.setObject(index, idField.get(obj)); // Bind ID
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException("Failed to populate parameters", e);
        }
    }

    private Object getNestedId(Object nestedObj) throws IllegalAccessException {
        if (nestedObj == null) return null;
        try {
            Field idField = nestedObj.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            return idField.get(nestedObj);
        } catch (NoSuchFieldException e) {
            throw new DatabaseException("Nested object does not have an 'id' field", e);
        }
    }

    private boolean isNestedEntity(Field field) {
        Class<?> fieldType = field.getType();
        return !(fieldType.isPrimitive() || fieldType.getName().startsWith("java.") || fieldType.isEnum());
    }

    private List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
        while (type != null) {
            for (Field field : type.getDeclaredFields()) {
                fields.add(field);
            }
            type = type.getSuperclass();
        }
        return fields;
    }

    private String convertToSnakeCase(String fieldName) {
        StringBuilder sb = new StringBuilder();
        for (char c : fieldName.toCharArray()) {
            if (Character.isUpperCase(c)) sb.append("_").append(Character.toLowerCase(c));
            else sb.append(c);
        }
        return sb.toString();
    }
}