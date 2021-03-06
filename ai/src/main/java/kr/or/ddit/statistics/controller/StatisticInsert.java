package kr.or.ddit.statistics.controller;

import java.io.UnsupportedEncodingException;

import javax.cache.expiry.CreatedExpiryPolicy;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enums.BrowserType;
import kr.or.ddit.enums.OsType;
import kr.or.ddit.statistics.service.IStatisticService;
import kr.or.ddit.utils.CookieUtil;
import kr.or.ddit.utils.CookieUtil.TextType;
import kr.or.ddit.vo.BrowserVO;

/**
 * 
 * @author 박슬기
 * @since 2019. 11. 21.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * 
 * --------     --------    ----------------------
 * 2019. 11. 21.      박슬기       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class StatisticInsert {
	@Inject
	IStatisticService service;
	
	
	@RequestMapping(value="static/staticInsert.do",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String insertLoginStatic(HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException {
		BrowserVO browserVO = new BrowserVO();
		String userAgent = req.getHeader("User-Agent");
		
		//접속 os
		OsType os = OsType.searchOS(userAgent);
		String osName = os.getName();
		
		
		//접속 browser
		BrowserType browser = BrowserType.searchBrowser(userAgent);
		String browserName = browser.getName();
		
		browserVO.setBroType(browserName);
		browserVO.setOsType(osName);
		
		
		// 쿠키 가져오기
		Cookie[] cookies = req.getCookies();
		boolean exist = false;
		
		//쿠키 유무 확인
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("check")) {
				exist = true;
			}
		}
		
		//쿠키 없을 때 쿠키 생성
		if (exist==false) {
			System.out.println("쿠키생성");
			Cookie imageCookie = CookieUtil.createCookie("check", "count",req.getContextPath(), TextType.PATH, -1);
			resp.addCookie(imageCookie);
			service.createLogin(browserVO);
		}
		return "index";
	}
}
