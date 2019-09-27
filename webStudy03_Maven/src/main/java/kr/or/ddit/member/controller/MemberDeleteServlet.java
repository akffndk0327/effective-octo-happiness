package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberDelete.do")
@CommandHandler
public class MemberDeleteServlet {
	IMemberService service = new MemberServiceImpl();
	@URIMapping(value = "/member/memberDelete.do", method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 누가 탈퇴? - 이미 로그인된 유저임.
		HttpSession session = req.getSession(); //세션 잡아 
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		String password = req.getParameter("password");
		//2. 인증받은걸로 탈퇴처리해야해 -> 검증 필요
		if(session.isNew() || authMember ==null ||StringUtils.isBlank(password)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		//3 비번 안넘어올때
		//검증 불가능할때 책임은 클라이언트 한테 잇어
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
		//4. 서비스호출 - 위에서 함.
		//5. 메세드 호출 
//		service.removeMember(member); 
		//6. 결과값이 serviceresult
		// 1) usernotfound, invalidpassword-정상비번입력위해 마이페이지로 이동 : redirect, 
		//faild, ok - 로그아웃 & 세션만료  월컴페이지 이동 ->redirection으로 로그아웃 페이지로  => 수정이랑 같아 
//		req.getSession().setAttribute("message", message);
	
//		resp.sendRedirect(req.getContextPath()+viewName); //redirect는 post 못써 !! 나갓다가 들어오면서 get으로 요청하닌까 ! 
		return "redirect:/";
	}
}
