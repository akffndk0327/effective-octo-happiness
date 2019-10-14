package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.CookieUtil;
import kr.or.ddit.utils.CookieUtil.TextType;
import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.PagingInfoVO;

@CommandHandler
public class BoardRetreiveController {
	IBoardService service = new BoardServiceImpl();

	@URIMapping("/board/boardList.do") // UI 동기, data 비동기
	public String list(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String pageParam = req.getParameter("page"); // input 태그의 name
		String searchType = req.getParameter("searchType"); // select 의 name
		String searchWord = req.getParameter("searchWord"); // input태그의 name 값

		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);

		int currentPage = 1;
		if (StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}

		PagingInfoVO<Board2VO> pagingVO = new PagingInfoVO<Board2VO>(7, 5);

		pagingVO.setSearchMap(searchMap);
		int totalRecord = service.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<Board2VO> list = service.retrieveBoardList(pagingVO);
		pagingVO.setDataList(list);

		String accept = req.getHeader("Accept");

		// 마샬링 & 직렬화
		if (accept.toLowerCase().contains("json")) {
			resp.setContentType("application/json;charset=UTF-8");

			ObjectMapper mapper = new ObjectMapper(); // java -> json
			try ( // ?????????????????
					PrintWriter out = resp.getWriter();) {
				mapper.writeValue(out, pagingVO); // 자바객체를 json으로 변환 <->json을 java객체로 변화 :readValue();
			}
			return null;
		} else {
			req.setAttribute("pagingVO", pagingVO);
			String viewName = "board/boardList";
			return viewName;
		}
	}

	@URIMapping("/board/boardView.do")
	public String boardView(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String what = req.getParameter("what"); // boardList에서 location.href="${cPath}/board/boardView.do?what="+bono; 로
												// what넘김
		if (StringUtils.isBlank(what)) {
			resp.sendError(400);
			return null;
		}
		Board2VO board = service.retrieveBoard(new Board2VO(Integer.parseInt(what)));
		req.setAttribute("board", board);

		// 1011 쿠키의 문자열이 필요해서
		String cookieValue = new CookieUtil(req).getCookieValue("likeCookie");
		boolean likable = false;
		if (cookieValue != null) { // 언마샬링하기
			ObjectMapper mapper = new ObjectMapper();
			int[] boNOs = mapper.readValue(cookieValue, int[].class); // 여기에 글번호잇나 확인학 ㅣ
			// 정렬해야함.
			Arrays.sort(boNOs);
			int idx = Arrays.binarySearch(boNOs, Integer.parseInt(what));// 이진검색 authe~lization에서 사용함
			if (idx < 0)
				likable = true;// likalbe true로 바꾸기 + jsp 바꾸기
		} else {
			likable = true;
		}
		req.setAttribute("likable", likable);
		return "board/boardView";
	}

	@URIMapping("/board/boardLike.do")
	public String like(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String what = req.getParameter("what");

		if (!StringUtils.isNumeric(what)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}

//		int bo_no = Integer.parseInt(what);
//		ServiceResult result = service.incrememtLike(bo_no); //추천수 하나 증가시킴
		ServiceResult result = service.incrememtLike(Integer.parseInt(what)); // 추천수 하나 증가시킴
		resp.setContentType("text/plain");
		if (ServiceResult.OK.equals(result)) {
			String cookieValue = new CookieUtil(req).getCookieValue("likeCookie");
			ObjectMapper mapper = new ObjectMapper();
			int[] array = null;
			if (cookieValue != null) { // 쿠키밸류는 문자열만잇는데 글번호는 숫자야 -> 토큰의 구분자
				// 여기 걸리면
				// cookieValue: json ->java로 언마샬링
				int[] boNOs = mapper.readValue(cookieValue, int[].class);
				array = new int[boNOs.length + 1];
				System.arraycopy(boNOs, 0, array, 0, boNOs.length);
				array[boNOs.length] = Integer.parseInt(what);

			} else {// 여기 걸리면 글번호 1개만 가지고 놀아
				array = new int[] { Integer.parseInt(what) }; // 모든 글번호는 array가 가지고 있음
			}
			// array ->json으로
			cookieValue = mapper.writeValueAsString(array);
			Cookie likeCookie = CookieUtil.createCookie("likeCookie", cookieValue, 
					req.getContextPath(), TextType.PATH,
					60 * 60 * 24 * 7); // 쿠키는 여기서 생성하지만 사용은 boardView.jsp => name, value, 사용위치 3개필요
			resp.addCookie(likeCookie);
		}
		try (PrintWriter out = resp.getWriter();

		) {
			out.print(result.name()); // 성공이면 ok에 걸려
		}
		return null;
//		
//		
//		String accept = req.getHeader("Accept");
//		//마샬링 & 직렬화
//		String viewName = null;
//		String message = null;
//		
//		switch (result) {
//		case OK:
//			//추천수가 증가된 결과값을 다시 조회 
//			Board2VO board =new Board2VO();
//			board.setBo_no(bo_no);
//			board = service.retrieveBoard(board); //추천 올라간 결과값
//			if(accept.toLowerCase().contains("json")) {
//				resp.setContentType("application/json;charset=UTF-8");
//				
//				ObjectMapper mapper = new ObjectMapper(); //java -> json 
//				try( 
//					PrintWriter out = resp.getWriter();	
//				){
//					mapper.writeValue(out, board); //자바객체를 json으로 변환 <->json을 java객체로 변화 :readValue();
//				}
//				return null;
//			}
//			break;
//		default:
//			message ="서버오류";
//			viewName="redirect:/board/boardView.do?what="+what;
//			break;
//		}
//		return viewName;
	}
}
