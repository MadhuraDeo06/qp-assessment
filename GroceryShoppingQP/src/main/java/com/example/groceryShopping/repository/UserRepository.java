package com.example.groceryShopping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.groceryShopping.entity.Role;
import com.example.groceryShopping.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	UserDetails findByUsername(String username);
	
	User findByRole(Role role);

}
