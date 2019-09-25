package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

/**
 * Servlet implementation class MemerList
 */
@WebServlet("/MemberList")
public class MemerList extends HttpServlet {
	
    public MemerList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트에서 요청시 데이타를 전송 받아서 - 없음
		// 처리(CRUD)후 응답 할 데이터를 만든다. - 서비스객체 필요 
		// 데이터의 형태는 json형태의 Object배열. []로 시작함
	
		//service객체 싱글톤으로 얻어오기
		IMemberService service = MemberServiceImpl.getInstance(); //객체 얻어옴.
		//서비스메소드 호출 - 결과값 가져온다.
		List<MemberVO> list = service.selectAll();
		
		//list를 request에 저장
		//jsp로 forward시킨다.
		request.setAttribute("list", list); //"list"가 jsp로 감
		
		RequestDispatcher disp = 
				request.getRequestDispatcher("/0910/memberList.jsp");
		disp.forward(request, response);
		
	}

}
