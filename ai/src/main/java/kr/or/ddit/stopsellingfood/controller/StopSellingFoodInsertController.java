package kr.or.ddit.stopsellingfood.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.stopsellingfood.service.IStopSellingService;
import kr.or.ddit.vo.StopSellingFoodVO;

@Controller
@RequestMapping("/stopSellingFood/stopSellingInsert.do")
public class StopSellingFoodInsertController {
	
	@Inject
	IStopSellingService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public String view() {
		return "stopSellingFood/stopSellingForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String createStopselling(@Valid@ModelAttribute("stopselling") StopSellingFoodVO stopselling,Errors errors,Model model,
			RedirectAttributes redirect)  {
		
		boolean valid = !errors.hasErrors();
		String viewName = null;
		String message = null;
		
		if(valid) {
			ServiceResult result = service.createStopSelling(stopselling);
			switch (result) {
			case OK:
				message="추가성공";
				viewName ="redirect:/stopSellingFood/stopSellingView.do?stopsellId="+ stopselling.getStopsellId();
				redirect.addFlashAttribute("success", message);
				break;
			default:
				message = "서버오류";
				viewName = "stopSellingFood/stopSellingForm";
				model.addAttribute("fail", message);
				break;
			}
		}else {
			viewName = "stopSellingFood/stopSellingForm";
		}

		return viewName;
	}

}
