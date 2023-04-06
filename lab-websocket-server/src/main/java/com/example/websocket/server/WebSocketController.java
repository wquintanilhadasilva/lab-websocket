//package com.example.websocket.server;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.event.EventListener;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.socket.messaging.SessionDisconnectEvent;
//import java.util.Iterator;
//
//@Controller
//@RequiredArgsConstructor
//public class WebSocketController {
//
//	private static final Content CONTENT = new Content();
//
//	private final SimpMessageSendingOperations message;
//
//	@MessageMapping("/join")
//	@SendTo("/topic/response")
//	public Content join(@Payload User user, SimpMessageHeaderAccessor accessor) {
//		CONTENT.getUsers().add(user);
//		CONTENT.getActivity().add(Activity.builder()
//				.user(user).type(ActivityType.JOIN).build());
//		accessor.getSessionAttributes().put("user", user);
//		return CONTENT;
//	}
//
//	@MessageMapping("/colaborate")
//	@SendTo("/topic/response")
//	public Content join(@Payload Colaborate colaborate, SimpMessageHeaderAccessor accessor) {
//		CONTENT.setContent(colaborate.getContent());
//		return CONTENT;
//	}
//
//	@EventListener
//	public void socktDisconnect(SessionDisconnectEvent event) {
//		// Atualiza o "cache" com a lista de usuários conectados e o hitórico de conteúdo
//		StompHeaderAccessor wrap = StompHeaderAccessor.wrap(event.getMessage());
//		if (wrap.getSessionAttributes().containsKey("user")) {
//			User user = (User) wrap.getSessionAttributes().get("user");
//			Iterator<User> iterator = CONTENT.getUsers().iterator();
//			while(iterator.hasNext()) {
//				var current = iterator.next();
//				if (current.getUserName().equals(user.getUserName())) {
//					iterator.remove();
//				}
//			}
//			CONTENT.getActivity().add(Activity.builder()
//					.user(user).type(ActivityType.LEAVE).build());
//		}
//		// broadcast com o conteúdo do histórico
//		message.convertAndSend("/topic/response", CONTENT);
//	}
//}
