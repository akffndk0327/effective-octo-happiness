package kr.or.ddit.mypage.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.allergy.service.IAllergyService;
import kr.or.ddit.mypage.dao.IComReportDAO;
import kr.or.ddit.mypage.service.IAdminMypageService;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.search.service.ISearchService;
import kr.or.ddit.vo.AllergyVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.SearchVO;


/**
 * @author 허민지
 * @since 2019. 11. 6.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                    수정자               수정내용
 * --------        --------    ----------------------
 * 2019. 11. 6.     허민지            최초작성
 * 2019. 11. 11.	최서희		총 매출현황 그래프
 * 2019. 11. 27.    허민지		접속 브라우저 그래프 추가
 * 2019. 12. 03.    이진희          인기검색어 테이블 추가
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */

@Controller
public class AdminMyPageController {
	@Inject IComReportDAO service;
	
	@Inject
	IAdminMypageService adminService;
	
	@Inject
	ISearchService searchService;
	
	@Inject
	IAllergyService allergyService;

	@RequestMapping("/mypage/admin")
	public String selectAdminMypage(Model model) {
			String n = null;
			int cnt1 = service.selectJan(n);
			int cnt2 = service.selectFeb(n);
			int cnt3 = service.selectMar(n);
			int cnt4 = service.selectApr(n);
			int cnt5 = service.selectMay(n);
			int cnt6 = service.selectJun(n);
			int cnt7 = service.selectJul(n);
			int cnt8 = service.selectAug(n);
			int cnt9 = service.selectSep(n);
			int cnt10 = service.selectOct(n);
			int cnt11 = service.selectNov(n);
			int cnt12 = service.selectDec(n);
			
			model.addAttribute("jan", cnt1);
			model.addAttribute("feb", cnt2);
			model.addAttribute("mar", cnt3);
			model.addAttribute("apr", cnt4);
			model.addAttribute("may", cnt5);
			model.addAttribute("jun", cnt6);
			model.addAttribute("jul", cnt7);
			model.addAttribute("aug", cnt8);
			model.addAttribute("sep", cnt9);
			model.addAttribute("oct", cnt10);
			model.addAttribute("nov", cnt11);
			model.addAttribute("dec", cnt12);
			
			
			//browser
			int cnt13 = adminService.selectChorme();
			int cnt14 = adminService.selectFireFox();
			int cnt15 = adminService.selectEx();
			int cnt16 = adminService.selectSafari();
			int cnt17 = adminService.selectOthers();
			
			
			int cnt18 = adminService.selectWindows();
			int cnt19 = adminService.selectLinux();
			int cnt20 = adminService.selectAndroid();
			int cnt21 = adminService.selectIOS();
			int cnt22 = adminService.selectEtc();
			
			model.addAttribute("Chrome", cnt13);
			model.addAttribute("FireFox", cnt14);
			model.addAttribute("Ex", cnt15);
			model.addAttribute("Safari", cnt16);
			model.addAttribute("Others", cnt17);
			
			model.addAttribute("Windows", cnt18);
			model.addAttribute("Linux", cnt19);
			model.addAttribute("Android", cnt20);
			model.addAttribute("IOS", cnt21);
			model.addAttribute("Etc", cnt22);
			
		return "adminMypage/adminPage";
	}
}
