package com.pac6.betinho.dto;

import java.time.LocalTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScheduledTimeResponse {
	private final LocalTime time;
}
