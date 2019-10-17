package kr.or.ddit.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/memberUpdate.do")
@Controller
public class MemberUpdateController {
	@Inject
	IMemberService service;

	@RequestMapping(value = "/member/memberUpdate.do", method = RequestMethod.POST)
	public String doPost(@Valid MemberVO member,
						Errors errors, RedirectAttributes redirectAttributes) {

		boolean valid = !errors.hasErrors();
		String viewName = "redirect:/mypage";
		String message = null;
		if (valid) {
			// 3. 통과
			// 1) 의존성...... 서비스 받기
			// 2) 메소드호출(ServiceResult modifyMember(member vo))
			// - userNotfoundexception -500 throw로 보내 안해도됨...
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				// - invalidpassword : redirection -> mypage -> jsp or controller
				message = "비번오류";
				break;
			case FAILED:
				// failed : redirection -> mypage
				message = "서버오류";
				break;

			default:
				// - ok : : redirection -> mypage
				message = "수정성공";
				break;
			}

			// 4.session scope에 메세지 전송하지
		} else {
			// 4. 불통 ex) 타입 잘 못왓을 때 / redirect ->mypage

		}
//		req.getSession().setAtt~
		redirectAttributes.addFlashAttribute("message", message);
		redirectAttributes.addFlashAttribute("errors", errors);
		// resp.sendRedirect(req.getContextPath() + "/mypage");
		return viewName;

	}

	
}
