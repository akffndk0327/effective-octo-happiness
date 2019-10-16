package kr.or.ddit.prod.controller;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.common.hints.UpdateHint;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod/prodUpdate.do")
public class ProdUpdateController {
	@Inject
	IProdService service ;
	@Inject //이렇게 쓰면 알아서 컨테이너 객체 주입
	WebApplicationContext container;
	ServletContext application ;

	@RequestMapping(method = RequestMethod.GET)
	public String updateForm(Model model,
			@RequestParam(name="what",required=true)String prod_id){
//		String prod_id = req.getParameter("what");
//		if (StringUtils.isBlank(prod_id)) {
//			resp.sendError(400);
//			return null;
//		}
		ProdVO prod = service.retrieveProd(prod_id);
		model.addAttribute("prod", prod);
		return "prod/prodForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String Update(Model model, @Validated(UpdateHint.class) @ModelAttribute("prod") ProdVO prod, Errors errors){
//			@RequestPart(required=false, name="prod_image") MultipartFile partWrapper,
		
		// 분석 검증
//		Map<String, String> errors = new HashMap<String, String>();
//		model.addAttribute("errors", errors);
		boolean valid = !errors.hasErrors();
		
		String viewName = null;
		String message = null;
		if(valid) { //빈항목 잇나없나
			//1.서비스 받기.2. 메서드 호출, 3 통과
			ServiceResult result = service.modifyProd(prod);
			switch (result) {
			case OK:
				message = "수정성공";
				viewName = "redirect:/prod/prodView.do?what="+prod.getProd_id();
//				viewName = "redirect:/prod/"+prod.getProd_id(); //경로의 일부분으로 상세코드 보여짐 
				break;
			default:
				message ="서버오류";
				viewName ="prod/prodForm";
				break;
			}
		}else {
			viewName = "prod/prodForm";
			
		}
		model.addAttribute("message", message);
		return viewName;
		
	}

}
