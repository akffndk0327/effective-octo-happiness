package kr.or.ddit.advertise.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.advertise.service.IAdvertiseService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.AdvertiseVO;

@Controller
@RequestMapping("/advertise")
public class AdvertiseInsertController {
	@Inject
	IAdvertiseService service;

	
	@RequestMapping(value="adInsert.do",method = RequestMethod.GET)
	public String adForm() { // 매개변슈.....................
		
		return "advertise/adForm";
	}

	@RequestMapping(value="adInsert.do",method = RequestMethod.POST)
	public String adinsert(@ModelAttribute("advertise") AdvertiseVO advertise, 
			 Model model) throws IOException {
		String viewName = null;
		String message = null;
		String filename=null;
			ServiceResult result = service.insertAd(advertise);
			switch (result) {
			case OK:
				message = "등록 성공";
				filename = service.processAttatch(advertise);
				viewName = "redirect:/advertise/adList.do" ; 
				break;
			default:
				message = "서버 오류";
				viewName = "advertise/adForm" ; 
				break;
			}
		model.addAttribute("filename", filename);
		model.addAttribute("message",message);
		return viewName;
	}
	
	
	
	
	//관리자가 승인버튼 누르면 이미지 append시키기 !
	@RequestMapping(value="adimgappend.do",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Model imgAppend(@RequestParam(name = "url", required=true) String url,
			@RequestParam(name = "position", required=true) String position,
			@RequestParam(name = "board", required=true) String board,
			@RequestParam(name = "adId", required=false) int adId,
			Model model
			) {
		model.addAttribute("url",url);
		model.addAttribute("position",position);
		model.addAttribute("board",board);
		model.addAttribute("adId",adId);
		return null;
		
	}
	

}
