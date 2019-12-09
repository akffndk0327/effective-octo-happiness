package kr.or.ddit.stopsellingfood.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.stopsellingfood.service.IStopSellingService;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.StopSellingFoodVO;
import kr.or.ddit.vo.UneatableVO;
/**
 * 
 * @author 이진희
 * @since 2019. 11. 7.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 7.      이진희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/stopSellingFood")
public class StopSellingFoodViewController {

	@Inject
	IStopSellingService service;
	
	@RequestMapping("stopSellingList.do")
	public String view(
			@RequestParam(required = false) String searchType,
			@RequestParam(required = false) String searchWord,
			@RequestParam(name ="page", required = false, defaultValue = "1") int currentPage, Model model	
	) {
		
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);
		
		PagingInfoVO<StopSellingFoodVO> pagingVO = new PagingInfoVO<>(10,5);
		StopSellingFoodVO searchVO = new StopSellingFoodVO();
		pagingVO.setSearchVO(searchVO);
		pagingVO.setSearchMap(searchMap);
		
		int totalRecord = service.selectStopSellingCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<StopSellingFoodVO> list = service.selectStopSellingList(pagingVO);
		pagingVO.setDataList(list);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "stopSellingFood/stopSellingList";
	}	
	
	@RequestMapping(value="stopSellingList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingInfoVO<StopSellingFoodVO> listForAjax(
			@RequestParam(required = false) String searchType,
			@RequestParam(required = false) String searchWord,
			@RequestParam(name ="page", required = false, defaultValue = "1") int currentPage, Model model
		){
		view(searchType,searchWord,currentPage,model);
		return (PagingInfoVO<StopSellingFoodVO>) model.asMap().get("pagingVO");
	}
	
	@RequestMapping("stopSellingView.do")
	public String stopSellingView(@RequestParam(required=true) int stopsellId,Model model) {
		StopSellingFoodVO stop = new StopSellingFoodVO();
		stop.setStopsellId(stopsellId);
		
		StopSellingFoodVO stopSell = service.selectStopSelling(stop);
		model.addAttribute("stopSell", stopSell);
		
		return "stopSellingFood/stopSellingView";
	}
}
