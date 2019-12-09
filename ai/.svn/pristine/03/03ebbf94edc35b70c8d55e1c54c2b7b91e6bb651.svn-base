package kr.or.ddit.stopsellingfood.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.stopsellingfood.service.IStopSellingService;
import kr.or.ddit.vo.StopSellingFoodVO;

/**
 * @author 이진희
 * @since 2019. 11. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 11.      이진희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/stopSellingFood/stopSellingUpdate.do")
public class StopSellingFoodUpdateController {

	
	@Inject
	IStopSellingService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(
			@RequestParam(required=true) int stopsellId,Model model
			) {
		
		StopSellingFoodVO stopsell = new StopSellingFoodVO();
		stopsell.setStopsellId(stopsellId);
		
		StopSellingFoodVO stopsellVO = service.selectStopSelling(stopsell);
		model.addAttribute("stopsellVO", stopsellVO);
		
		return "stopSellingFood/stopSellingForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String UpdateStopSell(
			@Valid@ModelAttribute("stopselling") StopSellingFoodVO stopselling,
			Errors errors,Model model,
			RedirectAttributes redirect) throws IOException {
		
		boolean valid = !errors.hasErrors();
		String viewName = null;
		String message = null;
		
		if(valid) {
			ServiceResult result = service.updateStopSelling(stopselling);
			switch (result) {
			case OK:
				message = "수정성공";
				viewName="redirect:/stopSellingFood/stopSellingView.do?stopsellId="+stopselling.getStopsellId();
				redirect.addFlashAttribute("success", message);
				break;
			default:
				message="서버오류";
				viewName="stopSellingFood/stopSellingForm";
				model.addAttribute("fail", message);
				break;
			}
		}else {
			viewName="stopSellingFood/stopSellingForm";
		}
		return viewName;
	}
	
	
}
