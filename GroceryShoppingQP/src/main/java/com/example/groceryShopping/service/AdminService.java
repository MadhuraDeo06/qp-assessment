package com.example.groceryShopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.groceryShopping.entity.GroceryItems;
import com.example.groceryShopping.repository.GroceryRepository;

@Service
public class AdminService {
	private GroceryRepository groceryRepo;

	@Autowired
	public AdminService(GroceryRepository groceryRepo) {
		this.groceryRepo = groceryRepo;
	}

	public GroceryItems addNewGroceryItems(GroceryItems grocery) {
		System.out.println(grocery.toString());
		groceryRepo.save(grocery);
		return grocery;
	}

	public List<GroceryItems> getAllGroceryList() {
		return groceryRepo.findAll();
	}

	public void deleteGroceryItem(int id) {
		groceryRepo.deleteById(id);

	}

	public GroceryItems updateGrocerDetails(Integer id, GroceryItems grocery) {
		GroceryItems existingItem = groceryRepo.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));

		existingItem.setName(grocery.getName());
		existingItem.setQuantity(grocery.getQuantity());
		existingItem.setDescription(grocery.getDescription());

		return groceryRepo.save(existingItem);
	}


}
