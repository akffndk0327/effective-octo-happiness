package kr.or.ddit.dailysupplies.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.chemicals.service.IChemicalsService;
import kr.or.ddit.common.hints.InsertHint;
import kr.or.ddit.dailysupplies.service.IDailySupplyService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.BioCheProVO;

@Controller
@RequestMapping("/dailysupply/dsInsert.do")
public class DailySupplyInsertController {
	@Inject
	IDailySupplyService service;
	@Inject
	IChemicalsService cheService;
	
	
	//등록 화면 
	@RequestMapping(method=RequestMethod.GET)
	public String getForm() {
		return "supplydaily/supplydailInsertForm";
	}


	//글작성하고 폼 받아오기
	@RequestMapping(method=RequestMethod.POST)
	public String insertForm(
			@Validated(InsertHint.class)@ModelAttribute("biochepro") BioCheProVO biochepro,
			Errors errors, Model model) throws IOException {
		//blob저장
		biochepro.setBio_img(biochepro.getBioImg());
		
		biochepro.setBioCheCom(biochepro.getBioCheName());
		boolean valid = !errors.hasErrors(); 
		String viewName = null;
		String message = null;
		if (valid) {
			ServiceResult result = service.insertDS(biochepro);
			switch (result) {
			case FAILED:
				message = "서버 오류";
				viewName = "supplydaily/supplydailInsertForm";
				break;
			default:
//				- OK   : redirect -> welcome page
				message = "등록  성공";
				viewName = "redirect:/dailysupply/dsView.do?dsNo="+biochepro.getBioCheId();
				break;
			}

		} else {
			viewName = "supplydaily/supplydailInsertForm";
		}

//		model.addAttribute("message", message);
		return viewName;

	}
}
