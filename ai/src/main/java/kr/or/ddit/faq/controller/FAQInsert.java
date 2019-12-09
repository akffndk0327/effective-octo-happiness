package kr.or.ddit.faq.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.faq.service.IFaqService;
import kr.or.ddit.vo.FaqVO;

@Controller
@RequestMapping("/faq/faqInsert.do")
public class FAQInsert {
	@Inject IFaqService service;
	
	@RequestMapping()
	public String InsertFaqForm() {
		return "faq/faqForm";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String InsertFaq(
			@Valid @ModelAttribute("faq") FaqVO faq, Errors errors,
			RedirectAttributes redirect,
			Model model
			) {
		boolean valid = !errors.hasErrors();
		String viewName = null;
		String message = null;
		if (valid) {
			ServiceResult result = service.createFaq(faq);
			switch (result) {
			case FAILED:
				message = "서버 오류";
				model.addAttribute("message", message);
				viewName = "faq/faqForm";
				break;
			default:
				message = "FAQ 글 게시 성공";
				redirect.addFlashAttribute("message", message);
				viewName = "redirect:/faq/faqList.do";
				break;
			}
		} else {
			viewName = "faq/faqForm";
		}
		return viewName;
	}
}
