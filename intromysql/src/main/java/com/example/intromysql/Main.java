package com.example.intromysql;

import java.util.List;

import adapter.MySQLCustomerRepository;
import application.Customers.GetCustomer;
import console.CustomerController;
import domain.Customer;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ventas";
        String username = "root";
        String password = "123456";

        MySQLCustomerRepository customerRepository = new MySQLCustomerRepository(url, username, password);
       /* 
        GetCustomer getCustomer = new GetCustomer(customerRepository);
        GetAllCustomers getAllCustomers = new GetAllCustomers(customerRepository);

        ConsoleController consoleController = new ConsoleController(getCustomer, getAllCustomers);

                // Agregar algunos clientes al repositorio
        customerRepository.save(new Customer(3L, "John Doe"));
        customerRepository.save(new Customer(4L, "Jane Doe"));*/

        // Ejecutar la aplicación de consola
        //consoleController.run();
        customerRepository.save(new Customer(6L, "Johlver Pardo"));
         List<Customer> customers = customerRepository.findAll();
         for (Customer customer : customers) {
            System.out.println("ID: " + customer.getId() + ", Name: " + customer.getName());
        }

    }
}