package com.example.websocket.server;

import lombok.AllArgsConstructor;
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
