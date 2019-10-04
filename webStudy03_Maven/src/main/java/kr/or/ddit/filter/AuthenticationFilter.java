package kr.or.ddit.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.taglibs.standard.lang.jstl.ArraySuffix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 인증형 서비스를 요청한느 경우 - 이메일 같은거 
 * 현제 요청을 발생시킨유저으 ㅣ인증ㅇ여부를 확인 
 * @author PC-03
 *
 */
public class AuthenticationFilter implements Filter{
	private Map<String, String[]> securedMap;
	public static final String ATTRNAME = "securedMap";
	private String securedPath ;
	private static Logger logger = LoggerFactory. getLogger(AuthenticationFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		securedPath = filterConfig.getInitParameter("securedPath");
		//프로퍼티명 받기
		Properties props = new Properties();
		try(
				InputStream is = getClass().getResourceAsStream(securedPath);
		){
			props.loadFromXML(is);
			
		}catch(IOException e) {
			throw new ServletException(e);
		}
		securedMap = new HashMap<String, String[]>();
		Iterator<Object> it = props.keySet().iterator();//여기에 정보 다잇어
		while (it.hasNext()) {
			String uri = (String) it.next();
			String value = props.getProperty(uri).trim();
			value.split(","); //, 기준으로 쪼개기
			String[] roles = value.split(",");
			Arrays.sort(roles); //여기서 미리 정렬 시키기 ! 
			securedMap.put(uri,roles);
			logger.info("인가 정보 - {} : {}", uri, Arrays.toString(roles));
		}
		filterConfig.getServletContext().setAttribute(ATTRNAME, securedMap);
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			String uri = req.getRequestURI(); //contextPath, session 잇을수도이어
			uri = uri.substring(req.getContextPath().length()); //front 에서 한 작업
			uri = uri.split(";")[0];
			boolean pass = true;
			if(securedMap.containsKey(uri)) {
				Object authMember = req.getSession().getAttribute("authMember");
				if(authMember == null) { //로그인 안된 상황
					pass = false;
				}
				
			}
			if(pass) {
				chain.doFilter(request, response);
				
			}else  {//인증안됨
				req.getSession().setAttribute("message", "인증형 서비스는 로그인 필요함");
				resp.sendRedirect(req.getContentType()+"/login");
			}
	}	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
