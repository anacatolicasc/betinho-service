package com.pac6.betinho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pac6.betinho.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmailAndPassword(String email, String password);
	
	User findByToken(String token);
}
