/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListCustomers() {
        // Create a list of mock customers
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1L, "John Doe"));
        customers.add(new Customer(2L, "Jane Smith"));

        // Mock the behavior of the customerRepository to return the list of customers
        when(customerRepository.findAll()).thenReturn(customers);

        // Call the service method to get the list of customers
        List<Customer> result = customerService.listCustomers();

        // Verify that the service method returns the expected list of customers
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Smith", result.get(1).getName());

        // Verify that the findAll method of customerRepository was called once
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void testGetCustomerById() {
        // Mock a customer with ID 1
        Customer customer = new Customer(1L, "John Doe");

        // Mock the behavior of the customerRepository to return the customer
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // Call the service method to get the customer by ID
        Customer result = customerService.getCustomerById(1L);

        // Verify that the service method returns the expected customer
        assertEquals("John Doe", result.getName());

        // Verify that the findById method of customerRepository was called once with ID 1
        verify(customerRepository, times(1)).findById(1L);
    }

    // Add more test cases for addCustomer, updateCustomer, and deleteCustomer methods
}
