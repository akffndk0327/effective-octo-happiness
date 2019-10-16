package kr.or.ddit.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application Lifecycle Listener implementation class SampleTestListener
 *
 */
//@WebListener //원래는 web.xml에 등록해야함 ㅊ
public class SampleTestListener 
	implements ServletContextListener, HttpSessionListener,ServletRequestListener
		,ServletContextAttributeListener,HttpSessionAttributeListener, ServletRequestAttributeListener{

	private static Logger logger = LoggerFactory.getLogger(SampleTestListener.class);
	//제일 먼저 실행됨. 
	//ex 누적방문자 수 구할 때 여기랑 create 쓰면됨 
	//ex 현재방문자 수  세션소멸되면 0 만들어 + 비어있는 리스트 만ㄷ르어서 attr~remove,~에서 사용하기 
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//서버 스타트 됐을때...
		logger.info("서버구동시작");
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("어플리케이션 종료");
	}
	//================================

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		String id = se.getSession().getId(); //세션아이디 받아올수잇어
		logger.info("{} 세션 시작", id);
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		String id = se.getSession().getId(); //세션아이디 받아올수잇어
		logger.info("{} 세션 소멸", id);
		
	}
	//===================================
	
	//하나의 요청이 서버에 도착하면 실행함 .
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		
	}
	//응답데이터 나가기 직전에 발생함 .
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		
	}
	//================================================================

	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		// TODO Auto-generated method stub
		
	} 
	//==========================================================

	//위에거랑 타입? 이 다름 
	@Override 
	public void attributeAdded(HttpSessionBindingEvent event) {
		String name = event.getName();
		logger.info("session scope add : {}",name);
	}

	@Override //여기서 현재유저 뺌
	public void attributeRemoved(HttpSessionBindingEvent event) {
		String name = event.getName();
		logger.info("session scope remove : {}",name);
			
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}
	//================================================================

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		
	}


    
}
