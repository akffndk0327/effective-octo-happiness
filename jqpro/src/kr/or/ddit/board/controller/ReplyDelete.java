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
 * Servlet implementation class ReplyDelete
 */
@WebServlet("/ReplyDelete")
public class ReplyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int renum = Integer.parseInt(request.getParameter("renum"));
		
		IBoardService service = BoardServiceImpl.getInstance();
		int rpl = service.deleteReply(renum);
		
		request.setAttribute("res", rpl);
		
		RequestDispatcher disp = request.getRequestDispatcher("board/delete.jsp");
		disp.forward(request, response);
	}

}
