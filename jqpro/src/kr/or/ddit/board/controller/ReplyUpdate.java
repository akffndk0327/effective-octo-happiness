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
import kr.or.ddit.board.vo.ReplyVO;

/**
 * Servlet implementation class ReplyUpdate
 */
@WebServlet("/ReplyUpdate")
public class ReplyUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//0/ 
		ReplyVO vo = new ReplyVO();
		try {
			BeanUtils.populate(vo,request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//1.서비스객체 얻기
		IBoardService service = BoardServiceImpl.getInstance();
		
		//2.메서드 호출
		int rpl = service.updateReply(vo);
		
		//3.결과값 저장
		request.setAttribute("seq", rpl);
		
		//4.jsp로 보내기
		RequestDispatcher disp = request.getRequestDispatcher("board/write.jsp");
		disp.forward(request, response);
		
		
	}

}
