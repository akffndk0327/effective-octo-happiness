package kr.or.ddit.recipereply.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.recipereply.service.IReplyService;
import kr.or.ddit.vo.ReplyVO;

@Controller
public class RecipeReplyController {
	@Inject
	IReplyService service;

	@ResponseBody
	@RequestMapping(value = "recipe/replyInsert.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map replyInsert(@RequestParam String cont,
			@RequestParam String loginId,
			@RequestParam(required=false) String parent,
			@RequestParam int recipeNo,ModelAndView mav) {

		ReplyVO reply=new ReplyVO();
		reply.setMemId(loginId);
		reply.setRepCont(cont);
		reply.setRecipeNo(recipeNo);
		if(StringUtils.isNotBlank(parent)) {
			if(!parent.contains("btn"))
			reply.setParRep(parent);
		}
		System.out.println(reply.getParRep());
		
		ServiceResult result = service.insertReply(reply);
		
		Map<String, String> map = new HashMap<>();
		String status=null;
		if(result.equals(ServiceResult.OK)) {
			status="OK";
		}else {
			status="FAIL";
		}
		System.out.println(status);
		map.put("status", status);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "recipe/replyDelete.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map replyDelete(@RequestParam String repNo, HttpServletResponse response) {
        response.setContentType("application/json");

		ReplyVO reply=new ReplyVO();
		reply.setRepId(repNo);
		int count=service.selectReply(reply);
		
		ServiceResult result;
		if(count>0) {
			result = service.deleteReply(reply);			
		}else {
			result=service.eliminateReply(reply);
		}
		
		
		Map<String, String> map = new HashMap<>();
		String status=null;
		if(result.equals(ServiceResult.OK)) {
			status="OK";
		}else {
			status="FAIL";
		}
		System.out.println(status);
		map.put("status", status);
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "recipe/replyUpdate.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map replyUpdate(@RequestParam String recipeNo,@RequestParam String cont) {
		ReplyVO reply=new ReplyVO();
		reply.setRepId(recipeNo);
		reply.setRepCont(cont);
		ServiceResult result = service.updateReply(reply);
		
		Map<String, String> map = new HashMap<>();
		String status=null;
		if(result.equals(ServiceResult.OK)) {
			status="OK";
		}else {
			status="FAIL";
		}
		map.put("status", status);
		return map;
	}
	
	
}
