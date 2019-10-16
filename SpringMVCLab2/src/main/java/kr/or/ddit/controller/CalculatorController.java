package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
//1단계 : 단순 연산만 해보기
//2단계 : 숫자를 박스안에 넣어놓기 


@Controller
@RequestMapping("/cal.do") //중복되는 주소 위로 빼 밑에는 메소드만 남겨
public class CalculatorController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String get() {
		//뷰로 연결만
		return "calForm";
		
	}
	
	//@RequestParam 핸들러 어뎁터에서 정보 받음 
	public ModelAndView prePost(@RequestParam(required=true) int leftOp, 
			@RequestParam(required=false, defaultValue="-1") int rightOp) { 
		//@RequestParam(required=false, defaultValue="-1") int rightOp) 
		//null 못너ㅗㅎ어서 500 나는 데 이때  defaultValue="-1"로 넣기 
		//두개는 한쌍 ! 
		return null;
	}
	
	
	//마지막으로 본거 @ModelAttribute: 모델의 속성명을 바꾸겟다.COC 누르고  이걸로 명시된거 쓰겟다.
	//단계2. 입력한 숫자 , 연산자 등  필요한 모든 정보 넣어놓기 ->VO이용해서 
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView post(@ModelAttribute("calVO") CalculatorVO calVO, 
			@RequestParam(name="user",required=false) String who) { //beanUtils.populate~, VO로 넣으면 2개의 피연산자 잇다고 생가함
		//@RequestParam 없으면 null 나옴.있으면 user넣고
		//=>req가 아닌 다른걸로 호출해야함.=>파라미터 2개 잡아야해  int left~, int right~
//		int result = leftOp+rightOp; //모델 데이터 -> 뷰로 보내기
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("result", result); //Model model. model.addAttrt~오 ㅏ같음
//		mav.setViewName("calForm");
//		return mav;

		//단계2
		//속성명은  CalculatorVO를 첫문자를 소문자로 넣은거 -> jsp 에서 result대신 cal~Vo로 들어감
		//자기가 갖고잇는 모든객체 request scope에 넣음..
		//=>req가 아닌 다른걸로 호출해야함.=>파라미터 2개 잡아야해  int left~, int right~
		int result = calVO.getLeftOp()+calVO.getRightOp(); //모델 데이터 -> 뷰로 보내기\
		calVO.setResult(result);
		ModelAndView mav = new ModelAndView();
		//addObject는 파라미터의 @ModelAttribute로 넘어감
		mav.setViewName("calForm");
		return mav;
		
	}
	//동기, 비동기 헤더 구분 : accept, content-Type
//	요청식별 1. 주소, 2. 파라미터 4.헤더
//	/ produces : json데이터가 만들어져야해 content타입을 뭘로줘야할지
//	request accpet 헤더가 json으로 들어왓는지 보고 위에가 아닌 아래 코드로 실행한다.
//	이걸 뭘로 마샬링할지는 appl/jsom이고 xml마샬링은 text/~
	//리턴 타입이 CalculatorVO
	@RequestMapping(method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody //json데이터 마샬링위한
	public CalculatorVO postAjax(@ModelAttribute("calVO") CalculatorVO calVO, 
			@RequestParam(name="user",required=false) String who) {
		post(calVO,who); //calVO에 결과 있어
		System.out.println(who);
		//json 마샬링 하고 내보야기
		return calVO;
	}
	
}
//get메소드 하나,
