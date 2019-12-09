package kr.or.ddit.uneatable.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import kr.or.ddit.uneatable.service.IUneatableService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.UneatableVO;

/**
 * @author 이진희
 * @since 2019. 11. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자       수정내용
 * 2019. 11. 7.      이진희       상세보기
 * --------     --------    ----------------------
 * 2019. 11. 5.      이진희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/uneatable")
public class UneatAbleViewController {

	@Inject
	IUneatableService service;

	@RequestMapping("uneatableList.do")
	public String view(@RequestParam(required = false) String searchType,
			@RequestParam(required = false) String searchWord,
			@RequestParam(name ="page", required = false, defaultValue = "1") int currentPage, Model model) {
		
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);
		
		PagingInfoVO<UneatableVO> pagingVO = new PagingInfoVO<UneatableVO>(10, 5);
		UneatableVO searchVO = new UneatableVO();
		pagingVO.setSearchVO(searchVO);
		pagingVO.setSearchMap(searchMap);
		
		int totalRecord = service.selectUneatableCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<UneatableVO> list = service.selectUneatableList(pagingVO);
		pagingVO.setDataList(list);
		
		model.addAttribute("pagingVO", pagingVO);

		return "uneatable/UneatableList";
	}
	
	@RequestMapping(value="uneatableList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingInfoVO<UneatableVO> listForAjax(
			@RequestParam(required = false) String searchType,
			@RequestParam(required = false) String searchWord,
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage, Model model) {
		view(searchType, searchWord, currentPage, model);
		return (PagingInfoVO<UneatableVO>) model.asMap().get("pagingVO");
		
	}
	
	@RequestMapping("uneatableView.do")
	public String uneatableView(@RequestParam(required=true) int uneatId,Model model) {
		UneatableVO uneat = new UneatableVO();
		uneat.setUneatId(uneatId);
		
		UneatableVO uneatAble = service.selectUneatable(uneat);
		model.addAttribute("uneatAble", uneatAble);
	
		return "uneatable/uneatableView";
	}
	
	

}
