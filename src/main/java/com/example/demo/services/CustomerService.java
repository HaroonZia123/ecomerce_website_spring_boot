package com.example.demo.services;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
////
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer( Customer customer) {
        
        customerRepository.save(customer);
    }
    public List<Customer> searchCustomersByName(String searchQuery) {
        return customerRepository.findByNameContainingIgnoreCase(searchQuery);
    }
    public List<Customer> listCustomers() {
        return customerRepository.findAll();
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }
     public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }
    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public void deleteCustomer(long id) {
        customerRepository.deleteById(id);
    }
}
