package com.example.groceryShopping.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.groceryShopping.entity.GroceryItems;
import com.example.groceryShopping.entity.Order;
import com.example.groceryShopping.entity.User;
import com.example.groceryShopping.repository.AdminRepository;
import com.example.groceryShopping.repository.GroceryRepository;
import com.example.groceryShopping.repository.OrderRepository;
import com.example.groceryShopping.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private GroceryRepository groceryRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AdminService adminService;

	@PostConstruct
    public void checkUserRepo() {
        System.out.println("UserRepository is injected: " + (userRepo != null));
    }


	public List<GroceryItems> bookNewOrder(List<Order> order) {
		List<GroceryItems> newOrder = new ArrayList<>();
		List<GroceryItems> items = groceryRepo.findAll();
		for(int i = 0; i < items.size(); i++) {
			for(int j=0 ; j<order.size(); j++) {
				if(items.get(i).getId() == order.get(j).getGroceryId() ) {
					GroceryItems newGrocery = new GroceryItems(items.get(i).getId(),
							items.get(i).getName(), items.get(i).getQuantity() - order.get(j).getOrderQuantity(),
							items.get(i).getDescription());
					adminService.updateGrocerDetails(items.get(i).getId(), newGrocery);
					newOrder.add(items.get(i));
				}
				
			}
		}
		orderRepo.saveAll(order);
		return newOrder;
	}

	public List<GroceryItems> viewAvailableGrocery() {
		List<GroceryItems> items = new ArrayList<>();
		List<GroceryItems> availableItems = new ArrayList<>();
		items = groceryRepo.findAll();
		for (int i = 0; i < items.size(); i++) {
			if(items.get(i).getQuantity() > 0) {
				availableItems.add(items.get(i));
			} 
	    }
		return availableItems;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			return this.userRepo.findByUsername(username);
		
	}

}
