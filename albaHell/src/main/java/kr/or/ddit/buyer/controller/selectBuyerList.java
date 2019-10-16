package kr.or.ddit.buyer.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.buyer.vo.PagingInfoVO;

/**
 * Servlet implementation class BuyerServlet
 */
@Controller
public class selectBuyerList{
	@Inject
	IBuyerService service ;
	
	@RequestMapping("/buyer/buyerList")
	@ResponseBody
	public String BuyerList(
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage //넘어갈때 넘어올때 name으로 받는다
			,Model model ){
//		String pageParam = request.getParameter("page");
//	      int currentPage = 1;
//	      if(StringUtils.isNumeric(pageParam)) {
//	         currentPage = Integer.parseInt(pageParam);
//	      }
//	      
//	      String accept = request.getHeader("Accept");
	      
	      PagingInfoVO<BuyerVO> pagingVO = new PagingInfoVO<BuyerVO>(3, 2);
	      int totalRecord = service.selectBuyerCount(pagingVO);
	      pagingVO.setTotalRecord(totalRecord);
	      pagingVO.setCurrentPage(currentPage);
	      
	      List<BuyerVO> list = service.selectNameList(pagingVO);
	      System.out.println(list);
	      pagingVO.setDataList(list);
	      
	      model.addAttribute("pagingVO",pagingVO);
	      
	      String viewName = null;
//	      if (
//	    	accept.toLowerCase().contains("json")) {
//	         response.setContentType("application/json;charset=UTF-8");
//	         String json = new MarshallingUtils().marshalling(pagingVO);
//
//	         try (PrintWriter out = response.getWriter();) {
//	            out.println(json);
//	            return null;
//	            
//	         }

//	      } else {
	    	  return "buyer/buyer";
	      
	   }
}