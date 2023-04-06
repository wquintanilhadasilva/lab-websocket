package com.example.websocket.client;

import org.springframework.lang.Nullable;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {
	
	@Override
	public void handleFrame(StompHeaders headers, @Nullable Object payload) {
		// Processa a mensagem recebida do servidor
		System.out.println("Mensagem recebida: " + payload.toString());
	}
	
	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
		// Lida com exceções que podem ser lançadas durante a conexão ou envio de mensagens
		System.err.println("Erro durante a conexão com o servidor WebSocket: " + exception.getMessage());
	}
}
