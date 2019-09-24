 package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.MarshallingUtils;
import kr.or.ddit.vo.MemberVO;

@CommandHandler //마킹해놓은 => 핸들러맵퍼가 수집함.. Plain Old  Java Object (POJO) 오래된 클래스이다.
public class MemberListController { // 0924 서블릿 삭제, alt, shift r

	IMemberService service = MemberServiceImpl.getInstance();

//   @Override =>컴파일 대상
	@URIMapping(value = "/member/memberList.do", method = HttpMethod.GET)
	// doget -> memberList로 바꿔도 상관x
	public String memberList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 동기/비동기 구분을 해 응답데이터를 넘기기 위함
		String accept = req.getHeader("Accept");
		// 데이터 조회
		List<MemberVO> list = service.retrieveMemberList();

		if (accept.toLowerCase().contains("json")) { // 비동기
			resp.setContentType("application/json;charset=UTF-8");

			// json을 만들기위한 marshalling
			String json = new MarshallingUtils().marshalling(list);

			try (PrintWriter out = resp.getWriter();) {
				out.println(json);
			}
			return null; // 0924 d여기 처리해야돼!!

		} else {
		// 동기
		// 0924
//         String viewName = "/WEB-INF/views/member/memberList.jsp";
			String viewName = "member/memberList"; // -\=> 논리적인 viewName = logical~
			return viewName;
//         req.getRequestDispatcher(viewName).forward(req, resp);
		}

	}
}