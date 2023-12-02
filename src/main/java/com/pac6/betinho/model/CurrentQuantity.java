package com.pac6.betinho.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "CURRENT_QUANTITY", schema = "public")
public class CurrentQuantity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_scheduled")
	private Long id;
	
	@Column(name = "date_time")
	private LocalDateTime dateTime;
	
	@Column(name = "grams")
	private Float grams;
	
	@ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
