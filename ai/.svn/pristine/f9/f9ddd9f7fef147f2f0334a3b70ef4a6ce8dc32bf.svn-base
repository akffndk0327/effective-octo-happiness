package kr.or.ddit.survey.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.survey.service.ISurveyService;
import kr.or.ddit.vo.SurveyVO;

@Controller
public class SurveyDeleteController {
	@Inject
	ISurveyService service;
	
	@RequestMapping(value="survey/surveyDelete.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, String> surveyDelete(@RequestParam String surId) {
		SurveyVO vo = new SurveyVO();
		vo.setSurId(surId);
		
		int cnt = service.deleteSurvey(vo);
		
		Map<String, String> map = new HashMap<>();
		String status=null;
		if(cnt>0) {
			status="OK";
		}else {
			status="FAIL";
		}
		map.put("status", status);
		return map;
	}

}
