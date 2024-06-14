package application.Customers;


import adapter.MySQLCustomerRepository;
import domain.Customer;


public class GetCustomer {
    private final MySQLCustomerRepository repository;

    public GetCustomer(MySQLCustomerRepository repository) {
        this.repository = repository;
    }

    public Customer execute(Long id) {
        return repository.findById(id);
    }
}
