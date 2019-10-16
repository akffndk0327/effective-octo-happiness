package kr.or.ddit.member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.member.exception.UserNotFoundException;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/idCheck.do")
//@Controller
@RestController
public class IdCheckServlet {
	@Inject
	IMemberService service ;
	
	@RequestMapping(value = "/member/idCheck.do", method =RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody //위에 rest써서 생략가능함.
	public Map<String, Object> doPost(@RequestParam(required=true)String mem_id){//@RequestParam(required=true): 검증필요하면 해라 
		Pattern regex = Pattern.compile("([a-z]+[a-zA-Z0-9]){3,11}");
		Matcher matcher=regex.matcher(mem_id);
		while(matcher.find()) {
			System.out.println(matcher.group(1));
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			service.retrieveMember(new MemberVO(mem_id, null));
			result.put("valid",new Boolean(false));
		}catch (UserNotFoundException e) {
			result.put("valid",new Boolean(true));
			result.put("userId", mem_id);
		}
		return result;
		
	}
}
