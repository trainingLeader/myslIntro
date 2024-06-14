package console;

import java.util.List;
import java.util.Scanner;

import application.Customers.GetAllCustomers;
import application.Customers.GetCustomer;
import domain.Customer;

public class CustomerController {
    private final GetCustomer getCustomer;
    private final GetAllCustomers getAllCustomers;

    public CustomerController(GetCustomer getCustomer, GetAllCustomers getAllCustomers) {
        this.getCustomer = getCustomer;
        this.getAllCustomers = getAllCustomers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Options:");
            System.out.println("1: Get customer by ID");
            System.out.println("2: Get all customers");
            System.out.println("Type 'exit' to quit");
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            switch (input) {
                case "1":
                    System.out.print("Enter customer ID: ");
                    String idInput = scanner.nextLine();
                    try {
                        Long id = Long.parseLong(idInput);
                        Customer customer = getCustomer.execute(id);

                        if (customer != null) {
                            System.out.println("Customer: " + customer.getName());
                        } else {
                            System.out.println("Customer not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid customer ID.");
                    }
                    break;
                case "2":
                    List<Customer> customers = getAllCustomers.execute();
                    System.out.println("Customers:");
                    for (Customer customer : customers) {
                        System.out.println("ID: " + customer.getId() + ", Name: " + customer.getName());
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
