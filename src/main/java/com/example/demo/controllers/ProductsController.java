package com.example.demo.controllers;

import com.example.demo.entity.Product;
import com.example.demo.services.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private final ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping("/list")
    public String listProducts(Model model)
    {
        //Boolean admin = Boolean.valueOf(adminStr);

        //model.addAttribute("admin",admin);


        List<Product> products = productService.listProducts();
        model.addAttribute("products", products);
        return "product/list";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(name = "search") String searchQuery, Model model) {
        List<Product> searchResults = productService.searchProductsByName(searchQuery);
        model.addAttribute("products", searchResults);
        return "product/list"; // Redirect to the product list page with search results.
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/add";
    }


    @PostMapping("/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        productService.addProduct(product);
        return "redirect:/products/list";
    }

    
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products/list";
    }
    
     @GetMapping("/edit/{productId}")
    public String showEditForm(@PathVariable Long productId, Model model) {
        // Retrieve the product by ID
        Product product = productService.getProductById(productId);
        
        // Add the product to the model to populate the form
        model.addAttribute("product", product);
        
        return "product/edit"; // This assumes you have an HTML template named "edit.html" for editing product details.
    }

    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute("product") Product product) {
        // Update the product using the service layer
        productService.updateProduct(product);
        //System.out.println("it is called"+product.getId());
        return "redirect:/products/list"; // Redirect to the product list page after editing.
    }
}