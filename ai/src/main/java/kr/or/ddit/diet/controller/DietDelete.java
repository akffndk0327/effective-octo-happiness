package kr.or.ddit.diet.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.diet.service.IDietService;

@Controller
public class DietDelete {
	@Inject
	IDietService service;
	
	@RequestMapping(value = "diet/dietDelete.do")
	public String DietDelete(@RequestParam(required = true) String monId) {
		int cnt = service.removeDiet(monId);
		return "redirect:/diet/dietList.do";
	}
	
	@RequestMapping(value = "diet/dietDelete.do",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public int DietDeleteList(@RequestParam(required = true) String[] deleteList) {
		int cnt = 0;
		for (int i = 0; i < deleteList.length; i++) {
			cnt = service.removeDiet(deleteList[i]);
			System.out.println(deleteList[i]);
		}
		return cnt;
	}
	
	
}
