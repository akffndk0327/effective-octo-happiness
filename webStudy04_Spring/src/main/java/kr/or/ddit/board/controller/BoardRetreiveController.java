package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.utils.CookieUtil;
import kr.or.ddit.utils.CookieUtil.TextType;
import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.PagingInfoVO;

@Controller
@RequestMapping("/board/{board_type}/")
public class BoardRetreiveController {
	@Inject
	IBoardService service;
	@Inject
	WebApplicationContext container;
	ServletContext application;
	
	@PostConstruct
	public void init() {
		application = container.getServletContext();
	}

	 @RequestMapping(value="boardList.do")// UI 동기, data 비동기
	public String list(
			@PathVariable(required=true)String board_type,
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
	@RequestParam(name="page", required=false, defaultValue="1") int currentPage //넘어갈때 넘어올때 name으로 받는다
			,Model model ){

		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);


		PagingInfoVO<Board2VO> pagingVO = new PagingInfoVO<Board2VO>(7, 5);
		
		Board2VO searchVO = new Board2VO();
		searchVO.setBoard_type(board_type);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setSearchMap(searchMap);
		int totalRecord = service.retrieveBoardCount(pagingVO); //분류넘어가야해 
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<Board2VO> list = service.retrieveBoardList(pagingVO);
		pagingVO.setDataList(list);

//		String accept = req.getHeader("Accept");

//		// 마샬링 & 직렬화
//		if (accept.toLowerCase().contains("json")) {
//			resp.setContentType("application/json;charset=UTF-8");
//
//			ObjectMapper mapper = new ObjectMapper(); // java -> json
//			try ( // ?????????????????
//					PrintWriter out = resp.getWriter();) {
//				mapper.writeValue(out, pagingVO); // 자바객체를 json으로 변환 <->json을 java객체로 변화 :readValue();
//			}
//			return null;
//		} else {
			model.addAttribute("pagingVO", pagingVO);
			String viewName = "board/boardList";
			return viewName;
//		}
	}
	 
	 @RequestMapping(value="boardList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		public PagingInfoVO listForAjax(
				@PathVariable(required=true) String board_type,
				@RequestParam(required=false) String searchType,
				@RequestParam(required=false) String searchWord,
				@RequestParam(name="page", required=false, defaultValue="1") int currentPage
				, Model model
				){
			list(board_type, searchType, searchWord, currentPage, model);
			return (PagingInfoVO<Board2VO>) model.asMap().get("pagingVO");
		}
	 
	 

@RequestMapping("boardView.do")
public String boardView(@PathVariable(required=true) String board_type,
		@RequestParam(required=true)int what,Model model,@CookieValue(name="likeCookie", required=false) String cookieValue
		) throws JsonParseException, JsonMappingException, IOException{
	Board2VO board = 
			service.retrieveBoard(new Board2VO(what));
	model.addAttribute("board", board);

		// 1011 쿠키의 문자열이 필요해서
//		1021 쿠기.. 
//		String cookieValue = new CookieUtil(req).getCookieValue("likeCookie");
		boolean likable = false;
		if (cookieValue != null) { // 언마샬링하기
			ObjectMapper mapper = new ObjectMapper();
			int[] boNOs = mapper.readValue(cookieValue, int[].class); // 여기에 글번호잇나 확인학 ㅣ
			// 정렬해야함.
			Arrays.sort(boNOs);
			int idx = Arrays.binarySearch(boNOs, what);// 이진검색 authe~lization에서 사용함
			if (idx < 0)
				likable = true;// likalbe true로 바꾸기 + jsp 바꾸기
		} else {
			likable = true;
		}
		model.addAttribute("likable", likable);
		return "board/boardView";
	}


@RequestMapping(value="boardLike.do", produces=MediaType.TEXT_PLAIN_VALUE)
@ResponseBody //응답데이터 json el 안쓰고 pathVa~ 안써도 되ㅔㅐ
public String updateLike(
		@RequestParam(required=true) int what, 
		@CookieValue(required=false, name="likeCookie") String cookieValue
		, HttpServletResponse resp
		) throws IOException {
	ServiceResult result = service.incrememtLike(what);
	if(ServiceResult.OK.equals(result)) {
		ObjectMapper mapper = new ObjectMapper();
		int[] array = null;
		if(cookieValue!=null) { // 쿠키밸류는 문자열만잇는데 글번호는 숫자야 -> 토큰의 구분자
				// 여기 걸리면
				// cookieValue: json ->java로 언마샬링
			int[] boNOs = mapper.readValue(cookieValue, int[].class);
			array = new int[boNOs.length+1];
			System.arraycopy(boNOs, 0, array, 0, boNOs.length);
			array[boNOs.length] = what;

			} else {// 여기 걸리면 글번호 1개만 가지고 놀아
				array = new int[] {what}; // 모든 글번호는 array가 가지고 있음
			}
			// array ->json으로
		cookieValue = mapper.writeValueAsString(array);
		Cookie likeCookie = CookieUtil.createCookie("likeCookie", cookieValue, 
						application.getContextPath(), TextType.PATH, 60*60*24*7);// 쿠키는 여기서 생성하지만 사용은 boardView.jsp => name, value, 사용위치 3개필요
		resp.addCookie(likeCookie);
	}
	return result.name();
	}
}
