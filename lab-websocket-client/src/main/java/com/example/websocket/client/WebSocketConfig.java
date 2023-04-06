package com.example.websocket.client;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import java.lang.reflect.Type;
import java.net.URI;

@Configuration
public class WebSocketConfig {
	
	@Bean
	public WebSocketStompClient stompSessionHandler() {
		
//		WebSocketClient client = new StandardWebSocketClient();
//		WebSocketStompClient stompClient = new WebSocketStompClient(client);
//		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
//		StompSessionHandler sessionHandler = new MyStompSessionHandler();
//		stompClient.connect(URL, sessionHandler)
		
		
		WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		return stompClient;
	}
	
	@Bean
	public StompSessionHandler sessionHandler() {
		return new MyStompSessionHandler();
	}
	
	@Bean
	@SneakyThrows
	public StompSession session() {
		
		var session = stompSessionHandler().connect(URI.create("ws://localhost:8080/ws"), null, null, sessionHandler()).get();
		
		session.subscribe("/topic/response", new StompFrameHandler() {
			@Override
			public Type getPayloadType(StompHeaders headers) {
				return Content.class;
			}
			
			@Override
			public void handleFrame(StompHeaders headers, Object payload) {
				System.out.println("Received message: " + payload);
			}
		});
		
		System.out.println("Subscribed to /topic/events!");
		
		return session;
	}
}
