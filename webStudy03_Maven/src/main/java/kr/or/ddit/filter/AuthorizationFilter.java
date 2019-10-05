package kr.or.ddit.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.vo.MemberVO;

public class AuthorizationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Map<String, String[]> securedMap = 
				(Map<String, String[]>) request.getServletContext().getAttribute(AuthenticationFilter.ATTRNAME);
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String uri = req.getRequestURI(); //contextPath, session 잇을수도이어
		uri = uri.substring(req.getContextPath().length()); //front 에서 한 작업
		uri = uri.split(";")[0];
		
		String[] roles = securedMap.get(uri);
		boolean pass = true;
		if(roles!= null) { //보호 필요없는 자원 은 통과 시키기
			MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
			String mem_role = authMember.getMem_role();
			if(Arrays.binarySearch(roles, mem_role)<0) { //권한이 없다 
				pass = false; //졸려.......잠와....zzzz
			}
		}
		if(pass) {
			chain.doFilter(request, response);
		}else {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
