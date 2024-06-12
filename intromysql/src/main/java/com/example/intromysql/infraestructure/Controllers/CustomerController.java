package com.example.intromysql.infraestructure.Controllers;

import java.util.Scanner;

import com.example.intromysql.application.GetCustomer;
import com.example.intromysql.domain.entities.Customer;

public class CustomerController {
    private final GetCustomer getCustomer;

    public CustomerController(GetCustomer getCustomer) {
        this.getCustomer = getCustomer;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Ingrese Id del cliente (o 'exit' para salir): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                Long id = Long.parseLong(input);
                Customer customer = getCustomer.execute(id);

                if (customer != null) {
                    System.out.println("Cliente: " + customer.getName());
                } else {
                    System.out.println("Cliente no encontrado.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no valida. Por fa ingrese un valor valido.");
            }
        }

        scanner.close();
    }
}
