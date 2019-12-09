package kr.or.ddit.mypage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.recipeboard.service.IRecipeBoardService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.RecipeBoardVO;

/**
 * @author 이유진
 * @since 2019. 11. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 26.      이유진       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 *      </pre>
 */
@Controller
@RequestMapping("/memMypage")
public class MemberRecipeController {
	@Inject
	IMemberService memberService;
	@Inject
	IRecipeBoardService service;

	@RequestMapping("/recipeList.do")
	public String selectLikeList(Authentication authentication, Model model) {
		MemberVO member = (MemberVO) authentication.getPrincipal();
		String memId = member.getMemId();
		List<RecipeBoardVO> recipeList = service.recipeMypage(memId);
		MemberVO savedMember = memberService.retrieveMember(member);
		model.addAttribute("recipeList", recipeList);
		model.addAttribute("savedMember", savedMember);
		return "memMypage/recipeList";
	}

}
