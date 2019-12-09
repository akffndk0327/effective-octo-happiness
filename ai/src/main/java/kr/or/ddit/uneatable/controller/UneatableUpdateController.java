package kr.or.ddit.uneatable.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/uneatable/uneatableUpdate.do")
public class UneatableUpdateController {
	
	@Inject
	IUneatableService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(
			@RequestParam(required=true) int uneatId,Model model
			) {
		
		UneatableVO uneat = new UneatableVO();
		uneat.setUneatId(uneatId);
		
		UneatableVO uneatable = service.selectUneatable(uneat);
		model.addAttribute("uneatable", uneatable);
		return "uneatable/uneatableForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String updateUneatable(@Valid@ModelAttribute("uneatable") UneatableVO uneatable, Errors errors, 
			Model model,RedirectAttributes redirect) {
		
		boolean valid = !errors.hasErrors();
		String viewName = null;
		String message = null;
		if(valid) {
			ServiceResult result = service.updateUneatable(uneatable);
			switch (result) {
			case OK:
				message = "수정성공";
				viewName = "redirect:/uneatable/uneatableView.do?uneatId=" + uneatable.getUneatId();
				redirect.addFlashAttribute("success", message);
				break;

			default:
				message="서버오류";
				viewName = "uneatable/uneatableForm";
				model.addAttribute("fail", message);
				break;
			}
		}else {
			viewName = "uneatable/uneatableForm";
		}
		return viewName;
	}
	
	

}
