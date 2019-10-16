package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/memberDelete.do")
//@CommandHandler
@Controller
public class MemberDeleteServlet {
//	@Inject
	@Resource(type=IMemberService.class, name="memberService") //방법2
	IMemberService service ;
	
	@RequestMapping(value = "/member/memberDelete.do", method=RequestMethod.POST)
	public String doPost(HttpSession session, //req,resp대신 session받는거  
				@RequestParam(required=true) String password, HttpServletResponse resp) throws IOException {
		 MemberVO authMember = (MemberVO) session.getAttribute("authMember");

		if(session.isNew()||authMember==null) {
	          resp.sendError(400);
	          return null;
	       }
		MemberVO member = new MemberVO(authMember.getMem_id(),password); //authMember.getMem_id() : 로그인된 유저
		ServiceResult result = service. removeMember(member); // authmember 넘기면 틀려도 맞다고 처리해버림..
		String viewName = "redirect:"; //이동 주소 변수
		String message = null;
		switch (result) {
		case INVALIDPASSWORD:
			viewName= "redirect:/mypage";
			message = "비번오류" ; 
			session.setAttribute("message", message);
			break;
		case FAILED : 
			viewName= "redirect:/mypage";
			message ="서버오류";
			session.setAttribute("message", message);
			break;
		default:
			viewName= "redirect:/";
			session.invalidate();
			break;
		}
		return "redirect:/";
	}
}
