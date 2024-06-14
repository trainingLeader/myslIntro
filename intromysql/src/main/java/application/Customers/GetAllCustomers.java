package application.Customers;

import java.util.List;

import adapter.MySQLCustomerRepository;
import domain.Customer;

public class GetAllCustomers {
    private final MySQLCustomerRepository repository;

    public GetAllCustomers(MySQLCustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> execute() {
        return repository.findAll();
    }
}
