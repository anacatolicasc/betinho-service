package com.pac6.betinho.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "USER", schema = "public")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "token")
	private String token;
}
