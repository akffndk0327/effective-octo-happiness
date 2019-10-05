package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.wrapper.MultipartRequestWrapper;
//파일업로드되고잇는지 확인위해서 
public class MultipartRequestCheckFilter implements Filter  {
	private static Logger logger = LoggerFactory.getLogger(MultipartRequestCheckFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("{}필터 생성", getClass().getSimpleName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//1. 파일업로드 여부 확인
		HttpServletRequest req = (HttpServletRequest) request;
		String bodyMime = req.getContentType();
		//method타입보기 
		if(bodyMime != null && bodyMime.startsWith("multipart/")) {
			//2. 업로드된 파일의 전처리할  Wrapper  생성
			MultipartRequestWrapper requestWrapper = new MultipartRequestWrapper(req);
			//요청인것처럼 넘겨서 속이기
			chain.doFilter(requestWrapper, response); //업로드 할때 ㄴwrapper로 쓰기 
			
		}else {
			chain.doFilter(request, response); //일반요청일땐 원본요청
		}
		
	}

	@Override
	public void destroy() {
		logger.info("{}필터 소멸", getClass().getSimpleName());
	}
		
	

}
