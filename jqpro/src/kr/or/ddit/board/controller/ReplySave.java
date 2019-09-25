package kr.or.ddit.board.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.ReplyVO;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Servlet implementation class ReplySave
 */
@WebServlet("/ReplySave")
public class ReplySave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ReplyVO vo = new ReplyVO();
		vo.setName(request.getParameter("name"));
		vo.setCont(request.getParameter("text"));
		vo.setBonum(Integer.parseInt(request.getParameter("idx")));
		
		IBoardService service = BoardServiceImpl.getInstance();
		
		int rpl = service.insertReply(vo);
		
		request.setAttribute("seq", rpl);
		
		RequestDispatcher disp = request.getRequestDispatcher("board/write.jsp");
		disp.forward(request, response);
	}

}
