package com.example.demo.services;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        
        productRepository.save(product);
    }
    public List<Product> searchProductsByName(String searchQuery) {
        return productRepository.findByNameContainingIgnoreCase(searchQuery);
    }
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
    public void updateProduct(Product product) {
        productRepository.save(product);
    }
    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }
}
