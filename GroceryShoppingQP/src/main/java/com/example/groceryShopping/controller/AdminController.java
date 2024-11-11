package com.example.groceryShopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.groceryShopping.entity.GroceryItems;
import com.example.groceryShopping.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@PostMapping("/add")
	public GroceryItems addNewGroceryItems(@RequestBody GroceryItems grocery){
		System.out.println("controller :: "+grocery.toString());
		return adminService.addNewGroceryItems(grocery);
	}
	
	@GetMapping("/viewGrocery")
	public List<GroceryItems> getAllGroceryList(){
		return adminService.getAllGroceryList();
	}
	
	@DeleteMapping("/removeGroceryItem/{id}")
	public void deleteGroceryItem(@PathVariable int id) {
		adminService.deleteGroceryItem(id);
	}
	
	@PutMapping("/updateGroceryDetails/{id}")
	public GroceryItems updateGroceryDetails(@PathVariable int id, @RequestBody GroceryItems grocery) {
		return adminService.updateGrocerDetails(id, grocery);
		
	}
	
}
