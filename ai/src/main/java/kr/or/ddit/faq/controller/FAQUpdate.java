package kr.or.ddit.faq.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.faq.service.IFaqService;
import kr.or.ddit.vo.FaqVO;

/**
 * @author 최서희
 * @since 2019. 11. 20.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * -------------   --------    ----------------------
 * 2019. 11. 20.     최서희 	         최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class FAQUpdate {
	@Inject IFaqService service;
	
	@RequestMapping(value="/faq/faqUpdate.do")
	public String updateFaqForm(
			@RequestParam int faqNo,
			Model model
			) {
		FaqVO faqVO = service.retrieveFaq(faqNo);
		model.addAttribute("faq", faqVO);
		return "faq/faqUpdateForm";
	}
	

	@RequestMapping(value="/faq/faqUpdate.do", method=RequestMethod.POST)
	public String updateFaq(
			@Valid @ModelAttribute("faq") FaqVO faq, Errors errors,
			RedirectAttributes redirect,
			Model model
			) {
		boolean valid = !errors.hasErrors();
		String viewName = null;
		String message = null;
		model.addAttribute("message", message);
		if (valid) {
			ServiceResult result = service.modifyFaq(faq);
			switch (result) {
			case OK:
				message = "FAQ 수정 성공";
				redirect.addFlashAttribute("message", message);
				viewName = "redirect:/faq/faqList.do";
				break;
			default:
				message = "서버 오류";
				viewName = "faq/faqList.do";
				break;
			}
		} else {
			viewName = "faq/faqList.do";
		}
		return viewName;
	}
	
	@RequestMapping("/faq/faqDelete.do")
	public String deleteFaq(
			@RequestParam(required=true) int faqNo,
			RedirectAttributes redirect,
			Model model
			) {
		String viewName = null;
		String message = null;
		model.addAttribute("message", message);
			ServiceResult result = service.removeFaq(faqNo);
			switch (result) {
			case OK:
				message = "FAQ 삭제 성공";
				redirect.addFlashAttribute("message", message);
				viewName = "redirect:/faq/faqList.do";
				break;
			default:
				message = "서버 오류";
				viewName = "faq/faqList";
				break;
			}
		return viewName;
		
	}
	
	@RequestMapping(value="/faq/faqUpdateHit.do", method=RequestMethod.POST, 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public FaqVO updateFaqHit(
			@RequestParam(required=true) int faqNo,
			Model model
			) {
		int hit = 0;
		ServiceResult result = service.modifyFaqHit(faqNo);
		switch (result) {
		case OK :
			FaqVO faq = service.retrieveFaq(faqNo);
			model.addAttribute("faq", faq);
			break;
		default:
			break;
		}
		
		return (FaqVO) model.asMap().get("faq");
	}
}
