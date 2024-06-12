package com.example.intromysql;

import com.example.intromysql.adapter.MySQLCustomerRepository;
import com.example.intromysql.application.GetCustomer;
import com.example.intromysql.infraestructure.Controllers.CustomerController;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "password";

        MySQLCustomerRepository customerRepository = new MySQLCustomerRepository(url, username, password);
        GetCustomer getCustomer = new GetCustomer(customerRepository);
        CustomerController consoleController = new CustomerController(getCustomer);

    }
}