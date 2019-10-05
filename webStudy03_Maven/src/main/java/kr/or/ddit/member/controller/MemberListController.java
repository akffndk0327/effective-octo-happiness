 package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.MarshallingUtils;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;

@CommandHandler //마킹해놓은 => 핸들러맵퍼가 수집함.. Plain Old  Java Object (POJO) 오래된 클래스이다.
public class MemberListController { // 0924 서블릿 삭제, alt, shift r
//	private static Logger logger = LoggerFactory.getLogger(MemberListController.class);
	IMemberService service = MemberServiceImpl.getInstance();

//   @Override =>컴파일 대상
	@URIMapping(value = "/member/memberList.do", method = HttpMethod.GET)
	// doget -> memberList로 바꿔도 상관x
	public String memberList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		//10.02 검색기능 
//		MemberVO searchVO = new MemberVO();
//		try {
//			BeanUtils.populate(searchVO, req.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			logger.error("검색중예외 발생!!",e);
//		}
				
		//1001 페이징 처리
		String pageParam = req.getParameter("page");
		String searchType = req.getParameter("searchType");
		String searchWord = req.getParameter("searchWord");
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchType",searchType);
		searchMap.put("searchWord",searchWord);
		
		int currentPage =1;
		if(
			StringUtils.isNumeric(pageParam)
		 ){ //페이지 잇나 없나 확인! 
			currentPage = Integer.parseInt(pageParam); //클라이언트꺼는 current page에 
		}
		// 동기/비동기 구분을 해 응답데이터를 넘기기 위함
		String accept = req.getHeader("Accept");

		//1001 pagingVO 만들기
		PagingInfoVO<MemberVO> pagingVO = new PagingInfoVO<MemberVO>(5,3); //5 : 게시물갯수, 3 : 하단의 페이지수
		pagingVO.setSearchMap(searchMap); //1002 searchVO 를 pagingVO에 넣기 =>member.xml 로가
		
		//토탈 & 현재페이지 넣기
		int totalRecord = service.retrieveMemberCount(pagingVO); //토탈레코드, 페이지 설정됨.
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		// 데이터 조회
		List<MemberVO> list = service.retrieveMemberList(pagingVO);
		pagingVO.setDataList(list); //제너릭타입 쓴 이유
		
		
		//마살링 대상도 바뀜 list->pagingVO
		if (accept.toLowerCase().contains("json")) { // 비동기
			resp.setContentType("application/json;charset=UTF-8");

			// json을 만들기위한 marshalling
			String json = new MarshallingUtils().marshalling(pagingVO);

			try (
				PrintWriter out = resp.getWriter();
			) {
				out.println(json);
			}
			return null; // 0924 d여기 처리해야돼!!

		} else {
		// 동기
		// 0924
//         String viewName = "/WEB-INF/views/member/memberList.jsp";
//         req.getRequestDispatcher(viewName).forward(req, resp);
			String viewName = "member/memberList"; // -\=> 논리적인 viewName = logical~
			return viewName;
		}

	}
}