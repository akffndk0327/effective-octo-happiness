package kr.or.ddit.member.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

/**
 * @author 허민지
 * @since 2019. 11. 8.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 8.   허민지       		최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/member")
public class CheckController{
	
	@Inject
	IMemberService service;
	@RequestMapping(value="idCheck.do", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public MemberVO checkId(
			@RequestParam(required=false) String memId,
			Model model
			) {
		MemberVO member = new MemberVO();
		if(memId != "") {
			member = service.idCheck(memId);
		}else {
			member.setMemId("");
		}
		
		return member;
	}
	
	@RequestMapping(value="mailCheck.do", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public MemberVO checkMail(
			@RequestParam(required=false) String memMail,
			Model model
			) {
		MemberVO member = new MemberVO();
		if(memMail !="") {
			member = service.mailCheck(memMail);
		}else {
			member.setMemMail("");
		}
		return member;
	}
	
}
