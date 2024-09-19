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

    protected abstract String getInsertSql();
    protected abstract String getUpdateSql();

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
                    Field idField = entityClass.getDeclaredField("id");
                    idField.setAccessible(true);
                    Integer idValue = ((Number) generatedKeys.getObject(1)).intValue(); // Convert to Integer
                    idField.set(entity, idValue);
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
                for (Field field : entityClass.getDeclaredFields()) {
                    field.setAccessible(true);
                    field.set(entity, resultSet.getObject(field.getName()));
                }
                entities.add(entity);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException("Error while finding all entities", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return entities;
    }

    @Override
    public T update(T entity) {
        String sql = getUpdateSql();
        Object idValue = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int paramIndex = setParametersForUpdate(preparedStatement, entity);
            Field idField = entityClass.getDeclaredField("id");
            idField.setAccessible(true);
            idValue = idField.get(entity);
            preparedStatement.setObject(paramIndex, idValue);

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
        T entity = findById(id); // Find entity before deletion
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

        return entity; // Return the deleted entity or handle accordingly
    }

    @Override
    public Optional<T> find(int id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        Optional<T> foundEntity = Optional.empty();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);  // Passez l'ID directement dans la requête

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Créez une instance vide de l'entité à partir de la classe
                    T found = entityClass.getDeclaredConstructor().newInstance();

                    // Parcourir les champs de la classe pour mapper les valeurs du ResultSet
                    for (Field field : entityClass.getDeclaredFields()) {
                        field.setAccessible(true);  // Rendez le champ accessible

                        // Récupération des valeurs du ResultSet par nom de colonne
                        Object columnValue = resultSet.getObject(field.getName());

                        // Vérifiez que la valeur extraite n'est pas nulle avant de la définir
                        if (columnValue != null) {
                            field.set(found, columnValue);  // Assignez la valeur au champ de l'entité
                        }
                    }

                    foundEntity = Optional.of(found);
                }
            }

        } catch (SQLException | IllegalAccessException |
                 InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Error while finding entity", e);
        }

        return foundEntity;
    }


    private T findById(int id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    T entity = entityClass.getDeclaredConstructor().newInstance();
                    for (Field field : entityClass.getDeclaredFields()) {
                        field.setAccessible(true);
                        field.set(entity, resultSet.getObject(field.getName()));
                    }
                    return entity;
                }
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("Error while finding entity by ID", e);
        }
        return null;
    }

    protected abstract void setParametersForSave(PreparedStatement preparedStatement, T entity) throws SQLException;
    protected abstract int setParametersForUpdate(PreparedStatement preparedStatement, T entity) throws SQLException;
}
