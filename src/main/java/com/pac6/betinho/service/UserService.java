package com.pac6.betinho.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import com.pac6.betinho.model.User;
import com.pac6.betinho.repository.UserRepository;
import java.security.Key;

@Service
public class UserService {
	
	private Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createToken(Long userId) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
    
    public User createUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        User savedUser = userRepository.save(user);

        String token = createToken(savedUser.getId());

        savedUser.setToken(token);

        userRepository.save(savedUser);

        return savedUser;
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserIdFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return Long.parseLong(claims.getSubject());
        } catch (Exception e) {
            return null;
        }
    }
    
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public boolean userExists(Long userId) {
        return userRepository.findById(userId).isPresent();
    }
    
    public Long getUserByToken(String token) {
    	return userRepository.findByToken(token).getId();
    }
}
