package kr.or.ddit.allergy.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.allergy.service.IAllergyService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.AllergyVO;

@Controller
@RequestMapping("/allergy/allergyDelete.do")
public class AllergyDeleteController {
	@Inject
	IAllergyService service;
	
	 
		@RequestMapping(method=RequestMethod.GET)
		public String allergyDelete(@RequestParam(required=true) String what, RedirectAttributes redirect){
			AllergyVO all = new AllergyVO();
			ServiceResult result = service.removeAllergy(new AllergyVO(what));
			String viewName = "redirect:/allergy/allergyList.do";
			String message = null;
			switch (result) {
				case FAILED:
					message = "서버 오류";
					viewName = "redirect:/allergy/allergyView.do?what="+all.getAllId();
					redirect.addFlashAttribute("fail", message);
					break;
				default:
					viewName = "redirect:/allergy/allergyList.do";
					redirect.addFlashAttribute("success", message);
					break;
			}
			return viewName;
		}
}
