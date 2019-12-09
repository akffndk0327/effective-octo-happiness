package kr.or.ddit.member.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.exception.NotAuthenticatedException;
import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

/**
 * @author 허민지
 * @since 2019. 11. 6.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     		--------    ----------------------
 * 2019. 11. 6.      	허민지      	 최초작성
 * 2019. 11. 16.        허민지		 checkPassForUpdate 추가
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/member")
public class MemberUpdateController {
	
	@Inject
	IMemberService service;
	
	@Inject
	DelegatingPasswordEncoder passwordEncoder;
	
	@RequestMapping(value="checkPassForUpdate.do", method=RequestMethod.POST)
	public String checkPassForUpdate(
			@RequestParam(required=true) String memPass,
			Authentication authentication,
			Model model,
			RedirectAttributes redirect) {
		
		MemberVO authMember = (MemberVO) authentication.getPrincipal();
		String viewName = null;
		String memId = authentication.getName();
		
		if(!passwordEncoder.matches(memPass, authMember.getMemPass())) {
			redirect.addFlashAttribute("msg", memId);
			//비밀번호가 틀렸을 때
			//일반회원일때의 viewName
			viewName="redirect:/memMypage/member?memId="+memId;
			//기업회원일때의 viewName
			if(authMember.getAuthorId().equals("ROLE_EMP")) {
				viewName="redirect:/mypage/company";
			}
		}else {
			//비밀번호가 맞았을때
			MemberVO savedMember = service.retrieveMember(authMember);
			model.addAttribute("savedMember", savedMember);
			viewName = "memMypage/memberView";
			if(savedMember.getAuthorId().equals("ROLE_EMP")) {
				viewName = "comMypage/memberView";
			}
		}
		return viewName;
	}
	
	@RequestMapping(value="memberUpdate.do", method=RequestMethod.POST)
	public String doPost(
			@Valid @ModelAttribute("member") MemberVO member, Errors errors, 
			RedirectAttributes redirect,
			Authentication authentication
			){
		String memId = authentication.getName();
		boolean valid = !errors.hasErrors();
		String viewName = "redirect:/memMypage/member?memId="+memId;
		String message = null;
		if(valid) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case FAILED:
//				- FAILED  : redirect -> mypage
				message = "서버 오류";
				break;
			default:
				message = "수정 성공";
				//일반회원일때의 viewName
				viewName = "redirect:/memMypage/member?memId="+memId;
				//기업회원일때의 viewName
				if(member.getAuthorId().equals("ROLE_EMP")) {
					viewName = "redirect:/mypage/company";
				}
				break;
			}
			
		}else {
//		4. 불통  : redirect -> mypage
			viewName="redirect:/mypage/member?memId="+memId;
		}
		redirect.addFlashAttribute("message", message);
		return viewName;
	}
}
