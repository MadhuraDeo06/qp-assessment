package com.example.groceryShopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.groceryShopping.entity.GroceryItems;
import com.example.groceryShopping.entity.Order;
import com.example.groceryShopping.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	

	@Autowired
	private UserService userService;
	
	@GetMapping("/availableItems")
	public List<GroceryItems> viewAvailableGroceryItem() {
		return userService.viewAvailableGrocery();
	}
	
	@PostMapping("/bookOrder")
	public List<GroceryItems> bookNewOrder(@RequestBody List<Order> order){
		return userService.bookNewOrder(order);
	}


}
