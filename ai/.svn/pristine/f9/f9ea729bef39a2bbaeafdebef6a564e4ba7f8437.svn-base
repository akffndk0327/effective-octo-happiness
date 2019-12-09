package kr.or.ddit.addrChart.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.addrChart.service.IAddrChartService;
import kr.or.ddit.allergy.service.IAllergyService;
import kr.or.ddit.mypage.service.IAdminMypageService;
import kr.or.ddit.search.service.ISearchService;
import kr.or.ddit.vo.AllergyVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SearchVO;

/**
 * @author 이진희
 * @since 2019. 11. 9.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 9.      이진희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class AddrChartController {
	
	@Inject
	IAddrChartService service;
	
	@Inject
	IAdminMypageService adminService;
	
	@Inject
	ISearchService searchService;
	
	@Inject
	IAllergyService allergyService;
	
	@RequestMapping("/")
	public String count(Model model) {
		//지역별 천식  환자 수 
		int cnt1 = service.selectSeoulAsthma();
		int cnt2 = service.selectBusanAsthma();
		int cnt3 = service.selectDaeguAsthma();
		int cnt4 = service.selectGwangjuAsthma();
		int cnt5 = service.selectDaejeonAsthma();
		int cnt6 = service.selectUlsanAsthma();
		int cnt7 = service.selectChungbukAsthma();
		int cnt8 = service.selectJeonnamAsthma();
		int cnt9 = service.selectJejuAsthma();
		
		model.addAttribute("seoulAsthma", cnt1);
		model.addAttribute("busanAsthma", cnt2);
		model.addAttribute("daeguAsthma", cnt3);
		model.addAttribute("gwangjuAsthma", cnt4);
		model.addAttribute("daejeonAsthma", cnt5);
		model.addAttribute("ulsanAsthma", cnt6);
		model.addAttribute("chungbukAsthma", cnt7);
		model.addAttribute("jeonnamAsthma", cnt8);
		model.addAttribute("jejuAsthma", cnt9);
		
		//지역별 아토피 환자수 
		int cnt10 = service.selectSeoulAtopy();
		int cnt11 = service.selectBusanAtopy();
		int cnt12 = service.selectDaeguAtopy();
		int cnt13 = service.selectGwangjuAtopy();
		int cnt14 = service.selectDaeguAtopy();
		int cnt15 = service.selectUlsanAtopy();
		int cnt16 = service.selectChungbukAtopy();
		int cnt17 = service.selectJeonnamAtopy();
		int cnt18 = service.selectJejuAtopy();
		
		model.addAttribute("seoulAtopy", cnt10);
		model.addAttribute("busanAtopy", cnt11);
		model.addAttribute("daeguAtopy", cnt12);
		model.addAttribute("gwangjuAtopy", cnt13);
		model.addAttribute("daejeonAtopy", cnt14);
		model.addAttribute("ulsanAtopy", cnt15);
		model.addAttribute("chungbukAtopy", cnt16);
		model.addAttribute("jeonnamAtopy", cnt17);
		model.addAttribute("jejuAtopy", cnt18);
		
		//지역별 아토피 환자수 
		int cnt19 = service.selectSeoulRhinitis();
		int cnt20 = service.selectBusanRhinitis();
		int cnt21 = service.selectDaeguRhinitis();
		int cnt22 = service.selectGwangjuRhinitis();
		int cnt23 = service.selectDaeguRhinitis();
		int cnt24 = service.selectUlsanRhinitis();
		int cnt25 = service.selectChungbukRhinitis();
		int cnt26 = service.selectJeonnamRhinitis();
		int cnt27 = service.selectJejuRhinitis();
		
		model.addAttribute("seoulRhinitis", cnt19);
		model.addAttribute("busanRhinitis", cnt20);
		model.addAttribute("daeguRhinitis", cnt21);
		model.addAttribute("gwangjuRhinitis", cnt22);
		model.addAttribute("daejeonRhinitis", cnt23);
		model.addAttribute("ulsanRhinitis", cnt24);
		model.addAttribute("chungbukRhinitis", cnt25);
		model.addAttribute("jeonnamRhinitis", cnt26);
		model.addAttribute("jejuRhinitis", cnt27);
		
		//10대 아토피 환자의수 
		int cnt28 = service.selectOneAtopy();
		
		//10대 천식 환자의 수 
		int cnt29 = service.selectOneAsthma();
		
		//10대 비염 환자의수
		int cnt30 = service.selectOneRhinitis();
		
		model.addAttribute("oneAtopy", cnt28);
		model.addAttribute("oneAsthma", cnt29);
		model.addAttribute("oneRhinitis", cnt30);
		
		//20대 아토피 환자의 수 
		int cnt31 = service.selectTwoAtopy();
		
		//20대 천식환자의수 
		int cnt32 = service.selectTwoAsthma();
		
		//20대 비염환자의수 
		int cnt33 = service.selectTwoRhinitis();
		
		model.addAttribute("twoAtopy", cnt31);
		model.addAttribute("twoAsthma", cnt32);
		model.addAttribute("twoRhinitis", cnt33);
		
		//30대 아토피 환자의수 
		int cnt34 = service.selectThrAtopy();
		
		//30대 천식환자의수 
		int cnt35 = service.selectThrAsthma();
		
		//30대 비염환자의수 
		int cnt36 = service.selectThrRhinitis();
		
		model.addAttribute("thrAtopy", cnt34);
		model.addAttribute("thrAsthma", cnt35);
		model.addAttribute("thrRhinitis", cnt36);
		
		//40대 아토피 환자의 수 
		int cnt37 = service.selectFourAtopy();
		
		//40대 천식환자의수 
		int cnt38 = service.selectFourAsthma();
		
		//40대 비염환자의수 
		int cnt39 = service.selectFourRhinitis();
		
		model.addAttribute("fourAtopy", cnt37);
		model.addAttribute("fourAsthma", cnt38);
		model.addAttribute("fourRhinitis", cnt39);
		
		//50대 아토피 환자의수 
		int cnt40 = service.selectFiveAtopy();
		
		//50대 천식환자의 수 
		int cnt41 = service.selectFiveAsthma();
		
		//50대 비염환자의수 
		int cnt42 = service.selectFiveRhinitis();
		
		model.addAttribute("fiveAtopy", cnt40);
		model.addAttribute("fiveAsthma", cnt41);
		model.addAttribute("fiveRhinitis", cnt42);
		
		//여자 아토피환자의수 
		int cnt43 = service.selectWoAtopy();
		
		//여자 천식환자수
		int cnt44 = service.selectWoAsthma();
		
		//여자 비염환자수 
		int cnt45 = service.selectWoRhinitis();
		
		//여자 조개류환자수46
		int cnt46 = service.selectWoShell();
		
		//여자 난류환자수47
		int cnt47 = service.selectWoEgg();
		
		//여자 우유환자수48
		int cnt48 = service.selectWoMilk();
		
		//여자 호두환자수49
		int cnt49 = service.selectWoNut();
		
		
		model.addAttribute("woAtopy", cnt43);
		model.addAttribute("woAsthma", cnt44);
		model.addAttribute("woRhinitis", cnt45);
		model.addAttribute("woShell", cnt46);
		model.addAttribute("woEgg", cnt47);
		model.addAttribute("woMilk", cnt48);
		model.addAttribute("woNut", cnt49);
		
		//남자 아토피환자의수 
		int cnt50 = service.selectManAtopy();
		
		//남자 천식환자수
		int cnt51 = service.selectManAsthma();
		
		//남자 비염환자수 
		int cnt52 = service.selectManRhinitis();
		
		//여자 조개류환자수46
		int cnt53 = service.selectManShell();
		
		//여자 난류환자수47
		int cnt54 = service.selectManEgg();
		
		//여자 우유환자수48
		int cnt55 = service.selectManMilk();
		
		//여자 호두환자수49
		int cnt56 = service.selectManNut();
		
		 
		model.addAttribute("manAtopy", cnt50);
		model.addAttribute("manAsthma", cnt51);
		model.addAttribute("manRhinitis", cnt52);
		model.addAttribute("manShell", cnt53);
		model.addAttribute("manEgg", cnt54);
		model.addAttribute("manMilk", cnt55);
		model.addAttribute("manNut", cnt56);
		
		
//		List<SearchVO> list = searchService.selectKeyWord();
//		List<SearchVO> result = new ArrayList();
//		
//		for(SearchVO temp : list) {
//		 result = searchService.selectcnt(temp.getSearchName());
//		}
//		
//		Collections.sort(result);
//		
//		model.addAttribute("result", result);
		

		return "index";
	}

}
