package kr.or.ddit.dailysupplies.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.dailysupplies.service.IDailySupplyService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.BioCheProVO;

@Controller
@RequestMapping("/dailysupply/dsUpdate.do")
public class DailySupplyUpdateController {
	@Inject
	IDailySupplyService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView bioupdateForm(@RequestParam(required=true) String dsNo) {
		BioCheProVO biochepro = service.retrieveDS(new BioCheProVO(dsNo));
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("dsprod",biochepro);
		mav.setViewName("supplydaily/supplydaliyForm");
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String updateForm(@ModelAttribute("dsprod") BioCheProVO biochepro,  
			Errors errors, Model model ) throws IOException {
		boolean valid = !errors.hasErrors();
		String viewName = null;
		String message = null;
		
		//blob저장
		biochepro.setBio_img(biochepro.getBioImg());
		if(valid) {
			ServiceResult result = service.modifyDS(biochepro);
			System.out.println(result);
			switch (result) {
			case OK:
				message = "수정 성공 ";
				viewName = "redirect:/dailysupply/dsView.do?dsNo="+ biochepro.getBioCheId();
				break;
			default:
				message="수정실패";
				viewName = "supplydaily/supplydaliyForm";
				break;
			}
		}else {
			viewName = "supplydaily/supplydaliyForm";
		}
		model.addAttribute("message", message);
		return viewName;
		
	}
	
	
	
	
	
	
}
