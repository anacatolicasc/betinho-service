package com.pac6.betinho.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pac6.betinho.dto.ScheduledTimeResponse;
import com.pac6.betinho.model.ScheduledTime;
import com.pac6.betinho.service.ScheduledTimeService;

@RestController
@RequestMapping("/scheduledTime")
public class ScheduledTimeController {
	
	private ScheduledTimeService scheduledTimeService;
	
	@Autowired
	public ScheduledTimeController(ScheduledTimeService scheduledTimeService) {
		this.scheduledTimeService = scheduledTimeService;
	}
	
	@GetMapping("/getAll")
    public ResponseEntity<List<ScheduledTimeResponse>> findScheduledTime(@RequestParam String token) {
		System.out.println("Linha 32");
        return scheduledTimeService.findScheduledTimeByUserId(token);
    }
	
	@PostMapping
	public ResponseEntity<ScheduledTime> createScheduledTime (@RequestBody ScheduledTime scheduledTime, @RequestParam String token) {
		return scheduledTimeService.createScheduledTime(scheduledTime, token);
	}
	
	@PutMapping
	public ResponseEntity<ScheduledTime> updateScheduledTime (@RequestBody ScheduledTime scheduledTime) {
		return ResponseEntity.status(201).body(scheduledTimeService.update(scheduledTime));
	}
}
