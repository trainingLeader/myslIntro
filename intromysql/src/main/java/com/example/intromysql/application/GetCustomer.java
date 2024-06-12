package com.example.intromysql.application;

import com.example.intromysql.domain.entities.Customer;
import com.example.intromysql.domain.repository.CustomerRepository;

public class GetCustomer {
    private final CustomerRepository repository;

    public GetCustomer(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer execute(Long id) {
        return repository.findById(id);
    }
}
