package kr.or.ddit.dailysupplies.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.advertise.service.IAdvertiseService;
import kr.or.ddit.dailysupplies.service.IDailySupplyService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.AdattatchVO;
import kr.or.ddit.vo.AdhitVO;
import kr.or.ddit.vo.AdvertiseVO;
import kr.or.ddit.vo.BioCheProVO;
import kr.or.ddit.vo.PagingInfoVO;

/**
 * @author 박주연
 * @since 2019. 11. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 19.        박주연		 최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/dailysupply")
public class DailySupplyRetrieveController {
	@Inject
	IDailySupplyService service;
	
	@Inject
	IAdvertiseService adService;
	
	@Inject
	WebApplicationContext container;
	ServletContext application;

	@PostConstruct
	public void init() {
		application = container.getServletContext();
	}
	
	
	public static String URL;
	public static String POSITION;
	public static String ADIMG;
	public static int ADID;
	
	//검색&리스트 페이징 처리 
	@RequestMapping(value="dsList.do") // UI 동기, data 비동기
	public String list(
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage,
			Model model,
			@ModelAttribute("advertise") AdvertiseVO advertiseVO
//			@ModelAttribute("adhit") AdhitVO adhitVO,
//			@ModelAttribute("adattatch") AdattatchVO adattatchVO
			){
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);
		PagingInfoVO<BioCheProVO> pagingVO = 
					new PagingInfoVO<>(10, 5);
		BioCheProVO searchVO = new BioCheProVO();
		pagingVO.setSearchVO(searchVO);
		pagingVO.setSearchMap(searchMap);
		int totalRecord = service.retrieveDSCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<BioCheProVO> list = service.retrieveDSList(pagingVO);
		pagingVO.setDataList(list);
		model.addAttribute("pagingVO", pagingVO); //기존 리스트 페이징 
		
		//광고 이미지 부분 
		AdvertiseVO adimgappend = adService.retrieveAdImage();
		
		if (StringUtils.isNotBlank(advertiseVO.getAdLink())) {
//			advertiseVO.setAdhit(adhitVO);
			model.addAttribute("advertiseVO", advertiseVO); //여기에 adLink, 위치 
			model.addAttribute("adimgappend",adimgappend); //여기에 광고 이미지
//			model.addAttribute("adattatchVO", adattatchVO);
		}
		return "supplydaily/supplydaliyList";
	}
	
	//페이징처리 UI
	@RequestMapping(value="dsList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingInfoVO listForAjax(
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, Model model,
			@ModelAttribute("advertise") AdvertiseVO advertiseVO
//			@ModelAttribute("adhit") AdhitVO adhitVO,
//			@ModelAttribute("adattatch") AdattatchVO adattatchVO
			){
		list(searchType, searchWord, currentPage,model,advertiseVO);
		return (PagingInfoVO<BioCheProVO>) model.asMap().get("pagingVO");
	}
	
	//상세보기 
	@RequestMapping("dsView.do")
	public String dsView(
			 @RequestParam(required=true) String dsNo ,
			 Model model	) {
		BioCheProVO dsprod = service.retrieveDS(new BioCheProVO(dsNo));
		model.addAttribute("dsprod", dsprod);
		return "supplydaily/supplydaliyView";
	}
	
	
	@RequestMapping(value="adUpdate.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ServiceResult adHitCount(@RequestParam(required=true) int adId) {
		ServiceResult cnt = adService.updateAdHit(adId);
		return cnt;
	}
	
//	
//	@RequestMapping(value="dsadImage.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE, method=RequestMethod.POST)
//	@ResponseBody
//	public ModelAndView test(@ModelAttribute("advertise") AdvertiseVO advertiseVO,
//			@ModelAttribute("adhit") AdhitVO adhitVO,
//			@ModelAttribute("adattatch") AdattatchVO adattatchVO
//			) {
//		advertiseVO.setAdhit(adhitVO);
//		ModelAndView mav = new ModelAndView();
//		System.err.println(adattatchVO.getAdattSavename());
//		mav.addObject("advertiseVO", advertiseVO);
//		mav.addObject("adattatchVO", adattatchVO);
//		mav.setViewName("supplydaily/supplydaliyList");
//		return mav;
////		
//	
//	}
	
	
}
