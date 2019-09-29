package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.MarshallingUtils;

/**
 * Servlet implementation class BuyerServlet
 */
//@WebServlet("/BuyerListServlet")
@CommandHandler
public class BuyerListServlet  {
	private static final long serialVersionUID = 1L;
	
	@URIMapping("/BuyerListServlet")
	public String SelectBuyer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("Accept");

		IBuyerService service = BuyerServiceImpl.getInstance();
		List<BuyerVO> list = service.selectBuyerList();

		if (accept.toLowerCase().contains("json")) { // 비동기
			resp.setContentType("application/json;charset=UTF-8");

			// json을 만들기위한 marshalling
			String json = new MarshallingUtils().marshalling(list);

			try (PrintWriter out = resp.getWriter();) {
				out.println(json);
			}
			return null; // 0924 d여기 처리해야돼!!

		} else {
			// 동기
			// 0924
//			String viewName = "WEB-INF/buyer.jsp";
//			req.getRequestDispatcher(viewName).forward(req, resp);
			String viewName = "buyer/buyer"; // -\=> 논리적인 viewName = logical~
			return viewName;
		}

	}


}
