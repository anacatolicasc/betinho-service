package com.pac6.betinho.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pac6.betinho.dto.ScheduledTimeResponse;
import com.pac6.betinho.model.ScheduledTime;
import com.pac6.betinho.repository.ScheduledTimeRepository;

import io.jsonwebtoken.lang.Assert;

@Service
public class ScheduledTimeService {
	
	private ScheduledTimeRepository repository;
	
	private UserService userService;
	
	@Autowired
	public ScheduledTimeService(ScheduledTimeRepository repository, UserService userService) {
		this.repository = repository;
		this.userService = userService;
	}
	
	public List<ScheduledTime> list() {
		List<ScheduledTime> lista = repository.findAll();
		return lista;
	}
	
	public ScheduledTime create(ScheduledTime ScheduledTime) {
		ScheduledTime newScheduledTime = repository.save(ScheduledTime);
		return newScheduledTime;
	}
	
    public ResponseEntity<ScheduledTime> createScheduledTime(ScheduledTime scheduledTime, String token) {
    	Assert.isTrue(scheduledTime.getId() == null, "ID não deve ser informado.");
        Long userId = userService.getUserByToken(token);

        if (userService.userExists(userId)) {
        	scheduledTime.setUser(userService.getUserById(userId));
            ScheduledTime createdScheduledTime = create(scheduledTime);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdScheduledTime);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
	
	public ScheduledTime update(ScheduledTime ScheduledTime) {
		ScheduledTime newScheduledTime = repository.save(ScheduledTime);
		return newScheduledTime;
	}
	
	public List<ScheduledTime> findByUserId(Long id) {
	    return repository.findByUserId(id);
	}
	
	public ResponseEntity<List<ScheduledTimeResponse>> findScheduledTimeByUserId(String token) {
        Long userId = userService.getUserByToken(token);
        List<ScheduledTime> scheduledTimes = findByUserId(userId);
        if (scheduledTimes != null && !scheduledTimes.isEmpty()) {
            LocalDate currentDate = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
            List<ScheduledTime> filteredScheduledTimes = filterByCurrentDate(scheduledTimes, currentDate);
            if (!filteredScheduledTimes.isEmpty()) {
                List<ScheduledTimeResponse> listResponse = new ArrayList<>();
                filteredScheduledTimes.forEach(scheduledTime -> {
                    listResponse.add(ScheduledTimeResponse
                                        .builder()
                                        .time(scheduledTime.getDateTime().toLocalTime())
                                        .build());
                });
                return ResponseEntity.status(HttpStatus.OK).body(listResponse);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
	
    private List<ScheduledTime> filterByCurrentDate(List<ScheduledTime> scheduledTimes, LocalDate currentDate) {
        return scheduledTimes.stream()
                .filter(scheduledTime -> scheduledTime.getDateTime().toLocalDate().isEqual(currentDate))
                .limit(3)
                .collect(Collectors.toList());
    }

}
