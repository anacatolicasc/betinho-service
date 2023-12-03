package com.pac6.betinho;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import jakarta.transaction.Transactional;

import com.pac6.betinho.model.ScheduledTime;
import com.pac6.betinho.repository.ScheduledTimeRepository;
import com.pac6.betinho.service.UserService;

@SpringBootApplication
public class BetinhoApplication {
	
	@Autowired
    private ScheduledTimeRepository scheduledTimeRepository;
	
	private UserService userService;
	
    @Autowired
    public BetinhoApplication(ScheduledTimeRepository scheduledTimeRepository, UserService userService) {
        this.scheduledTimeRepository = scheduledTimeRepository;
        this.userService = userService;
    }
	
    public static void main(String[] args) {
        SpringApplication.run(BetinhoApplication.class, args);
    }
    
    @Bean
    @Transactional
    public CommandLineRunner criarHorariosAoIniciar() {
        return args -> {
            LocalDateTime today = LocalDateTime.now().with(LocalTime.MIN);
            
            if (!existemHorarios(today)) {
                criarHorario(today.withHour(12));
                criarHorario(today.withHour(16));
                criarHorario(today.withHour(20));
            }
        };
    }

    private void criarHorario(LocalDateTime scheduledDatetime) {
        try {
            ScheduledTime newScheduledTime = new ScheduledTime();
            newScheduledTime.setDateTime(scheduledDatetime);
            newScheduledTime.setUser(userService.getUserById(1L));
            scheduledTimeRepository.save(newScheduledTime);
        } catch (Exception e) {
            
        }
    }
    
    private boolean existemHorarios(LocalDateTime today) {
        List<ScheduledTime> horariosExistem = scheduledTimeRepository.findByDateTimeBetween(
                today.withHour(0).withMinute(0).withSecond(0),
                today.withHour(23).withMinute(59).withSecond(59)
        );

        return !horariosExistem.isEmpty();
    }
}

