package kr.or.ddit.uneatable.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.uneatable.service.IUneatableService;

@Controller
@RequestMapping("/uneatable")
public class UneatAbleDeleteController {
	
	@Inject
	IUneatableService service;
	
	@RequestMapping(value="uneatableDelete.do",method=RequestMethod.POST)
	public String deleteUneatable(@RequestParam(required=true,name="uneatId") int uneatId
			, RedirectAttributes redirect) {
		
		ServiceResult result = service.deleteUneatable(uneatId);
		String viewName = null;
		String message = null;
		
		switch (result) {
		case FAILED:
			message = "서버오류";
			viewName = "redirect:/uneatable/uneatableView.do?uneatId="+uneatId; 
			redirect.addFlashAttribute("fail", message);
			break;

		case OK:
			message = "삭제성공";
			viewName = "redirect:/uneatable/uneatableList.do"; 
			redirect.addFlashAttribute("success", message);
			break;
		}
		
		return viewName;
	}

}
