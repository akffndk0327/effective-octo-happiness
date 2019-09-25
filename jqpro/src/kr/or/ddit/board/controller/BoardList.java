package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

/**
 * Servlet implementation class BoardList
 */
@WebServlet("/BoardList")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//(0.파라미터 얻기 - 없음)
		int cpage = Integer.parseInt(request.getParameter("page"));
		//1. 서비스 객체 얻기 
		IBoardService service = BoardServiceImpl.getInstance();
		//2.서비스 메소드 호출- 결과값 받기
//		List<BoardVO> list = service.selectBoard();
		int perlist = 5; //게시물 몇개
		int perpage = 2; // [1][2][3]
		//전체 글 개수 구하기 =>서비스,다오에 메소드 만들기 
		int totalCount = service.countList();
		//전체 페이지 수 구하기 => 20/5
		int totalPage = (int)(Math.ceil(totalCount/(double)perlist));
//		if(totalCount % perlist >=1) totalPage++;
		
		//0923
		//시작페이지, 끝페이지 값 구하기
		int startpage = ((cpage-1)/perpage * perpage)+1;
		int endpage = startpage + perpage-1;
		if(endpage >totalPage) endpage = totalPage; // end 페이지 부족할때 totalPage랑 비교해서 맞춰주기.
		
		//시작번호 start값과 끝번호 end값 구하기 
		//[1] => 1~5 [2]6~10 [3] 11~15
		int start = (cpage-1)*perlist+1;
		int end = start+perlist-1;
		
		if(end>totalCount) end=totalCount; //0923 예외처리
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start",start ); //mapper에서 select 쿼리문으로 쓴거 ! 
		map.put("end", end ); //mapper에서 select 쿼리문으로 쓴거 ! 
		
		List<BoardVO> list = service.selectByPage(map);
		
		//3. 결과값 request에 저장
		request.setAttribute("list", list);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		//4.jsp로 이동 - list.jsp
//		 RequestDispatcher disp = request.getRequestDispatcher("board/list.jsp"); // 각 게시글 data를 배열로 출력 jsp
		RequestDispatcher disp = request.getRequestDispatcher("board/listpage.jsp"); //게시글 data 배열로 출력 + 페이지번호 보이는 jsp
		disp.forward(request, response);
		
		//0923
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
