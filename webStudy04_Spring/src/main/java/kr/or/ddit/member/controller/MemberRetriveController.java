 package kr.or.ddit.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;

@Controller //마킹해놓은 => 핸들러맵퍼가 수집함.. Plain Old  Java Object (POJO) 오래된 클래스이다.
@RequestMapping(value="/member")
public class MemberRetriveController { // 0924 서블릿 삭제, alt, shift r
	@Inject
	IMemberService service ;

	@RequestMapping("memberList.do")
	public String memberList(
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
	@RequestParam(name="page", required=false, defaultValue="1") int currentPage //넘어갈때 넘어올때 name으로 받는다
			,Model model ){
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("searchType",searchType);
		searchMap.put("searchWord",searchWord);
		// 동기/비동기 구분을 해 응답데이터를 넘기기 위함

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
		
		model.addAttribute("pagingVO",pagingVO);
		
		//마살링 대상도 바뀜 list->pagingVO
			String viewName = "member/memberList"; // -\=> 논리적인 viewName = logical~
			return viewName;

	}
	@RequestMapping(value="memberList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE) //마임설정2
	@ResponseBody //이거보고 마샬링할꺼임- 그럼 마샬링할 대상의 객체가 리턴타입이 되야함 
	public PagingInfoVO<MemberVO> memberListAjax(
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currnetPage
			,Model model
			){
		memberList(searchType, searchWord, currnetPage, model);
//		model : 키, 밸류 있음 
		return (PagingInfoVO<MemberVO>) model.asMap().get("pagingVO"); //addAtt의 ""를 서먹음 
		
		//만ㄷ르어진 페이징 Vo만 가져오면 돼! 
		
	}
	
	@RequestMapping("memberView.do")
	public String memberView(
			@RequestParam(required=true) String who, Model model){
	      //service -> retr
	      MemberVO saved = service.retrieveMember(new MemberVO(who,null)); //데이터 조회
//	      req.setAttribute("member", saved);
	      model.addAttribute("member", saved);
//         String viewName = "member/memberView"; //서버사이드 경로 ! -> 디스패치장식 7. 
         String viewName = "/member/memberView"; //tiles 속이기 위해 /member로  IRVR로 넘어감 
         return viewName; //6.
	      }
	
	
}