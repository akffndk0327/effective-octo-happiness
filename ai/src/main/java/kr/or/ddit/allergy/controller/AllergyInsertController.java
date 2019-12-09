package kr.or.ddit.allergy.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.allergy.service.IAllergyService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.AllergyVO;

@Controller
@RequestMapping("/allergy/allergyInsert.do")
public class AllergyInsertController {
	@Inject
	IAllergyService service;
	
	//작성화면 띄우기 
	@RequestMapping(method=RequestMethod.GET)
	public String boardForm(){ 
		return "allergy/chemiallergyForm";
	}
	//받아오는거
		@RequestMapping(method=RequestMethod.POST)
		public String insert(
				@ModelAttribute("allergy") AllergyVO allergy
				, Errors errors, Model model
				) {
			boolean valid = !errors.hasErrors();
			String viewName = null;
			String message = null;
			if (valid) {
				ServiceResult result = service.insertAllergy(allergy);
				switch (result) {
				case OK:
//					- OK   : redirect 
					viewName = "redirect:/allergy/allergyView.do?what="+allergy.getAllId();
					break;
				default:
					message = "서버 오류";
					viewName = "allergy/chemiallergyForm";
					break;
				}

			} else {
				viewName = "allergy/chemiallergyForm";
			}
			
			model.addAttribute("message", message);
			return viewName;
		}
}
