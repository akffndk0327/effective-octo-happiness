package kr.or.ddit.advertiseReply.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.advertiseReply.service.IAdReplyService;
import kr.or.ddit.common.hints.DeleteHint;
import kr.or.ddit.common.hints.InsertHint;
import kr.or.ddit.common.hints.UpdateHint;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.AdreplyVO;
import kr.or.ddit.vo.PagingInfoVO;

/**
 * @author 박주연
 * @since 2019. 11. 15.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 15.        박주연		 최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller

public class AdReplyController {
	@Inject
	IAdReplyService service;
	
	@ResponseBody
	public PagingInfoVO<AdreplyVO> list(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @PathVariable(required=true) int adId
			){
		PagingInfoVO<AdreplyVO> pagingVO = 
				new PagingInfoVO<AdreplyVO>(10, 5);
		pagingVO.setSearchVO(new AdreplyVO(adId));
		int totalRecord = service.retrieveReplyCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<AdreplyVO> list = service.retrieveReplyList(pagingVO);
		pagingVO.setDataList(list);

		return pagingVO;
	}
//
	private String redirectPtrn = "redirect:/advertise/adView.do?adNo=%s&page=%s";
//
	@ResponseBody
	@RequestMapping(value ="advertise/adreplyInsert.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String adreplyInsert(
			@RequestParam(required=false, defaultValue="1") int page,
			@RequestParam int adId, //광고글 번호
			@RequestParam String adreplyId, //댓글 번호
			@RequestParam String adreplyCont, //댓글 내용 
			@RequestParam String loginId, 
			@ModelAttribute("reply") @Validated(InsertHint.class) AdreplyVO reply,
			Errors errors, HttpServletResponse resp
			, Model model) throws IOException {
//		//받아올거 로그인한 사람이 관리자이고
//		//한 광고에 대한 게시글 번호 
//		//댓글 관리를 위한 댓글 번호 필요 
		reply.setMemId(loginId);
		reply.setAdreplyCont(adreplyCont);
		reply.setAdreplyId(adreplyId); //댓글 번호 
		reply.setAdId(adId);

		boolean valid = !errors.hasErrors();
		String viewName = null;
		if(valid) {
			ServiceResult result = service.createReply(reply);
			if(ServiceResult.OK.equals(result)) {
				viewName = String.format(redirectPtrn, reply.getAdId());
			}else {
				model.addAttribute("failed", true);
				model.addAttribute("message", "저장 실패");
			}	
		}else {
			model.addAttribute("failed", true);
			model.addAttribute("message", "입력 데이터 확인 필요");

		}
		if(model.asMap().size()>2) {
			return "jsonView";
		}else {
			return viewName;
		}
	}
//
//	@PutMapping
//	@ResponseBody
//	public Object update(
//			@RequestParam(required=false, defaultValue="1") int page,
//			@Validated(UpdateHint.class) AdreplyVO reply
//			, Errors errors, HttpServletResponse resp
//			) throws IOException {
//		Map<String, Object> messageMap = new HashMap<>();
//		messageMap.put("reply", reply);
//		boolean valid = !errors.hasErrors();
//		String viewName = null;
//		if(valid) {
//			ServiceResult result = service.modifyReply(reply);
//			if(ServiceResult.OK.equals(result)) {
//				viewName = String.format(redirectPtrn, reply.getAdId(), page);
//			}else {
//				messageMap.put("failed", true);
//				messageMap.put("message", "서버오류");
//			}
//		}else {
//			messageMap.put("failed", true);
//			messageMap.put("message", "입력 데이터 확인 필요");
//
//		}
//		if(messageMap.size()>1) {
//			return messageMap;
//		}else {
//			return list(page,reply.getAdId());
//		}
//
//	}
//
//
//
//	@DeleteMapping
//	@ResponseBody
//	public Object delete(
//			@RequestParam(required=false, defaultValue="1") int page
//			, @Validated(DeleteHint.class) AdreplyVO reply
//			, Errors errors, HttpServletResponse resp
//			) throws IOException {
//		Map<String, Object> messageMap = new HashMap<>();
//		messageMap.put("reply", reply);
//		boolean valid = !errors.hasErrors();
//		String viewName = null;
//		if(valid) {
//			ServiceResult result = service.removeReply(reply);
//			if(ServiceResult.OK.equals(result)) {
//				viewName = String.format(redirectPtrn, reply.getAdId(), page);
//			}else {
//				messageMap.put("failed", true);
//				messageMap.put("message", "서버 오류");
//			}	
//		}else {
//			messageMap.put("failed", true);
//			messageMap.put("message", "입력 데이터 확인 필요");
//
//		}
//		if(messageMap.size()>1) {
//			return messageMap;
//		}else {
//			return list(page, reply.getAdId());
//		}
//	}

}
