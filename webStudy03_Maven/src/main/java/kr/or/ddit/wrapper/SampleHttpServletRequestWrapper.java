package kr.or.ddit.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class SampleHttpServletRequestWrapper extends HttpServletRequestWrapper{

	public SampleHttpServletRequestWrapper(HttpServletRequest request) {//warpping할 객체를 받아야함 
		super(request);
	} 
@Override
	public String getParameter(String name) {
		if("who".equals(name)) { //얘만 비정상 처리 
			return "b001";
		}
		return super.getParameter(name);
	}

}
