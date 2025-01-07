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
        System.out.println("Generated SQL: " + sql);
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            populateInsertParameters(stmt, obj);
            stmt.executeUpdate();
            System.out.println("Insert successful!");
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
        System.out.println("Generated SQL: " + sql);
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            populateUpdateParameters(stmt, obj);
            stmt.executeUpdate();
            System.out.println("Update successful!");
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
            System.out.println("Delete successful!");
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
        boolean firstField = true; // Track whether it is the first field

        for (Field field : getAllFields(obj.getClass())) {
            field.setAccessible(true);

            if (field.getName().equals("id")) {
                continue; // Skip auto-generated IDs
            }

            // Append column and value
            if (!firstField) {
                columns.append(", ");
                values.append(", ");
            } else {
                firstField = false; // The first field has been handled
            }

            // Handle relational fields
            if (isNestedEntity(field)) {
                columns.append(convertToSnakeCase(field.getName())).append("_id");
                values.append("?");
            } else {
                columns.append(convertToSnakeCase(field.getName()));
                values.append("?");
            }
        }

        return "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ")";
    }


    private String generateUpdateQuery(T obj) {
        StringBuilder setClause = new StringBuilder();
        boolean hasId = false;

        for (Field field : getAllFields(obj.getClass())) {
            field.setAccessible(true);
            if (field.getName().equals("id")) {
                hasId = true;
                continue; // Skip `id` in SET clause
            }

            if (isNestedEntity(field)) {
                setClause.append(", ").append(convertToSnakeCase(field.getName())).append("_id = ?");
            } else {
                if (setClause.length() > 0) {
                    setClause.append(", ");
                }
                setClause.append(convertToSnakeCase(field.getName())).append(" = ?");
            }
        }

        if (!hasId) {
            throw new DatabaseException("Cannot update entity without 'id' field", new Exception());
        }

        return "UPDATE " + tableName + " SET " + setClause + " WHERE id = ?";
    }

    private void populateInsertParameters(PreparedStatement stmt, T obj) throws SQLException {
        int index = 1;
        try {
            for (Field field : getAllFields(obj.getClass())) {
                field.setAccessible(true);
                if (field.getName().equals("id")) {
                    continue; // Skip id
                }
                if (isNestedEntity(field)) {
                    Object nestedObj = field.get(obj);
                    stmt.setObject(index++, getNestedId(nestedObj)); // Use the ID of the nested entity
                } else {
                    stmt.setObject(index++, field.get(obj));
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to populate insert parameters", e);
        }
    }

    private void populateUpdateParameters(PreparedStatement stmt, T obj) throws SQLException {
        int index = 1;
        Integer idValue = null;

        try {
            for (Field field : getAllFields(obj.getClass())) {
                field.setAccessible(true);
                if (field.getName().equals("id")) {
                    idValue = (Integer) field.get(obj);
                    continue;
                }
                if (isNestedEntity(field)) {
                    Object nestedObj = field.get(obj);
                    stmt.setObject(index++, getNestedId(nestedObj));
                } else {
                    stmt.setObject(index++, field.get(obj));
                }
            }
            if (idValue == null) {
                throw new DatabaseException("Object does not have an 'id' field set", new Exception());
            }
            stmt.setInt(index, idValue);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to populate update parameters", e);
        }
    }

    private Object getNestedId(Object nestedObj) throws IllegalAccessException {
        if (nestedObj == null) {
            return null;
        }
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
        return !(fieldType.isPrimitive() ||
                fieldType.getName().startsWith("java.") ||
                fieldType.isEnum());
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
            if (Character.isUpperCase(c)) {
                sb.append("_").append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
