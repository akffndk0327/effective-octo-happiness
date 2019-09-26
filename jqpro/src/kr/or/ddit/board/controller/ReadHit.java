package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

/**
 * Servlet implementation class ReadHit
 */
@WebServlet("/ReadHit")
public class ReadHit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int seq = Integer.parseInt(request.getParameter("bonum")); //ajax에서 dataType 쓴 name
		IBoardService service = BoardServiceImpl.getInstance();
		
		//메소드 호출
		int hit = service.updateHit(seq);
		
		//결과값 거장
		request.setAttribute("res", hit);
		//jsp로 넘기기 
		RequestDispatcher disp = request.getRequestDispatcher("board/update.jsp");
		disp.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
