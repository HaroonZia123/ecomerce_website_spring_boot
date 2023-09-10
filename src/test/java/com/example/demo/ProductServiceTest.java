/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.services.ProductService;
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

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListProducts() {
        // Create a list of mock products
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 1000.0));
        products.add(new Product( "Smartphone", 800.0));

        // Mock the behavior of the productRepository to return the list of products
        when(productRepository.findAll()).thenReturn(products);

        // Call the service method to get the list of products
        List<Product> result = productService.listProducts();

        // Verify that the service method returns the expected list of products
        assertEquals(2, result.size());
        assertEquals("Laptop", result.get(0).getName());
        assertEquals("Smartphone", result.get(1).getName());

        // Verify that the findAll method of productRepository was called once
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetProductById() {
        // Mock a product with ID 1
        Product product = new Product("Laptop", 1000.0);

        // Mock the behavior of the productRepository to return the product
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Call the service method to get the product by ID
        Product result = productService.getProductById(1L);

        // Verify that the service method returns the expected product
        assertEquals("Laptop", result.getName());

        // Verify that the findById method of productRepository was called once with ID 1
        verify(productRepository, times(1)).findById(1L);
    }

    // Add more test cases for addProduct, updateProduct, and deleteProduct methods
}







