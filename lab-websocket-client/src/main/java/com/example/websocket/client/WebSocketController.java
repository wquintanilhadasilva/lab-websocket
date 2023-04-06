package com.example.websocket.client;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import java.lang.reflect.Type;

@Controller
@RequestMapping
@AllArgsConstructor
public class WebSocketController {
	
//	private final StompSessionHandler sessionHandler;
	private final WebSocketStompClient stompClient;
//	private final RestTemplate restTemplate;
//	private final ThreadPoolTaskScheduler taskScheduler;
	private final StompSession session;
	
//	public WebSocketController(StompSessionHandler sessionHandler, WebSocketStompClient stompClient,
//							   RestTemplate restTemplate, ThreadPoolTaskScheduler taskScheduler) {
//		this.sessionHandler = sessionHandler;
//		this.stompClient = stompClient;
//		this.restTemplate = restTemplate;
//		this.taskScheduler = taskScheduler;
//	}
	
	@GetMapping("/{user}")
	@ResponseBody
	public String sendMessage(@PathVariable("user") String user) throws Exception {
//		StompSession session = stompClient.connect("ws://localhost:8080/ws", sessionHandler).get();
		var usr = new User();
		usr.setUserName(user);
		session.send("/app/join", usr);
		return "Message sent!";
	}
	
	@GetMapping("/events")
	@ResponseBody
	public String getEvents() throws Exception {
//		StompSession session = stompClient.connect("ws://localhost:8080/ws", sessionHandler).get();
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
		return "Subscribed to /topic/events!";
	}

}
