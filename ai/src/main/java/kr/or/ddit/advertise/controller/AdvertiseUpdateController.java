package kr.or.ddit.advertise.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.advertise.service.IAdvertiseService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.AdvertiseVO;

@Controller
public class AdvertiseUpdateController {
	@Inject
	IAdvertiseService service;
	
	//광고 등록 및 수정할 때 폼 보내기 
	@RequestMapping(value="/advertise/admemUpdate.do",method=RequestMethod.GET)
	public String admemForm(
			@RequestParam(required=true) int what
			, Model model
			){
		AdvertiseVO advertise = 
				service.retrieveAd(new AdvertiseVO(what));
		model.addAttribute("advertise",advertise);
		return "advertise/adupdateForm";
	}
	
	//회원이 수정 할때  받기 
	@ResponseBody
	@RequestMapping(value="/advertise/admemUpdate.do",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String adUpdate(@ModelAttribute("advertise") AdvertiseVO advertise, BindingResult binding, Model model ) {
		String message = null;
		String viewname = null;
		ServiceResult result = service.modifyAd(advertise); 
		switch (result) {
		case OK:
			message="OK";
			viewname = "redirect:/advertise/adView.do?adNo="+advertise.getAdId();
			break;
		default:
			message = "서버 오류";
			viewname ="/advertise/admemUpdatefrom";
			break;
		}
	return viewname;
		
	}
	
	//광고 등록 및 수정할 때 폼 보내기 
		@RequestMapping(value="/advertise/adUpdate.do",method=RequestMethod.GET)
		public String adForm(
				@RequestParam(required=true) int what
				, Model model
				){
			AdvertiseVO advertise = 
					service.retrieveAd(new AdvertiseVO(what));
			model.addAttribute("advertise",advertise);
			return "advertise/adForm";
		}
	
	//관리자가 승인 반려할때 업데이트
	@ResponseBody
	@RequestMapping(value="/advertise/adUpdate.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String adAdminUpdate(
			@Valid@RequestParam int adId, @RequestParam String selected
			,@RequestParam(required=false) String adRep
			, Model model
			){
		AdvertiseVO advertise = new AdvertiseVO();
		advertise.setAdId(adId);
		
		if(selected.equals("0")) {
			advertise.setAdApprove("a1");
		}else {
			advertise.setAdApprove("a0");
			advertise.setAdRep(adRep);
		}
		
		String message = null;
		String viwename= null;
			ServiceResult result = service.updateApprove(advertise);
			switch (result) {
			case OK:
				message="OK";
				viwename = "redirect:/advertise/adViweAdmin.do?what="+advertise.getAdId();
				break;
			default:
				message = "서버 오류";
				viwename ="redirect:/advertise/adUpdate.do";
						
				break;
			}

		
		return message;
	}
}
