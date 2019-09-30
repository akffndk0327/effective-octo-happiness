package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
@CommandHandler
public class selectBuyerList{
	IBuyerService service = BuyerServiceImpl.getInstance();
	
	@URIMapping("/buyer/buyerList")
	public String BuyerList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accept = request.getHeader("Accept");
		List<BuyerVO> list = service.selectNameList();

		String viewName = null;
		if (
			accept.toLowerCase().contains("json")
		) {
			response.setContentType("application/json;charset=UTF-8");
			String json = new MarshallingUtils().marshalling(list);

			try (PrintWriter out = response.getWriter();) {
				out.println(json);
			}

		} else {
			viewName = "buyer/buyer";
		}
		return viewName;
	}
}