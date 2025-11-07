package com.example.crud.bank.controller;
import com.example.crud.bank.model.Customer;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bank")
public class CustomerController {

    ArrayList<Customer> customers = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    @PostMapping("/add")
    public String addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return "Customer added successfully";
    }

    @PutMapping("/update/{index}")
    public String updateCustomer(@PathVariable int index, @RequestBody Customer customer) {
        customers.set(index, customer);
        return "Customer updated successfully";
    }

    @DeleteMapping("/delete/{index}")
    public String deleteCustomer(@PathVariable int index) {
        customers.remove(index);
        return "Customer deleted successfully";
    }

    @PutMapping("/deposit/{index}")
    public String deposit(@PathVariable int index, @RequestParam double amount) {
        Customer customer = customers.get(index);
        customer.setBalance(customer.getBalance() + amount);
        return "Deposited " + amount + " to " + customer.getUsername();
    }

    @PutMapping("/withdraw/{index}")
    public String withdraw(@PathVariable int index, @RequestParam double amount) {
        Customer customer = customers.get(index);
        if (customer.getBalance() >= amount) {
            customer.setBalance(customer.getBalance() - amount);
            return "Withdrawn " + amount + " from " + customer.getUsername();
        } else {
            return "No enough money " + customer.getUsername();
        }
    }

}
