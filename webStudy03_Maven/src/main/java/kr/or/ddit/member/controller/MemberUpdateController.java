package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
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
import kr.or.ddit.wrapper.MultipartRequestWrapper;
import kr.or.ddit.wrapper.PartWrapper;

//@WebServlet("/member/memberUpdate.do")
@CommandHandler
public class MemberUpdateController {
	IMemberService service = new MemberServiceImpl();

	@URIMapping(value = "/member/memberUpdate.do", method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// 1. 요청(파라미터 헤더 바디.. ) 방고,
		MemberVO member = new MemberVO();
//		member.setMem_id(req.getParameter("mem_id"));
//		member.setMem_pass(req.getParameter("mem_pass"));
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException();
		}
		
		if(req instanceof MultipartRequestWrapper) { //이거 있으면 밑에 Parㅅ 없어도 되
			PartWrapper partWrapper = ((MultipartRequestWrapper) req).getPartWrappper("mem_image");
			if(partWrapper!=null) {
				member.setMem_img(partWrapper.getBytes());
			}
		}
		
//		사진 넣기 1004
//		Part part = req.getPart("mem_image");
//		long size = part.getSize(); //3.1에는 원본파일명 가져올수잇고 그 파일명으로 존재여부 확인할수 있음 하지 만 우리는 3.0버전임 
//		if(size>0) {
//			try(
//				InputStream is = part.getInputStream();
//				ByteArrayOutputStream bos = new ByteArrayOutputStream();
//			){
//			 IOUtils.copy(is,bos);
//			 member.setMem_img(bos.toByteArray()) ; //이걸로 바이트 배열로 만들고 이걸 멤버가 가지고 잇는 프로퍼티로 준다
//			}
//			
//		}

		// 2. 분석(검증) => 아이디 비번, 빈칸 검증
		Map<String, String> errors = new HashMap<String, String>();
		boolean valid = validate(member, errors);
		String viewName = "redirect:/mypage";
		String message = null;
		if (valid) {
			// 3. 통과
			// 1) 의존성...... 서비스 받기
			// 2) 메소드호출(ServiceResult modifyMember(member vo))
			// - userNotfoundexception -500 throw로 보내 안해도됨...
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				// - invalidpassword : redirection -> mypage -> jsp or controller
				message ="비번오류";
				break;
			case FAILED:
				// failed : redirection -> mypage
				message ="서버오류";
				break;

			default:
				// - ok : : redirection -> mypage
				message ="수정성공";
				break;
			}
			
			//4.session scope에 메세지 전송하지
			} else {
				// 4. 불통 ex) 타입 잘 못왓을 때 / redirect ->mypage

			}
			req.getSession().setAttribute("message",message);
			req.getSession().setAttribute("errors",errors);
//			resp.sendRedirect(req.getContextPath() + "/mypage");
			return viewName;

		}

	

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
