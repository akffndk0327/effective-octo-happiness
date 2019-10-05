package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.HandlerInvoker;
import kr.or.ddit.mvc.annotation.HandlerMapper;
import kr.or.ddit.mvc.annotation.IHandlerInvoker;
import kr.or.ddit.mvc.annotation.IHandlerMapper;
import kr.or.ddit.mvc.annotation.URIMappingInfo;

public class FrontController extends HttpServlet {
	private IHandlerMapper handlerMapper;
	private IHandlerInvoker handlerInvoker;
	private IViewProcessor viewProcessor;
	
	//<load-on-startup>1</load-on-startup> :web.xml에 생성하기 
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//web.xml로 가기 init-param 설정하기
		String packages = config.getInitParameter("basePackages"); //web.xml에서 공백으로 정보 구분해서 넘기고 있음 .  요청이 들어오기 전에 실행해야해
		String[] basePackages = packages.split("\\s+"); //공백이 하나 이상 있으면 
		handlerMapper = new HandlerMapper(basePackages); //HandlerMapper 1
		handlerInvoker = new HandlerInvoker();
		viewProcessor = new ViewProcesser();
		viewProcessor.SetPreFix(config.getInitParameter("prefix"));
		viewProcessor.SetSuffFix(config.getInitParameter("suffix"));
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//템플릿, hook메소드 호출안해
		//대신에 뒷단에서 실행되는 메소드 호출해
		//인터페이스 가져와야헤
		//상단, 하단에서 반복되는거 뺄려고 만든 메섣,
		
		req.setCharacterEncoding("UTF-8");
		URIMappingInfo handlerinfo = null;
		//핸들러 호출해서 뷰네임 받아와야함 
		//1.핸들러 매퍼로 핸들러 찾기2. 3.해당 메소드업으면 여기까지 예외 올수있어 -illegal~ 
		try {
			handlerinfo = handlerMapper.findCommandHandler(req);
		}catch (IllegalArgumentException e) { //메소드 없어
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,e.getMessage());
			return;
		}
		if(handlerinfo ==null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "제공하지 않는 서비스 입니다"); //404 
			return ; 
		}
		
		//통과하면
		//인보크로 찾기
		String viewName;
		try {
			viewName = handlerInvoker.invokeHandler(handlerinfo, req, resp); //handlerinfo 핸들러 정보넘기고 
			if(viewName ==null && !resp.isCommitted()) { //!resp.isCommitted():응답 나갓나 확인 -> 실패함 개발자 잘못. 문제3
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"viewName이 없어요");
				return ;
			}else if(viewName != null) {
				//이동코드
				viewProcessor.viewProcess(viewName, req, resp);
			}
		} catch (Exception e) {
			throw new ServletException(e); //e: 원본유지 해주기. 예:외종류 바꿔치기 결국 예왼느 톰캣이 가젹 ㅏ
		}	
		
		
	}
}


























