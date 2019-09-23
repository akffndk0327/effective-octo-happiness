package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.utils.MarshallingUtils;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberView.do")
public class MemberViewServlet extends HttpServlet {
	IMemberService service = MemberServiceImpl.getInstance();
 @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 0923
		String who = req.getParameter("who");
		if (StringUtils.isBlank(who)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "누구를 조회하나요??");
			return;
		}
	    //정상적으로 넘어왔을때 
	    //서비스 불러오기 
	      String accept = req.getHeader("Accept");
	      
	      //service -> retr
	      MemberVO saved = service.retrieveMember(new MemberVO(who,null)); //데이터 조회
	      
	      req.setAttribute("member", saved);
	         
	         String viewName = "/WEB-INF/views/member/memberView.jsp"; //서버사이드 경로 ! -> 디스패치장식 
	         req.getRequestDispatcher(viewName).forward(req, resp);
	         
	      }
	 
}
