package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.MarshallingUtils;

@CommandHandler
public class InsertBuyer{
	
	@URIMapping(value="/buyer/InsertBuyer", method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		String accept = req.getHeader("Accept");
		IBuyerService service = BuyerServiceImpl.getInstance();
		BuyerVO vo = new BuyerVO();
		
		try {
			BeanUtils.populate(vo, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Map<String, String> errors = new HashMap<String, String>();
		req.setAttribute("errors", errors);

		int result = service.buyerInsert(vo);
		
		String viewName = null;
		if (accept.toLowerCase().contains("json")) {
			resp.setContentType("application/json;charset=UTF-8");
			String json = new MarshallingUtils().marshalling(vo);
			try (PrintWriter out = resp.getWriter();

			) {
				out.print(json);
			} 
		} else {
			viewName = "/buyer/buyer";
			req.setAttribute("vo", vo);
		}
		return viewName;
	}
}
