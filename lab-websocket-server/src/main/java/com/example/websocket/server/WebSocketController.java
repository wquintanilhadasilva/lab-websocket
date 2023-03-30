package com.example.websocket.server;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Controller
public class WebSocketController {
	
	private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());
	
	@MessageMapping("/send")
	public void sendMessage(Message message) throws Exception {
		for (WebSocketSession session : sessions) {
			session.sendMessage(new TextMessage(message.getContent()));
		}
	}
	
	@MessageMapping("/news")
	@SendTo("/topic/news")
	public String broadcastNews(@Payload String message) {
		return message;
	}
}
