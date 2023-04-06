package com.example.websocket.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig2 implements WebSocketConfigurer {
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new MyWebSocketHandler(), "/my-websocket");
	}
	
	class MyWebSocketHandler implements WebSocketHandler {
		
		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			// Connection established.
			System.out.println("Connection established.");
		}
		
		@Override
		public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
			// Message received.
			System.out.println("Message received");
		}
		
		@Override
		public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
			// Transport error occurred.
			System.out.println("Transport error occurred");
		}
		
		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
			// Connection closed.
			System.out.println("Connection closed.");
		}
		
		@Override
		public boolean supportsPartialMessages() {
			System.out.println("supportsPartialMessages");
			return false;
		}
	}
	
}
