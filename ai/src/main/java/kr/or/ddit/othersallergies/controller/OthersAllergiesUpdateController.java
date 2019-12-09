package kr.or.ddit.othersallergies.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.othersallergies.service.IOthersAllergiesService;
import kr.or.ddit.vo.AllergyVO;

/**
 * @author 허민지
 * @since 2019. 11. 6.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 6.      허민지       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */

@Controller
@RequestMapping("/othersAllergies")
public class OthersAllergiesUpdateController {
	
	@Inject
	IOthersAllergiesService service;
	
	@RequestMapping(value="othersAllergiesForm.do", method=RequestMethod.GET)
	public String updateOthersAllergies(
			@RequestParam(required=true) String allId
			,Model model) {
		
		AllergyVO othersAllergies = service.retrieveOthersAllergies(allId);
		
		model.addAttribute("othersAllergies", othersAllergies);

		return "othersAllergies/othersAllergiesForm";
	}
	
	@RequestMapping(value="othersAllergiesForm.do", method=RequestMethod.POST)
	public String realUpdateOthersAllergies(
			@ModelAttribute("allergy") AllergyVO allergy,
			Model model) {
		String viewName = null;
		
		ServiceResult result = service.modifyOthersAllergies(allergy);
		switch(result) {
		case FAILED:
			viewName="othersAllergies/othersAllergiesForm";
			break;
		default :
			viewName="redirect:/othersAllergies/othersAllergies.do?allId="+allergy.getAllId();
		}
		return viewName;
	}
	
}
