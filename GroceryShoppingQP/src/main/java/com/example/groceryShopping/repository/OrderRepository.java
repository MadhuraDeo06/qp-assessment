package com.example.groceryShopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.groceryShopping.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
