package kr.or.ddit.correctboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.correctboard.service.ICorrectBoardService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.CorrectBoardVO;

@Controller
@RequestMapping("/correct")
public class CorrectDeleteController {
	
	@Inject
	ICorrectBoardService service;
	
	@RequestMapping(value="correctDelete.do",method=RequestMethod.POST)
	public String deleteCorrect(
			@RequestParam(required=true,name="correctNo") int correctNo
			,@RequestParam(required=true,name="memId") String memId,
			RedirectAttributes redirect
			) {
		
		ServiceResult result = service.deleteCorrect(new CorrectBoardVO(correctNo,memId));
		String viewName = "redirect:/correct/correctView.do?correctNo="+correctNo;
		String message = null;
		switch (result) {
			case FAILED:
				message="서버오류";
				redirect.addFlashAttribute("fail", message);
				break;
			case INVALIDPASSWORD:
				message="비번오류";
				redirect.addFlashAttribute("password", message);
				break;
	
			default:
				message="삭제성공";
				viewName="redirect:/correct/correctList.do";
				redirect.addFlashAttribute("success", message);
				break;
		}
		
		return viewName;
	}

}
