package kr.or.ddit.board.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

/**
 * Servlet implementation class BoardUpdate
 */
@WebServlet("/BoardUpdate")
public class BoardUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		BoardVO vo = new BoardVO();
		try{
			BeanUtils.populate(vo, request.getParameterMap());
		}catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		IBoardService service = BoardServiceImpl.getInstance();
		int seq = service.updateBoard(vo);
		request.setAttribute("res", seq);
		RequestDispatcher disp = request.getRequestDispatcher("board/update.jsp");
		disp.forward(request, response);
				
		
	}

}
