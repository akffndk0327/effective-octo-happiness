package kr.or.ddit.note.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import kr.or.ddit.note.service.INoteService;

@Repository
public class NoteWebsocketController extends TextWebSocketHandler {

	@Autowired
	SqlSession sqlsession;

	@Inject
	INoteService service;

	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();

	private static Logger logger = LoggerFactory.getLogger(NoteWebsocketController.class);

	@Override

	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
		logger.info("{} 연결 끊김.", session.getId());
	}

	@Override

	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.add(session);
		logger.info("{} 연결됨", session.getId());
	}

	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		for (WebSocketSession webSocketSession : sessionList) {
				CharSequence cnt = service.countUnreadNotes(message.getPayload()) + "";
				webSocketSession.sendMessage(new TextMessage(message.getPayload()+","+cnt));
		}
	}
	
}
