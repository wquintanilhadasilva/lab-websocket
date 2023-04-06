package com.example.websocket.client;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import javax.annotation.PostConstruct;

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
	
	@PostConstruct
	@SneakyThrows
	public void start() {
		WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		stompClient.setTaskScheduler(new DefaultManagedTaskScheduler());
		
		MyStompSessionHandler2 sessionHandler = new MyStompSessionHandler2();
		
		String url = "ws://localhost:8080/ws";
		StompSession stompSession = stompClient.connect(url, sessionHandler).get();
	}
	
	public class MyStompSessionHandler2 extends StompSessionHandlerAdapter {
		@Override
		public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
			session.subscribe("/topic/response", this);
			var usr = new User();
			usr.setUserName("Olá, servidor!");
//			session.send("/app/join", "Olá, servidor!");
			session.send("/app/join", usr);
		}
		
		@Override
		public void handleFrame(StompHeaders headers, Object payload) {
			// Processa a mensagem recebida do servidor
			System.out.println("Mensagem recebida: " + payload.toString());
		}
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
