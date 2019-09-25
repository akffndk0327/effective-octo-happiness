package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

/**
 * Servlet implementation class InsertMember
 */
@WebServlet("/InsertMember")
public class InsertMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//0 id 값 받는다.그런데모든 값을 가져와야하닌까 9개를 가져와야하는데
		//귀찮으닌까 태그의 name 을 vo의 변수명과 일치하게 만든다 
		//ex) <input type="" name="id"> -> name="mem_id">로 쓰기 
		
		//0. id값 받기 .
		MemberVO vo = new MemberVO();
		
		try {
			BeanUtils.populate(vo, request.getParameterMap()); 
			//request.getParameterMap() : 굳이 map으로 안만들고 이렇게 만들면 vo랑 이랑 일치하게 했으닌까 자동으로 map으로 생성해줌
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//1. 서비스 객체 얻기
		IMemberService service = MemberServiceImpl.getInstance();
		
		//2.메소드 호출
		String resId = service.insertMember(vo); //vo를 파라미터로 주면 resid를 받아옴.
		
		//3.결과값 request에 저장하기
		request.setAttribute("resId", resId); //setAttribute("name값",value값). 
		request.setAttribute("inputId", vo.getMem_id()); //가입 실패 시
		
		//4.jsp에 보내기
		RequestDispatcher disp = request.getRequestDispatcher("member/insert.jsp");
		disp.forward(request, response);
		
		
		
	}

}
