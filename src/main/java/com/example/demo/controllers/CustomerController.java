/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controllers;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.services.CustomerService;
import com.example.demo.services.OrderService;
import com.example.demo.services.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {

     private final CustomerService customerService;
     private final OrderService orderService; // You should have an OrderService to manage orders.
     private final ProductService productService; // You should have an OrderService to manage orders.

    @Autowired
    public CustomerController(CustomerService customerService,OrderService order,ProductService p) {
        this.customerService = customerService;
        this.orderService=order;
        this.productService=p;
    }

    @GetMapping("/list")
    public String listCustomers(Model model) {
        // Retrieve a list of customers from the service layer


        List<Customer> customers = customerService.listCustomers();
      
        // Add the list of customers to the model to be used in the view
        model.addAttribute("customers", customers);
        
        // Return the view name (HTML template)
        return "customer/list"; // This assumes you have an HTML template named "list.html" for displaying the list of customers.
    }
    @GetMapping("/listOrder")
    public String listOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "customer/list-orders"; // This assumes you have a Thymeleaf template named "list.html" for displaying orders.
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        // Create a new Customer object to bind to the form
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer/add"; // This assumes you have an HTML template named "add.html" for adding a new customer.
    }



    @PostMapping("/add")
    public String addCustomer(@ModelAttribute("customer") Customer customer) {
        // Save the customer using the service layer
        customerService.addCustomer(customer);
        return "redirect:/customers/list"; // Redirect to the customer list page after adding.
    }

    @GetMapping("/edit/{customerId}")
    public String showEditForm(@PathVariable Long customerId, Model model) {
        // Retrieve the customer by ID
        Customer customer = customerService.getCustomerById(customerId);
        
        // Add the customer to the model to populate the form
        model.addAttribute("customer", customer);
        
        return "customer/edit"; // This assumes you have an HTML template named "edit.html" for editing customer details.
    }

    @PostMapping("/edit")
    public String updateCustomer(@ModelAttribute("customer") Customer customer) {
        // Update the customer using the service layer
        customerService.updateCustomer(customer);
        return "redirect:/customers/list"; // Redirect to the customer list page after editing.
    }

    @GetMapping("/delete/{customerId}")
    public String deleteCustomer(@PathVariable Long customerId) {
        // Delete the customer by ID
        customerService.deleteCustomer(customerId);
        return "redirect:/customers/list"; // Redirect to the customer list page after deleting.
    }
    
     @GetMapping("/view-orders/{customerId}")
    public String viewCustomerOrders(@PathVariable Long customerId, Model model) {
        Customer customer = customerService.getCustomerById(customerId);
        List<Order> orders = orderService.getOrdersByCustomer(customer); // Assuming you have a method like this in your OrderService.
        for(Order o:orders)
        {
            o.price=o.getProduct().getPrice();
            o.name=o.getProduct().getName();
        }
        
        model.addAttribute("customer", customer);
        model.addAttribute("orders", orders);
        

        return "customer/view-order";
    }

    @GetMapping("/add-order/{customerId}")
    public String showAddOrderForm(@PathVariable Long customerId, Model model) {
        Customer customer = customerService.getCustomerById(customerId);
        // You can provide a form to add an order here
        // For example, model.addAttribute("order", new Order());
        model.addAttribute("customer", customer);
        model.addAttribute("products", productService.listProducts());

        return "customer/add-order";
    }

    @PostMapping("/add-order")
    public String addOrderForCustomer( @RequestParam Long product_id, // Add this parameter to capture the selected product ID
 @ModelAttribute("customer") Customer customer,@ModelAttribute("order") Order order) {
        // Set the customer for the order
        order.setCustomer(customer);
        order.setProduct(productService.getProductById(product_id));
        // Save the order
        order.name=customer.getName();
        order.price=order.getProduct().getPrice();
        order.cid=customer.getId();
        orderService.addOrder(order);
        return "redirect:/customers/list";
    }
    @GetMapping("/search")
    public String searchCustomers(@RequestParam(name = "search") String searchQuery, Model model) {
        List<Customer> searchResults = customerService.searchCustomersByName(searchQuery);
        model.addAttribute("customers", searchResults);
        return "customer/list"; // Redirect to the customer list page with search results.
    }
}
