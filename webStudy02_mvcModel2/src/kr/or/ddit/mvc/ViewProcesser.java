package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewProcesser implements IViewProcessor {
	private String prefix;
	private String suffix;
	
	@Override
	public void SetPreFix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public void SetSuffFix(String suffix) {
		this.suffix= suffix;
	}

	@Override
	public void viewProcess(String viewName, HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

	}

}
