package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * HTTP request 패키징 방식 
 * 1. Request Line : URL, Method Protocol/ver
 * 	getRequestURI,URL, getMethod, getProtocol
 * 	** Method : 현재 요청의 목적, 요청 패키징 방식
 * 		1) GET(Read)=  select : 일반적으로 클라이언트의 요청을 기본 get방식.
 * 		2) POST (Create) : 서버상에 새로운 리소스 생성 
 * 							***** Request Body 영역 생성 
 * 		3) PUT (Update) : 서버상의 리소스(데이터) 갱신
 * 		4) Delete(Delete): 서버상의 리소스 삭제
 * 		Header. Option, Trace, Connect(데이터오고갈려면 연결 필요해. 제일먼저), Patch(=put과 비슷) 
 * 
 * 2. Request Header : 전송 데이터(클라이언트)에 대한 부가정보(meta data)
 * 					 :  일반적으로 요청의 모든정보는 문자열의 형태로 전달됨. 
 * 					** 문자열 데이터에 특수문자가 포함된 경우 ! 
 * 					RFC2396규약에 따라 %인코딩(URL인코딩)방식으로 인코딩되어 전달됨.
 * 		String Value = getHeader(headerName)
 * 					   Enumneration(:collection view) getheaderNames
 * 3. Request(Message/Content) Body  : method가 post일 경우 만들어짐. 
 * 									   : 서버로 전송할 컨텐츠가 포함됨. 
 * 		** 요청 파라미터를 확보하는 방법
 * 		1)method GET : Request Line을 통해 전달됨. 
 *		URL ? -> queryString
 *			queryString :section1&section2&section3.... ?뒤에 붙은걸 "queryString"
 *						 section : name=value
 *		ex) gugudan?minDan=2&maxDan =4
 * 		2)method POST : Request Body 를 통해(section1&section2&section3 이것이 ) 전달되.
 *  --이것들이 필요하진않지만 Map 쓰면돼
 *  parameterMap 을 통해 모든 요청 파라미터를 확보함.
 *  String getParamether(name) : 하나밖에못 뽑아 . String[] getParameterValues(name)
 *  Map<String,String[]> getParameterMap(), getParameterNames()
 * 
 * 	*** 특수 문자가 포함된 파라미터는 ???? %인코딩 된 파라미터....
 *  1. POST : setCharacterEncoding - body 영역의 decoding방식.
 *  2. GET  : server 설정 필요.
 *  		 server.xml : http connector 의 설정 변경 
 *  		 - URIEncoding : charset이 고정됨.
 *  		 - useBodyEncodingForURI : get 메소드에서 setCharacterEncoding를 사용함. 
 *  
 * 
 *  *** HttpServletRequest : http 프로토콜에 따라 패키징된 요청에 대한 
 *  						정보를 캡슐화 한 객체.(middleware == tomcat에 의해 수행) 
 *  						클라이언트와 요청에 대한 모든 정보가 포함됨.
 * 
 *
 */
@WebServlet("/httpRequest")
public class HttpRequestDescServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//꼭 super빼야함.
		//왜 get은 많고 set은 적은가 => 클라이언트가 정보 주는거여서 
		//request line 의 정보 : URL, Method, HTTP
		String requestURI = req.getRequestURI();
		String requestMethod = req.getMethod();
		String protocol = req.getProtocol();
		System.out.println(requestURI);
		System.out.println(requestMethod);
		System.out.println(protocol);
		
		//동작하기위해선 doget이 요청되어야함. ->@WebServlet("/httpRequest") 쓰기
		
		//request header, iterator(set , enum : 가상의 컬렉션 ,컬렉션 뷰 
		Enumeration<String> en = req.getHeaderNames();
		while (en.hasMoreElements()) {
			String headerName = (String) en.nextElement();
			String headerValue = req.getHeader(headerName);
			System.out.printf("헤더 이름, 값, : %s : %s\n", headerName,headerValue);
		}
		
		//request body : POST
		InputStream is =req.getInputStream();
		System.out.println("바디크기 : " + is.available()); //들어있는 바디의 크기가얼마인지 알려줌 이게 돌아갈려면 바디가 있어야해 
		//form구성한적 없으니 무조건 get방식 ! 
		//body 확인하고 싶으면 form태그 발생하고 POST잇어야해
		
		//요청파라미터 확보방법
		// get : line을 통해 전달
		// post: body 통해 전달 
		// 파라미터 명 == 입력 태그의 name 속성 이 ()안에 들어가야해 
		// 서버에 넘길때도 String 으로
		// 파라미터는 특별한 설정없으면 문자열로 전송됨. 
		String value = req.getParameter(""); //?파라미터 명을 어캐 설정할것이냐???? 방법1
		// 하나의 파라미터명으로여러개의 값이 전송될떄 사용 .
		String[] values = req.getParameterValues(""); //방법2 둘의 차이는? 하나로 여러개 배열 가능함
//		key : 파라미터명, value : 파라미터값
		Map<String, String[]> pMap = req.getParameterMap(); //값이 배열임.
		//넘길땐 검증 해야해 
		Enumeration<String> names = req.getParameterNames();
		
		
	}

}
