package com.example.groceryShopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.groceryShopping.entity.GroceryItems;

@Repository
public interface GroceryRepository extends JpaRepository<GroceryItems, Integer> {

}
