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

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.buyer.vo.PagingInfoVO;
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
		String pageParam = request.getParameter("page");
		int currentPage =1;
		if(
			StringUtils.isNumeric(pageParam)
		) {
			currentPage = Integer.parseInt(pageParam);
		}
		// 동기/비동기 구분을 해 응답데이터를 넘기기 위함
		String accept = request.getHeader("Accept");
		PagingInfoVO<BuyerVO> pagingVO = new PagingInfoVO<BuyerVO>(3,3);
		int totalRecord = service.selectBuyerCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setTotalPage(currentPage);
		
		List<BuyerVO> list = service.selectNameList(pagingVO);
		pagingVO.setDataList(list);

		String viewName = null;
		//마샬링 대상 바뀜 list -> pagingVO
		if (
			accept.toLowerCase().contains("json")
		) {
			response.setContentType("application/json;charset=UTF-8");
			String json = new MarshallingUtils().marshalling(pagingVO);

			try (PrintWriter out = response.getWriter();) {
				out.println(json);
			}

		} else {
			viewName = "buyer/buyer";
		}
		return viewName;
	}
}