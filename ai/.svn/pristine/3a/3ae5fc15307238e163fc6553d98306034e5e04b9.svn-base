package kr.or.ddit.notice.controller;

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
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;
/**
 * @author 최서희
 * @since 2019. 11. 20.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * -------------   --------    ----------------------
 * 2019. 11. 20.     최서희             최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/notice/noticeInsert.do")
public class NoticeInsert {
	@Inject INoticeService service;

	@RequestMapping()
	public String insertNoticeForm() {
		return "notice/noticeForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String insertNotice(
			@Valid @ModelAttribute("notice") NoticeVO notice,
			Errors errors,
			RedirectAttributes redirectAttributes,
			Model model
			) {
		boolean valid = !errors.hasErrors();
		String viewName = null;
		String message = null;
		if (valid) {
			ServiceResult result = service.createNotice(notice);
			switch (result) {
			case FAILED:
				message = "서버 오류";
				model.addAttribute("message", message);
				viewName = "notice/noticeForm";
				break;
			default:
				message = "공지사항 글 게시 성공";
				redirectAttributes.addFlashAttribute("message", message);
				viewName = "redirect:/notice/noticeList.do";
				break;
			}
		} else {
			viewName = "notice/noticeForm";
		}
		return viewName;
	}
}
