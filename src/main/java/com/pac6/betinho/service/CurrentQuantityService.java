package com.pac6.betinho.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pac6.betinho.model.CurrentQuantity;
import com.pac6.betinho.repository.CurrentQuantityRepository;

import io.jsonwebtoken.lang.Assert;

@Service
public class CurrentQuantityService {

	private CurrentQuantityRepository repository;
	
	private UserService userService;
	
	@Autowired
	public CurrentQuantityService(CurrentQuantityRepository repository, UserService userService) {
		this.repository = repository;
		this.userService = userService;
	}
	
	public List<CurrentQuantity> list() {
		List<CurrentQuantity> lista = repository.findAll();
		return lista;
	}
	
	public CurrentQuantity create(CurrentQuantity currentQuantity) {
		return repository.save(currentQuantity);
	}
	
	public CurrentQuantity update(CurrentQuantity currentQuantity) {
		return repository.save(currentQuantity);
	}
	
    public ResponseEntity<CurrentQuantity> createCurrentQuantity(CurrentQuantity currentQuantity, String token) {
    	Assert.isTrue(currentQuantity.getId() == null, "ID não deve ser informado.");
        Long userId = userService.getUserByToken(token);

        if (userService.userExists(userId)) {
            currentQuantity.setUser(userService.getUserById(userId));
            CurrentQuantity createdCurrentQuantity = create(currentQuantity);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCurrentQuantity);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
