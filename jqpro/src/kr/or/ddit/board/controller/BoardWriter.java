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
 * Servlet implementation class BoardWriter
 */
@WebServlet("/BoardWriter")
public class BoardWriter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0924
		//ajax 클라이언트 부분에서 불러온거 
		request.setCharacterEncoding("UTF-8");
		BoardVO vo =new BoardVO();
		
		try {
			BeanUtils.populate(vo, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		
		vo.setWip(request.getRemoteAddr()); // ajax를 보낸사람의 ip를 얻어오는거. getRemoteAddr : 멀리있는 wip를 따로 추가를 한다. 
		//1. 
		IBoardService service= BoardServiceImpl.getInstance();
		
		//2.메소드 호출 
		int seq = service.insertBoard(vo);
		
		//3. 저장하기 
		request.setAttribute("seq", seq);
		
		//4. jsp로 보내기 
		RequestDispatcher disp = request.getRequestDispatcher("board/write.jsp");
		disp.forward(request, response);
		
		
		//
		
	}

}
