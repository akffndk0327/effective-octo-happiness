package kr.or.ddit.mypage.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mypage.service.IMemMypageService;

@Controller
@RequestMapping("/memMypage")
public class MemberDeleteController {

	@Inject
	IMemMypageService service;

	@RequestMapping(value="memberDelete.do")
	public String memDelete(
			@RequestParam(required=true) String memId,
			HttpSession session
			
			) {
		String viewName = null;
		ServiceResult result = service.removeMember(memId);
		switch(result) {
		case FAILED:
			viewName="memMypge/member?memid="+memId;
			break;
		default :
			viewName = "redirect:/";
			session.invalidate();
			break;
		}
		return viewName;
	}
}
