package kr.or.ddit.notice.controller;

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
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;

@Controller
public class NoticeUpdate {
	@Inject INoticeService service;
	
	@RequestMapping("/notice/noticeUpdate.do")
	public String updateNoticeForm(
			@RequestParam(required=true) int what,
			Model model
			) {
		NoticeVO notice = service.retrieveNotice(what);
		model.addAttribute("notice",notice);
		return "notice/noticeUpdateForm";
	}
	@RequestMapping(value="/notice/noticeUpdate.do", method=RequestMethod.POST)
	public String updateNotice(
			@Valid @ModelAttribute("notice") NoticeVO notice, Errors errors,
			RedirectAttributes redirect,
			Model model
			) {
		boolean valid = !errors.hasErrors();
		String viewName = null;
		String message = null;
		model.addAttribute("message", message);
		if (valid) {
			ServiceResult result = service.modifyNotice(notice);
			switch (result) {
			case OK:
				message = "공지사항 수정 성공";
				redirect.addFlashAttribute("message", message);
				viewName = "redirect:/notice/noticeView.do?noticeNo="+notice.getNoticeNo();
				break;
			default:
				message = "서버 오류";
				viewName = "notice/noticeForm";
				break;
			}
		} else {
			viewName = "notice/noticeForm";
		}
		return viewName;
	}
	
	@RequestMapping("/notice/noticeDelete.do")
	public String deleteNotice(
			@RequestParam(required=true) int what,
			RedirectAttributes redirect,
			Model model
			) {
		String viewName = null;
		String message = null;
		model.addAttribute("message", message);
			ServiceResult result = service.removeNotice(what);
			switch (result) {
			case OK:
				message = "공지사항 삭제 성공";
				redirect.addFlashAttribute("message", message);
				viewName = "redirect:/notice/noticeList.do";
				break;
			default:
				message = "서버 오류";
				viewName = "notice/noticeList";
				break;
			}
		return viewName;
	}
}
