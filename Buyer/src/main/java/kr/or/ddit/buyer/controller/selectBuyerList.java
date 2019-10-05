package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
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
	      int currentPage = 1;
	      if(StringUtils.isNumeric(pageParam)) {
	         currentPage = Integer.parseInt(pageParam);
	      }
	      
	      String accept = request.getHeader("Accept");
	      
	      PagingInfoVO<BuyerVO> pagingVO = new PagingInfoVO<BuyerVO>(3, 2);
	      
	      int totalRecord = service.selectBuyerCount(pagingVO);
	      pagingVO.setTotalRecord(totalRecord);
	      pagingVO.setCurrentPage(currentPage);
	      
	      List<BuyerVO> list = service.selectNameList(pagingVO);
	      System.out.println(list);
	      pagingVO.setDataList(list);
	      
	      String viewName = null;
	      if (accept.toLowerCase().contains("json")) {
	         response.setContentType("application/json;charset=UTF-8");
	         String json = new MarshallingUtils().marshalling(pagingVO);

	         try (PrintWriter out = response.getWriter();) {
	            out.println(json);
	            return null;
	         }

	      } else {
	    	  return "buyer/buyer";
	      }
	   }
}