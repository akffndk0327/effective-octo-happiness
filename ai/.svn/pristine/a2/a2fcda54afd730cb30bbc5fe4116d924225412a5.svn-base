package kr.or.ddit.mypage.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.mypage.service.IAdminMypageService;
import kr.or.ddit.mypage.service.IMemMypageService;
import kr.or.ddit.vo.MemAllergyVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;

@Controller
@RequestMapping("/adminMypage")
public class AdminRetireveMemberController {
	
	@Inject
	IAdminMypageService service;
	
	@Inject
	IMemMypageService memMypageService;
	
	@Inject
	IMemberService memberService;
	
	@RequestMapping("memberList.do")
	public String memberList(
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, Model model
			) {
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);
		
		PagingInfoVO<MemberVO> pagingVO = new PagingInfoVO<MemberVO>(10, 5);
		pagingVO.setSearchMap(searchMap);
		int totalRecord = service.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<MemberVO> list = service.retrieveMemberList(pagingVO);
		pagingVO.setDataList(list);
		
		model.addAttribute("pagingVO", pagingVO);
		
		String viewName= "adminMypage/memberList";
		
		return viewName;
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="memberList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingInfoVO<MemberVO> memberListAjax(
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, Model model
			){
		memberList(searchType, searchWord, currentPage, model);
		return (PagingInfoVO<MemberVO>) model.asMap().get("pagingVO"); 
	}
	
	
	@RequestMapping("memberView.do")
	public String memberView(
			@RequestParam(required=true) String who, Model model){
		
		MemberVO saved = service.retrieveMember(new MemberVO(who, null));
		List<MemAllergyVO> memAllList = memberService.selectMemAllergy(who);
		
		model.addAttribute("member", saved);
		model.addAttribute("memAllList", memAllList);
		String viewName = "/adminMypage/memberView";
		return viewName;
	}
	
	
	@RequestMapping("memDelete.do")
	public String memDelete(
			@RequestParam(required=true) String memId,
			RedirectAttributes redirect
			) {
		String viewName=null;
		ServiceResult result = memMypageService.removeMember(memId);
		String message = null;
		switch(result) {
		case FAILED:
			message = "탈퇴 실패";
			viewName="adminMypage/memberList";
			break;
		default :
			message = "탈퇴 성공";
			viewName="redirect:/adminMypage/memberList.do";
			break;
		}
		redirect.addFlashAttribute("message", message);
		return viewName;
	}
	
	
}
