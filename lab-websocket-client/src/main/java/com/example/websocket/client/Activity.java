package com.example.websocket.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Activity {
	
	private User user;
	private ActivityType type;
	private LocalDateTime date = LocalDateTime.now();
	
}
