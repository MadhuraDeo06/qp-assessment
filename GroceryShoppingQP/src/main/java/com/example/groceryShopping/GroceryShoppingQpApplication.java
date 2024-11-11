package com.example.groceryShopping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.groceryShopping.entity.Role;
import com.example.groceryShopping.entity.User;
import com.example.groceryShopping.repository.UserRepository;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.groceryShopping")
public class GroceryShoppingQpApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(GroceryShoppingQpApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User adminAccount = userRepo.findByRole(Role.ADMIN);
		
		if(adminAccount == null) {
			User user = new User();
			
			user.setUsername("admin");
			user.setFirstname("admin");
			user.setLastnae("admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin@123"));
			userRepo.save(user);
		}
	}

}
