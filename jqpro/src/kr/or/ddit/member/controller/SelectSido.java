package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.LSUB;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.ZipVO;

/**
 * Servlet implementation class SelectZipServlet
 */
@WebServlet("/SelectSido")
public class SelectSido extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//1. 서비스 연결하기
		IMemberService service = MemberServiceImpl.getInstance();
		//2메서드 호출하기
		List<ZipVO> list = service.selectSido();
		
		//3. 결과값 request에 저장하기
		request.setAttribute("list", list);
		//4. jsp로 이동하기
		RequestDispatcher disp = request.getRequestDispatcher("member/selectDong.jsp");
		disp.forward( request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
