package kr.or.ddit.stopsellingfood.controller;

import javax.inject.Inject;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.stopsellingfood.service.IStopSellingService;
import kr.or.ddit.vo.StopSellingFoodVO;

/**
 * @author 이진희
 * @since 2019. 11. 8.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 8.      이진희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/stopSellingFood")
public class StopSellingFoodDeleteController {
	
	@Inject
	IStopSellingService service;
	//삭제하고 화면이동 안됨
	@RequestMapping(value="stopSellingDelete.do",method=RequestMethod.POST)
	public String deleteStopSelling(@RequestParam(required=true,name="stopsellId") int stopsellId
			, RedirectAttributes redirect) {
		
		ServiceResult result = service.deleteStopSelling(stopsellId);
		String viewName=null;
		String message = null;
		
		switch (result) {
		case FAILED:
			viewName="redirect:/stopSellingFood/stopSellingView.do?stopsellId="+stopsellId;
			message = "서버오류";
			redirect.addFlashAttribute("fail", message);
			break;

		case OK: 
			message = "삭제성공";
			viewName="redirect:/stopSellingFood/stopSellingList.do";
			redirect.addFlashAttribute("success", message);
			break;
		}

		return viewName;
	}

}
