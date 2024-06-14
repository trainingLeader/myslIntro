package adapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Customer;

public class MySQLCustomerRepository extends MySQLRepository<Customer, Long> {
    public MySQLCustomerRepository(String url, String username, String password) {
        super(url, username, password);
    }

    @Override
    protected Customer mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Customer(resultSet.getLong("id"), resultSet.getString("name"));
    }

    @Override
    protected PreparedStatement createFindByIdStatement(Connection connection, Long id) throws SQLException {
        String query = "SELECT id,name FROM customers WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, id);
        return statement;
    }

    @Override
    protected PreparedStatement createSaveStatement(Connection connection, Customer customer) throws SQLException {
        String query = "INSERT INTO customers (id, name) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setLong(1, customer.getId());
        statement.setString(2, customer.getName());
        return statement;
    }

    @Override
    protected String getTableName() {
        return "customers";
    }
}