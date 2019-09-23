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
import kr.or.ddit.utils.MarshallingUtils;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberList.do")
public class MemberListServlet extends HttpServlet {

   IMemberService service = MemberServiceImpl.getInstance();
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //동기/비동기 구분을 해 응답데이터를 넘기기 위함
      String accept = req.getHeader("Accept");
      //데이터 조회
      List<MemberVO> list = service.retrieveMemberList();
      
      if(accept.toLowerCase().contains("json")) { //비동기
         resp.setContentType("application/json;charset=UTF-8");
         
         //json을 만들기위한 marshalling
         String json = new MarshallingUtils().marshalling(list);
         
         try(
            PrintWriter out = resp.getWriter();
         ){
            out.println(json);
         }
      
      }else {//동기
         String viewName = "/WEB-INF/views/member/memberList.jsp";
         req.getRequestDispatcher(viewName).forward(req, resp);
      }
         
      
      
   }
}