package kr.or.ddit.mypage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.mypage.service.IMemMypageService;
import kr.or.ddit.order.service.IOrderService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MonthlyVO;
import kr.or.ddit.vo.Order2VO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.RecipeBoardVO;

/**
 * @author 허민지
 * @since 2019. 11. 6.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 6.      허민지       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class MypageController {
	@Inject
	IMemberService service;
	
	@Inject
	IMemMypageService mypageService;
	
	
	@RequestMapping(value="memMypage/member")
	public String myPageView(Authentication authentication,
							@RequestParam(required=true) String memId,
							Model model) {
		PagingInfoVO<Order2VO> order = new PagingInfoVO<>(3, 3);
		order.setPk(memId);
		MemberVO authMember = (MemberVO) authentication.getPrincipal();
		String viewName = null;
		MemberVO savedMember = service.retrieveMember(authMember);
		List<MonthlyVO> monthlyRecentList = service.orderByMonthly(memId);
		List<RecipeBoardVO> recipeRecentList = service.orderByRecipe(memId);
		//최근 주문내역 3건만을 불러온다
		List<Order2VO> orderRecentList = mypageService.orderByOrderList(memId);
		
		
		model.addAttribute("monthlyRecentList", monthlyRecentList);
		model.addAttribute("recipeRecentList", recipeRecentList);
		model.addAttribute("orderRecentList", orderRecentList);
		model.addAttribute("savedMember", savedMember);
		viewName = "memMypage/mypage";
		
		return viewName;
	}
}
