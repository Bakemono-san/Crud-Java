package org.detteapp.odc.repositories;

import org.detteapp.odc.config.DatabaseConfig;
import org.detteapp.odc.repositories.interfaces.RepositoryInt;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Profile("jdbc")
@Repository
public abstract class JdbcRepository<T> implements RepositoryInt<T> {

    private final Connection connection;
    private final String tableName;
    private final Class<T> entityClass;

    protected JdbcRepository(DatabaseConfig databaseConfig, String tableName, Class<T> entityClass) {
        this.connection = databaseConfig.getConnection();
        this.tableName = tableName;
        this.entityClass = entityClass;
    }

    protected Connection getConnection() {
        return connection;
    }

    protected String getTableName() {
        return tableName;
    }

    protected Class<T> getEntityClass() {
        return entityClass;
    }

    protected String getInsertSql() {
        StringBuilder sql = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
        StringBuilder values = new StringBuilder("VALUES (");

        Field[] fields = entityClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (!"id".equals(field.getName())) {
                sql.append(field.getName()).append(", ");
                values.append("?, ");
            }
        }

        sql.setLength(sql.length() - 2); // Remove trailing comma and space
        values.setLength(values.length() - 2); // Remove trailing comma and space

        sql.append(") ").append(values).append(")");

        return sql.toString();
    }

    protected String getUpdateSql() {
        StringBuilder sql = new StringBuilder("UPDATE ").append(tableName).append(" SET ");
        Field[] fields = entityClass.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (!"id".equals(field.getName())) {
                sql.append(field.getName()).append(" = ?, ");
            }
        }

        sql.setLength(sql.length() - 2); // Remove trailing comma and space
        sql.append(" WHERE id = ?");

        return sql.toString();
    }

    @Override
    public T save(T entity) {
        String sql = getInsertSql();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            setParametersForSave(preparedStatement, entity);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserting entity failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    setEntityId(entity, generatedKeys);
                }
            }
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException("Error while saving entity", e);
        }
        return entity;
    }

    @Override
    public Collection<T> findall() {
        Collection<T> entities = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                T entity = entityClass.getDeclaredConstructor().newInstance();
                mapResultSetToEntity(resultSet, entity);
                entities.add(entity);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Error while finding all entities", e);
        }

        return entities;
    }

    @Override
    public T update(T entity) {
        String sql = getUpdateSql();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int paramIndex = setParametersForUpdate(preparedStatement, entity);
            setEntityIdForUpdate(preparedStatement, entity, paramIndex);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating entity failed, no rows affected.");
            }
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException("Error while updating entity", e);
        }

        return entity;
    }

    @Override
    public T delete(int id) {
        T entity = findById(id);
        if (entity == null) {
            throw new RuntimeException("Entity not found");
        }

        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting entity failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting entity", e);
        }

        return entity;
    }

    @Override
    public Optional<T> find(int id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        Optional<T> foundEntity = Optional.empty();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    T entity = entityClass.getDeclaredConstructor().newInstance();
                    mapResultSetToEntity(resultSet, entity);
                    foundEntity = Optional.of(entity);
                }
            }
        } catch (SQLException | IllegalAccessException | InstantiationException |
                 NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Error while finding entity", e);
        }

        return foundEntity;
    }

    private void setEntityId(T entity, ResultSet generatedKeys) throws SQLException, NoSuchFieldException, IllegalAccessException {
        Field idField = entityClass.getDeclaredField("id");
        idField.setAccessible(true);
        Integer idValue = ((Number) generatedKeys.getObject(1)).intValue();
        idField.set(entity, idValue);
    }

    private void setEntityIdForUpdate(PreparedStatement preparedStatement, T entity, int paramIndex) throws SQLException, NoSuchFieldException, IllegalAccessException {
        Field idField = entityClass.getDeclaredField("id");
        idField.setAccessible(true);
        Object idValue = idField.get(entity);
        preparedStatement.setObject(paramIndex, idValue);
    }

    private T findById(int id) {
        return find(id).orElse(null);
    }

    protected void setParametersForSave(PreparedStatement preparedStatement, T entity) throws SQLException {
        Field[] fields = entityClass.getDeclaredFields();
        int paramIndex = 1;

        for (Field field : fields) {
            field.setAccessible(true);
            if (!"id".equals(field.getName())) {
                try {
                    preparedStatement.setObject(paramIndex++, field.get(entity));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error setting parameters for save", e);
                }
            }
        }
    }

    protected int setParametersForUpdate(PreparedStatement preparedStatement, T entity) throws SQLException {
        Field[] fields = entityClass.getDeclaredFields();
        int paramIndex = 1;

        for (Field field : fields) {
            field.setAccessible(true);
            if (!"id".equals(field.getName())) {
                try {
                    preparedStatement.setObject(paramIndex++, field.get(entity));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error setting parameters for update", e);
                }
            }
        }

        return paramIndex;
    }

    private void mapResultSetToEntity(ResultSet resultSet, T entity) throws SQLException, IllegalAccessException {
        for (Field field : entityClass.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = resultSet.getObject(field.getName());
            if (value != null) {
                field.set(entity, value);
            }
        }
    }
}
