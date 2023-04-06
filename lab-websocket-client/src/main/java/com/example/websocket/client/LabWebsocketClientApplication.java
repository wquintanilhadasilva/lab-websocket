package com.example.websocket.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@SpringBootApplication
public class LabWebsocketClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabWebsocketClientApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public ThreadPoolTaskScheduler taskScheduler() {
		return new ThreadPoolTaskScheduler();
	}
	
//	@Bean
//	public WebSocketStompClient stompSessionHandler() {
//		WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
//		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
//		return stompClient;
//	}
//
//	@Bean
//	public StompSessionHandler sessionHandler() {
//		return new MyStompSessionHandler();
//	}

}
