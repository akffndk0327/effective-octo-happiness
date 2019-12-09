package kr.or.ddit.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.order.service.IOrderService;
import kr.or.ddit.vo.Order2VO;
import kr.or.ddit.vo.PagingInfoVO;

@Controller
public class OrderRetrieve {
	@Inject IOrderService service;
	//주문 리스트
	@RequestMapping("/order/orderList.do")
	public String selectOrderList(
			@RequestParam(required = false) String searchType,
			@RequestParam(required = false) String searchWord,
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage, 
			Model model,
			Authentication user
			) {
		String loginId = user.getName();
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);
		PagingInfoVO<Order2VO> pagingVO = new PagingInfoVO<>(6, 5);
		Order2VO searchVO = new Order2VO();
		pagingVO.setSearchVO(searchVO);
		pagingVO.setSearchMap(searchMap);
		int totalRecord = service.retrieveOrderCount(loginId);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setPk(loginId);
		List<Order2VO> list = service.retrieveOrderList(pagingVO);
		pagingVO.setDataList(list);
		model.addAttribute("pagingVO", pagingVO);
		return "order/orderList";
	}
	//기업회원주문 리스트
	@RequestMapping("/order/orderEmpList.do")
	public String selectOrderEmpList(
			@RequestParam(required = false) String searchType,
			@RequestParam(required = false) String searchWord,
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage, 
			Model model,
			Authentication user
			) {
		String loginId = user.getName();
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);
		PagingInfoVO<Order2VO> pagingVO = new PagingInfoVO<>(10, 5);
		Order2VO searchVO = new Order2VO();
		pagingVO.setSearchVO(searchVO);
		pagingVO.setSearchMap(searchMap);
		pagingVO.setPk(loginId);
		int totalRecord = service.retrieveOrderEmpCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setPk(loginId);
		List<Order2VO> list = service.retrieveOrderEmpList(pagingVO);
		pagingVO.setDataList(list);
		model.addAttribute("pagingVO", pagingVO);
		return "order/orderEmpList";
	}

	
	//주문 상세조회
	@RequestMapping("/order/orderView.do")
	public String selectOrder(
			@RequestParam(required=true) String orderId,
			Model model
			) {
		Order2VO orderVO = service.retrieveOrder(orderId);
		model.addAttribute("orderVO", orderVO);
		
		return "order/orderView";
	}
	
	//주문 완료 페이지
	@RequestMapping("/order/orderResultView.do")
	public String selectOrderResult() {
		return "order/orderResult";
	}
	
}
