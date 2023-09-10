/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author City Computer
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // You can add custom query methods here if needed
        List<Order> findByCustomer(Customer customer);

}
