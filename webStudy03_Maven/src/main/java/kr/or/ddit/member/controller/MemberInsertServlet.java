package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/memberInsert.do")
@CommandHandler
public class MemberInsertServlet {
	IMemberService service = MemberServiceImpl.getInstance();
	@URIMapping("/member/memberInsert.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 여기서는 jsp에 연결만 해주기.
		String viewName = "member/memberForm";
//		RequestDispatcher disp = req.getRequestDispatcher(viewName);
//		disp.forward(req, resp);

		// 들어온느 요청 2개 구분하기 a태그로 들어오는지 form태그인지 => 잘못썻을 경우 기존 데이터 복원 !
		// 파라미더
		return viewName;
	}

	// 보내주는 데이터 받아야해
	@URIMapping(value = "/member/memberInsert.do", method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVO member = new MemberVO(); // 가입하는 정보는 이거만 있느면 됨

		req.setAttribute("member", member); // 여기가 공유하는 거 !!!! - 기존데이터 가지고잇음
		// 실패했을때 계속 가지고 다녀야하고 - 그럼 어디에 공유를 해야해

		try {
			BeanUtils.populate(member, req.getParameterMap()); // 실행되면 모든 파라미터 갖고 있어 . redirection안하면 정보 곗고 살아있어

		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}

		// 검증하기 - 기준,룰은 db에 맞춰서 1/필수테이터는 뭐고 입력데이터는ㅁ ㅝ고 길이제한틍ㄴ어떻게 ?해야하는가
		Map<String, String> errors = new HashMap<String, String>(); // 검증 결과 담기위해

		req.setAttribute("errors", errors);

		// scope 에는 기존데이터, 검증결과 데이터 2개 들어있음

		boolean valid = validate(member, errors); // member: 검증대상 타겟 callbye

		String viewName = "member/memberForm";
		String message = null;
//		boolean redirect = false;
		if (valid) {
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				message = "아이디 중복";
				viewName = "member/memberForm";
				break;
			case FAILED:
				message = "서버 오류";
				viewName = "member/memberForm";
				break;
			default:
//				- OK   : 디스패쳐 -> welcome page
				message = "수정 성공";
//				redirect = true;
				viewName = "redirect:/";
				break;
			}

		} else {
			viewName = "member/memberForm"; //검증통과못하면 여기로 가겟다. +기존 입력 데이터, 검증데이터 가지고잇음 
		}
		
		req.setAttribute("message", message);
		return viewName;
		
//		if(redirect) {
//			resp.sendRedirect(req.getContextPath() + viewName);
//		}else {
//			String prefix = "/WEB-INF/views/";
//			String suffix = ".jsp";
//			viewName = prefix + viewName + suffix;
//			req.getRequestDispatcher(viewName).forward(req, resp);
//		}
	}

		//스키마에 따라 검증 룰이 달라짐  
		
		//통과햇다면 등록 
		
		//통과못햇으면 알람띄워서 다시 입력하게하기 - memberform으로 가야헤 
		
		//가져갈것 기존 입력 데이터, 뭐가 잘못되엇는지 정보 
		
		//map활용 멤버 업데이트 서블릿과 비슷해 
		
		//가입하려면 서비스 만들어
		
		//메서드호출
		
		//createreturn 타입의 result는 
		
		//pk중복 -member form  
		
		//메세지, 기존 입력데이터 
		
		//3. 가입성공시 : 로그인화면으로 보내기 loginform
	 
	//db 필수데이터 검증 코드조각 
	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true; // 무조건 통과
		if (StringUtils.isBlank(member.getMem_id())) {
			valid = false;
			errors.put("mem_id", "회원아이디 누락");
		}
		if (StringUtils.isBlank(member.getMem_pass())) {
			valid = false;
			errors.put("mem_pass", "비밀번호 누락");
		}
		if (StringUtils.isBlank(member.getMem_name())) {
			valid = false;
			errors.put("mem_name", "이름 누락");
		}
		if (StringUtils.isBlank(member.getMem_zip())) {
			valid = false;
			errors.put("mem_zip", "우편번호 누락");
		}
		if (StringUtils.isBlank(member.getMem_add1())) {
			valid = false;
			errors.put("mem_add1", "주소1 누락");
		}
		if (StringUtils.isBlank(member.getMem_add2())) {
			valid = false;
			errors.put("mem_add2", "주소2 누락");
		}
		if (StringUtils.isBlank(member.getMem_mail())) {
			valid = false;
			errors.put("mem_mail", "이메일 누락");
		}

		return valid;
	}
	
}












