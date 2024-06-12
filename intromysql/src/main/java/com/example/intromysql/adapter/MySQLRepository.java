package com.example.intromysql.adapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.intromysql.domain.repository.Repository;

public abstract class MySQLRepository<T, ID> implements Repository<T, ID> {
    protected final String url;
    protected final String username;
    protected final String password;

    public MySQLRepository(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    protected abstract T mapResultSetToEntity(ResultSet resultSet) throws SQLException;
    protected abstract PreparedStatement createFindByIdStatement(Connection connection, ID id) throws SQLException;
    protected abstract PreparedStatement createSaveStatement(Connection connection, T entity) throws SQLException;

    @Override
    public T findById(ID id) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = createFindByIdStatement(connection, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<T> findAll() {
        List<T> entities = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM " + getTableName();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public void save(T entity) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = createSaveStatement(connection, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected abstract String getTableName();
}
