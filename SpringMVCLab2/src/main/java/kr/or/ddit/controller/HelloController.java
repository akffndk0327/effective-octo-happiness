package kr.or.ddit.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //@component 등록할때 기본 7가지중 1 개 . 목적1 Spring bean 등록 , 2 Command handler 지정 
public class HelloController { //저 어노테이션 써서 bean으로 쓸수있는 준비 됨. s 안생긴건 component-scan안됨.
	
	//특정패턴 요청받아야하닌까 하위의 servlet-context.xml에 등록하기=> <context:component-scan base-package="kr.or.ddit">
	@RequestMapping("/hello.do") //=>URIMapping. 주소만 맞으면 여기서 실행됨. get post 상관 x

//	public String hello(HttpServletRequest req) { //응답은 받기싫으면 안받아도 됨.
		public String hello(Model model) { //응답은 받기싫으면 안받아도 됨.
		Date today = new Date(); //request 스코프에 담아야해
//		req.setAttribute("today", today);
		model.addAttribute("today", today); //request 스코프와 같음. 
		return "hello"; //views : view단 
	}
}
