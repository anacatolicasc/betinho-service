package com.pac6.betinho.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pac6.betinho.model.ScheduledTime;

@Repository
public interface ScheduledTimeRepository extends JpaRepository<ScheduledTime, Long> {
	List<ScheduledTime> findByUserId(Long id);

	List<ScheduledTime> findByDateTimeBetween(LocalDateTime today, LocalDateTime hours);
}
