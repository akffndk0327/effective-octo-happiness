package kr.or.ddit.mypage.controller;


import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.mypage.dao.IComReportDAO;

/**
 * @author 허민지
 * @since 2019. 11. 6.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 6.     허민지       최초작성
 * 2019. 11. 11. 	최서희       기업 매출 현황 그래프
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */

@Controller
public class CompanyMyPageController {
	@Inject IComReportDAO service;
	
	@RequestMapping(value="/mypage/company")
	public String selectComMypage(
			Model model
			,Authentication customUser
			) {
		String n = customUser.getName();
		
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
		
		return "comMypage/comPage";
	}
	
	
}
