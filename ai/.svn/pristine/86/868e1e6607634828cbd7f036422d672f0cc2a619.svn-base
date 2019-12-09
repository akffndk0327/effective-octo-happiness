package kr.or.ddit.mypage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.mypage.service.IMemMypageService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MonthlyVO;
import kr.or.ddit.vo.PagingInfoVO;

@Controller
@RequestMapping("/memMypage")
public class MemberMonthlyController {

	@Inject
	IMemMypageService service;
	
	@Inject
	IMemberService memberService;
	
	@RequestMapping(value="monthlyView.do")
	public String selectMonthly(
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			Authentication authentication,
			Model model) {
		PagingInfoVO<MonthlyVO> pagingVO = new PagingInfoVO<>(5, 5);
		pagingVO.setPk(authentication.getName());
		int totalRecord = service.retrieveMemberDietCount(pagingVO);
		
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<MonthlyVO> m_list = service.retrieveMemberDietList(pagingVO);
		pagingVO.setDataList(m_list);
		String viewName = null;
		model.addAttribute("pagingVO", pagingVO);
		return viewName = "memMypage/monthlyView";
	}
}
