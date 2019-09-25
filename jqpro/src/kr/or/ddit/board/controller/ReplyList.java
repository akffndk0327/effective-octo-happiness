package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.ReplyVO;

/**
 * Servlet implementation class ReplyList
 */
@WebServlet("/ReplyList")
public class ReplyList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int bonum = Integer.parseInt(request.getParameter("bonum"));
		
		IBoardService service = BoardServiceImpl.getInstance();
		List<ReplyVO> list = service.replyList(bonum);
		request.setAttribute("list", list);
		
		RequestDispatcher disp = request.getRequestDispatcher("board/replyList.jsp");
		disp.forward(request, response);
	
	}

}
