package kr.or.ddit.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//클라이언트 블라인드 처리
public class BlindFilter implements Filter {
	private static Logger logger = LoggerFactory.getLogger(BlindFilter.class);
	
	//차단할 대상의 ip, 차단 reason 
	private Map<String, String> blindMap;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{}필터생성", getClass().getSimpleName());
		blindMap = new HashMap<String, String>();
		blindMap.put("192.168.206.16", "이쁘닌까 차단!!!");
//		blindMap.put("127.0.0.1", "내꺼차단1");
//		blindMap.put("0:0:0:0:0:0:0:1", "내꺼차단");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String clientIP = request.getRemoteAddr(); //클라이언트 ip뽑기
		boolean pass = true;
		//계속 무한루프 돌수있느닌 까 클라이언트 요청의 uri확인하기 
		String messagePage="/14/messagePage.jsp";
		String uri = req.getRequestURI();
		if (!uri.contains(messagePage)) { // 잇으면 통과, 없으면 통과안시키고 밑에 if로 들어가고 블라인드 여부 확인해
			if (blindMap.containsKey(clientIP)) {
				pass = false;
			}
		}
		
		if(pass) {
			//다음필터있으면 필터로 넘기던가 다음 자원으로 넘기기
			chain.doFilter(request, response);
		}else {
			//메서지 먼저 꺼내기
			req.getSession().setAttribute("message",blindMap.get(clientIP)); //세션으로 공유해
			//메세지 보내고 페이지 이동 
			//정보남길필요없으닌까 redirect
			resp.sendRedirect(req.getContextPath()+messagePage);
		}
	}

	@Override
	public void destroy() {
		logger.info("{}필터 소멸 ", getClass().getSimpleName());
		
	}

}
