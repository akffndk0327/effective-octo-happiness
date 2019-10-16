package kr.or.ddit.member.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/memberInsert.do")
@Controller
@RequestMapping(value="/member")

public class MemberInsertController {
	@Inject
	IMemberService service ;
	@RequestMapping("memberInsert.do")
	public String doGet(){
		// 여기서는 jsp에 연결만 해주기.
		String viewName = "member/memberForm";
		return viewName;
	}

	// 보내주는 데이터 받아야해
	@RequestMapping(value = "memberInsert.do", method=RequestMethod.POST)
	public String doPost(@Valid @ModelAttribute("member") MemberVO member,  //memberForm.jsp에서 바등ㅁ
						Errors errors, 	
						Model model){
		//("member") MemberVO member: commandObject
//		@Valid : 검증하고 넣어라 기준이 잇어야해 -밑에 코드 길었던더 

//		session.setAttribute("member", member); // 여기가 공유하는 거 !!!! - 기존데이터 가지고잇음
		
		// 검증하기 - 기준,룰은 db에 맞춰서 1/필수테이터는 뭐고 입력데이터는ㅁ ㅝ고 길이제한틍ㄴ어떻게 ?해야하는가
//		Map<String, String> errors = new HashMap<String, String>(); // 검증 결과 담기위해
//		model.addAttribute("errors", errors);
		
		// scope 에는 기존데이터, 검증결과 데이터 2개 들어있음
//		boolean valid = validate(member, errors); // member: 검증대상 타겟 callbye
		boolean valid = !errors.hasErrors(); //hasErrors():문제잇어서 검증통과못햇다는 뜻 ! 붙이면 반대 ~ 
		String viewName = "member/memberForm";
		String message = null;
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
		
		model.addAttribute("message", message);
		return viewName;
		
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
	 
	//1016 검증하면서 @Valid로 불러옴 + Errors로 검사함 
	//db 필수데이터 검증 코드조각 
//	private boolean validate(MemberVO member, Map<String, String> errors) {
//		boolean valid = true; // 무조건 통과
//		if (StringUtils.isBlank(member.getMem_id())) {
//			valid = false;
//			errors.put("mem_id", "회원아이디 누락");
//		}
//		if (StringUtils.isBlank(member.getMem_pass())) {
//			valid = false;
//			errors.put("mem_pass", "비밀번호 누락");
//		}
//		if (StringUtils.isBlank(member.getMem_name())) {
//			valid = false;
//			errors.put("mem_name", "이름 누락");
//		}
//		if (StringUtils.isBlank(member.getMem_zip())) {
//			valid = false;
//			errors.put("mem_zip", "우편번호 누락");
//		}
//		if (StringUtils.isBlank(member.getMem_add1())) {
//			valid = false;
//			errors.put("mem_add1", "주소1 누락");
//		}
//		if (StringUtils.isBlank(member.getMem_add2())) {
//			valid = false;
//			errors.put("mem_add2", "주소2 누락");
//		}
//		if (StringUtils.isBlank(member.getMem_mail())) {
//			valid = false;
//			errors.put("mem_mail", "이메일 누락");
//		}
//
//		return valid;
//	}
	
}












