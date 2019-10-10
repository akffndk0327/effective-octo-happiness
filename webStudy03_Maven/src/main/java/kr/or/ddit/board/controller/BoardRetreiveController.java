package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.PagingInfoVO;

@CommandHandler
public class BoardRetreiveController {
	IBoardService service = new BoardServiceImpl();
	
	@URIMapping("/board/boardList.do") // UI 동기, data 비동기
	public String list(HttpServletRequest req, HttpServletResponse resp)
		throws IOException, ServletException{
		String pageParam = req.getParameter("page"); //input 태그의 name
		String searchType = req.getParameter("searchType"); //select 의 name
		String searchWord = req.getParameter("searchWord"); //input태그의 name 값 
		
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);
		
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		
		PagingInfoVO<Board2VO> pagingVO = 
					new PagingInfoVO<Board2VO>(7, 5);
		
		pagingVO.setSearchMap(searchMap);
		int totalRecord = service.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<Board2VO> list = service.retrieveBoardList(pagingVO);
		pagingVO.setDataList(list);
		
		String accept = req.getHeader("Accept");
		
		//마샬링 & 직렬화
		if(accept.toLowerCase().contains("json")) {
			resp.setContentType("application/json;charset=UTF-8");
			
			ObjectMapper mapper = new ObjectMapper(); //java -> json 
			try( // ?????????????????
				PrintWriter out = resp.getWriter();	
			){
				mapper.writeValue(out, pagingVO); //자바객체를 json으로 변환 <->json을 java객체로 변화 :readValue();
			}
			return null;
		}else {
			req.setAttribute("pagingVO", pagingVO);
			String viewName = "board/boardList";
			return viewName;
		}
	}
	
	@URIMapping("/board/boardView.do")
	public String boardView(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String what = req.getParameter("what"); //boardList에서 location.href="${cPath}/board/boardView.do?what="+bono; 로 what넘김 
		if(StringUtils.isBlank(what)) {
			resp.sendError(400);
			return null;
		}
		Board2VO board = 
				service.retrieveBoard(new Board2VO(Integer.parseInt(what)));
		req.setAttribute("board", board);
		return "board/boardView";
	}
}

