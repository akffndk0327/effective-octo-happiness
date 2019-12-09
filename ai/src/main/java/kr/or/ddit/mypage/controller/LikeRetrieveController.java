package kr.or.ddit.mypage.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.recipeboard.service.IRecipeBoardService;
import kr.or.ddit.vo.Like2VO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.RecipeBoardVO;

/**
 * @author 허민지
 * @since 2019. 11. 6.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 6.      허민지       최초작성
 * 2019. 11. 6.      이유진       기능구현
 * Copyright (c) 2019 by DDIT All right reserved
 *      </pre>
 */
@Controller
@RequestMapping("/memMypage")
public class LikeRetrieveController {
	@Inject
	IRecipeBoardService service;
	@Inject
	IMemberService memberService;

	@RequestMapping("recipeLike.do")
	public String selectLikeList(Authentication authentication, Model model) {
		String id = authentication.getName();
		List<RecipeBoardVO> likeList = service.selectLikeMypage(id);
		MemberVO member = (MemberVO) authentication.getPrincipal();
		MemberVO savedMember = memberService.retrieveMember(member);
		
		model.addAttribute("likeList", likeList);
		model.addAttribute("savedMember", savedMember);
		return "memMypage/likeList";
	}
}
