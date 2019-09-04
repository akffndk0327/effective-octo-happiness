package kr.or.ddit.servlet01;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 190827
 * Servlet 이란 무엇인가요??? 척척박사님 알려주세요 ^_^
 * 하나의 객체가 웹상에서 동작 할 수잇는 자바 객체에 대한 모든 정의를 가진 스펙
 * 서블릿 스펙에 따른 모듈을 개발하는 단계
 * 
 * 1.HttpServlet 하위 구현체 정의(DescriptionServlet)
 * 	: 필요한 "callback" 메소드를 overriding 함. 척척박사님 졸려요 쉬고싶어요  
 *	: callback 메소드란? 특정 이벤트가 발생한 경우 , 시스템에 의해 자동 호출되는 ㄱ구조.- javafx에서 들어왓데요.. ^^ 모르겟네요 
 *
 * 2. 컴파일 -> 해당 컨텍스트의 classpath에 배치 (/WEB-INF/classes)
 * 
 * 3. web.xml에 등록/매핑
 *    @WebServlet(servlet 3.0 ..)
 *    ** 등록 설정 종류 
 *    1) loadOnStartUp : 설정된 순서에 따라 서버가 구동되면 자동으로 서블릿 객체가 생성됨
 *    2) init-param : 서블릿 객체 생성 후 초기화 시점에(한번만) 파라미터를 전달 
 *    서블릿의 팔라파이드?
 *    
 * 4. 서버(WAS, Servlet Container)를 재구동. 
 * 
 * ** 서블릿의 콜백 메소드 종류
 * 1. lifecycle callback : 생명주기 내에서 한번씩만 실행된느 메소드(init, destory)
 * 2. request callback(**제일중요해 ! 클라이언트 요청이닌까 ! ) : template method pattern
 * 		1) service : 요청을 받고, 요청의 조건(request method)를 판단하여, 
 * 					 해당 callback(doXXX)메서드를 호출하는 역할.
 * 					 template method
 * 		2) doXXX : 단일 책임의 원칙을 적용하여 메소드 구조를 분리함. 
 * 					hook method
 * 
 * ** Servlet Container(Web Application Server, tomcat) 
 * 	  : Servlet의 lifecycleㅇ르 관리하며, 매핑된 요청이 발생하면, 
 * 		해당 서블릿의 콜백을 호출하여 요청에 대한 처리를 하는 주체. 
 * 
 * ** JSP Container(WAS, tomcat) : 둘의 사전 작업이 다름 .
 * 	  : JSP의 lifecycleㅇ르 관리하며, 매핑된 요청이 발생하면, 
 * 		해당 서블릿의 콜백을 호출하여 요청에 대한 처리를 하는 주체. 
 * 
 * 컨테이너 : 물건관리해 . ->문짝열어서  이걸 꺼내서 써야되는걸 허가해야해.
 *     
 *    
 *
 */
//@WebServlet("/desc")
public class DescriptionServlet extends HttpServlet {
	
	public DescriptionServlet() {
		super();
		System.out.printf("%s 생성\n", this.getClass().getName()); //포맷핑하겟다
	}
	
	String paramValue;
	@Override
	public void init(ServletConfig config) throws ServletException { //ServletConfig config : web.xml의 모든 정보 다 갖고잇어.
		super.init(config);
		paramValue = config.getInitParameter("param");
	}
	
	@Override //이런건 없다라고 간주하는게 속편해 이게 실행 안될수도 잇어 
	public void destroy() {
		super.destroy();
		System.out.printf("%s 객체 소멸", getClass().getName());
	}
	
	@Override //요청들어오면 무조건 여기 호출 => 템플릭메소드패턴 =>순서는 같은데 특정부분의 디테일이 다름 
	// 순서정의작업함
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		System.out.println(getServletContext().hashCode()); //같은 객체... 서블릿해쉬코드가 같어  1752984099
		System.out.println("servic 메소드 실행시작 ^_^");
		super.service(arg0, arg1); 
		System.out.println("servic 메소드 실행 종료 ㅠ_ㅠ");
	}
	
	
	//특정부분 작업함 => hook메서드 끌어당겨서 호출함. 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.printf("%s 가 실행중.\n ", Thread.currentThread().getName()); //현재출력되는 스레드 이름 출력
		System.out.printf("전달된 초기화 파라미터값 : %s\n", paramValue);
	}
	
	
	
}















