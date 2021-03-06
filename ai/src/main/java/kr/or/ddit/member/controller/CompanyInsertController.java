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
import kr.or.ddit.vo.ComInfoVO;

/**
 * @author 허민지
 * @since 2019. 11. 12.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 12.      허민지       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/company/companyInsert.do")
public class CompanyInsertController {

	@Inject
	IMemberService service;
	
	//회사번호를 받아서 회사 주소, 이름을 보내준다
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ComInfoVO doGet(
			@RequestParam String comNum,
			Model model) {
		ComInfoVO company = service.retrieveCompanyForSignUp(comNum);
		model.addAttribute("company", company);
		return company;
	}
	
	
}
