package com.pac6.betinho.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonInclude(value = Include.NON_NULL)
@Table(name = "SCHEDULED_TIME", schema = "public")
public class ScheduledTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_scheduled")
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
	
	@Column(name = "date_time")
	private LocalDateTime dateTime;
}
