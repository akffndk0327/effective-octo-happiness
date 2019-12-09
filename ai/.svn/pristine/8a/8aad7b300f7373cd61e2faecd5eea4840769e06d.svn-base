package kr.or.ddit.allergy.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.allergy.service.IAllergyService;
import kr.or.ddit.vo.AllergyVO;
import kr.or.ddit.vo.BioCheProVO;
import kr.or.ddit.vo.AllergyVO;
import kr.or.ddit.vo.PagingInfoVO;

@Controller
@RequestMapping("/allergy")
public class AllergyRetriveController {
	@Inject
	IAllergyService service;
	
	@Inject
	WebApplicationContext container;
	ServletContext application;
	
	@Inject
	IAllergyService allergyService;
	
	@PostConstruct
	public void init() {
		application = container.getServletContext();
	}
	
	@RequestMapping("allergyList.do") // UI 동기, data 비동기
	public String alList(
			@RequestParam(required=false) String searchType, //전체, 제품명, 제조업자
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, Model model
			){
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);
		
		PagingInfoVO<AllergyVO> pagingVO = 
					new PagingInfoVO<AllergyVO>(10, 5);
		AllergyVO searchVO = new AllergyVO();
		pagingVO.setSearchVO(searchVO);
		pagingVO.setSearchMap(searchMap);
		int totalRecord = service.retrieveAllergyCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<AllergyVO> list = service.retrieveAllergyList(pagingVO);
		pagingVO.setDataList(list);
		
		model.addAttribute("pagingVO", pagingVO);
		
		List<AllergyVO> allList = allergyService.selectList();
		
		Collections.sort(allList);
		
		model.addAttribute("allList", allList);
		
		
		return "allergy/allergyList";
	}
	
	//페이징처리 UI
	@RequestMapping(value="allergyList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingInfoVO listForAjax(
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, Model model
			){
		alList(searchType, searchWord, currentPage, model);
		return (PagingInfoVO<BioCheProVO>) model.asMap().get("pagingVO");
	}
	
	
	@RequestMapping("allergyView.do")
	public String alView(
			@RequestParam(required=true) String what, Model model ){
		
		AllergyVO allergy = service.retrieveAllergy(new AllergyVO(what));
		model.addAttribute("allergy", allergy);
		
		return "allergy/allergyView";
	}
	
}
