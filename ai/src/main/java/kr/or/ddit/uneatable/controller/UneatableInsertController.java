package kr.or.ddit.uneatable.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.uneatable.service.IUneatableService;
import kr.or.ddit.vo.UneatableVO;

/**
 * @author 이진희
 * @since 2019. 11. 10.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 10.      이진희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/uneatable")
public class UneatableInsertController {

	@Inject
	IUneatableService service;

	@RequestMapping(value = "/uneatableInsert.do", method = RequestMethod.GET)
	public String view() {
		return "uneatable/uneatableForm";
	}

	@RequestMapping(value = "/uneatableInsert.do", method = RequestMethod.POST)
	public String createUneatable(@Valid@ModelAttribute("uneatable") UneatableVO uneatable, Errors errors, Model model,
			RedirectAttributes redirect) {

		boolean valid = !errors.hasErrors();
		String viewName = null;
		if (valid) {
			ServiceResult result = service.creatUneatable(uneatable);
			switch (result) {
			case OK:
				String msg = "추가성공";
				viewName = "redirect:/uneatable/uneatableView.do?uneatId=" + uneatable.getUneatId();
				redirect.addFlashAttribute("success", msg);
				break;

			default:
				viewName = "uneatable/uneatableForm";
				String msg1 = "서버오류";
				model.addAttribute("fail", msg1);
				break;
			}
		} else {
			viewName = "uneatable/uneatableForm";
		}
		return viewName;

	}

	@RequestMapping(value="insttNmList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public List<String> insttNmList(@RequestParam(name="insttNm2",required = false) String insttNm) {

		List<String> insttNmList = service.selectInsttNmList(insttNm);
		return insttNmList;
	}
	
	@RequestMapping(value="testNmList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE,method=RequestMethod.POST)
	@ResponseBody
	public List<String> testNmList(@RequestParam(name="testNm",required = false) String testNm) {

		List<String> testNmList = service.selectTestNmList(testNm);
		return testNmList;
	}

}
