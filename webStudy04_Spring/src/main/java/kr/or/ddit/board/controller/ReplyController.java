package kr.or.ddit.board.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.common.hints.InsertHint;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.Reply2VO;
//1021 RESTful 1.requestMapping 삭제 ->getMapping, deleteMapping,...
@Controller
@RequestMapping(value = "/board/{bo_no}/reply", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ReplyController {
	@Inject
	IReplyService service ;
	
	@GetMapping
	@ResponseBody //마샬링 하게 함. -> 마임설정필요
	public PagingInfoVO<Reply2VO> list(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(required=true) int bo_no						
			) {
		/*String pageParam = req.getParameter("page");
		String bo_noParam = req.getParameter("bo_no");
		if(StringUtils.isBlank(bo_noParam)) {
			resp.sendError(400);
			return null;
		}
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		*/
		PagingInfoVO<Reply2VO> pagingVO = 
					new PagingInfoVO<Reply2VO>(4, 5);
		pagingVO.setSearchVO(new Reply2VO((bo_no)));
		int totalRecord = service.retriveReplyCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<Reply2VO> list = service.retriveReplyList(pagingVO);
		pagingVO.setDataList(list);
		/*
		resp.setContentType("application/json;charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		try(
			PrintWriter out = resp.getWriter();	
		){
			mapper.writeValue(out, pagingVO);
		}*/
		return pagingVO;
	}
	
	//domain layer에 검증 코드 만듬.
//	아래 힌트 파라미터에서 넘어온게 알맞는거에 적요오딤 
	/*public static interface DefaultHint{}
	public static interface InsertHint extends DefaultHint{}
	public static interface UpdateHint extends DefaultHint{}
	public static interface DeleteHint extends DefaultHint{}
	
	private boolean validate(Reply2VO reply, Map<String, Object> errors, Class<?> hint) {
		boolean valid = true;
		// 신규, 수정, 삭제시 공통 검증
		if(DefaultHint.class.isAssignableFrom(hint)) {
			if(reply.getBo_no()==null || reply.getBo_no()<=0) {
				errors.put("bo_no", "글번호 누락");
				valid = false;
			}
			if(StringUtils.isBlank(reply.getRep_pass())) {
				errors.put("rep_pass", "비밀번호 누락");
				valid = false;
			}
		}
		// 신규 검증
		if(InsertHint.class.equals(hint)) {
			if(StringUtils.isBlank(reply.getRep_writer())) {
				errors.put("rep_writer", "작성자 누락");
				valid = false;
			}
			if(StringUtils.isBlank(reply.getRep_ip())) {
				errors.put("rep_ip", "아이피 누락");
				valid = false;
			}
			if(StringUtils.length(reply.getRep_content()) > 200) {
				errors.put("rep_content", "댓글 길이 200자 제한");
				valid = false;
			}
		}
		// 수정 검증
		if(UpdateHint.class.equals(hint)) {
			if(reply.getRep_no()==null || reply.getRep_no()<=0) {
				errors.put("rep_no", "댓글번호 누락");
				valid = false;
			}
			if(StringUtils.length(reply.getRep_content()) > 200) {
				errors.put("rep_content", "댓글 길이 200자 제한");
				valid = false;
			}
		}
		// 삭제 검증
		if(DeleteHint.class.equals(hint)) {
			if(reply.getRep_no()==null || reply.getRep_no()<=0) {
				errors.put("rep_no", "댓글번호 누락");
				valid = false;
			}
		}
		return valid;
	}
	*/
	private String redirectPtrn = "redirect:/board/{bo_no}/reply?bo_no=%s&page=%s";	
	
	@PostMapping
//	@RequestMapping(value = "replyInsert.do", method = RequestMethod.POST)
//	@ResponseBody
	public String insert( //string->object->string(viewName필요)
			@RequestParam(required=false, defaultValue="1") int page
			, @ModelAttribute("reply") @Validated(InsertHint.class) Reply2VO reply
			, Errors errors, HttpServletResponse resp,
			Model model
			) throws IOException{
		boolean valid = !errors.hasErrors();
		String viewName = null;
		if(valid) {
			ServiceResult result = service.createReply(reply);
			if(ServiceResult.OK.equals(result)) {
				viewName = String.format(redirectPtrn, reply.getBo_no(), page);
			}else {
				model.addAttribute("failed", true);
				model.addAttribute("message", "저장 실패");
			}	
		}else {
			model.addAttribute("failed", true);
			model.addAttribute("message", "입력 데이터 확인 필요");

		}
		/*
		if(messageMap.size()>1) {
			resp.setContentType("application/json;charset=UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			try(
				PrintWriter out = resp.getWriter();	
			){
				mapper.writeValue(out, messageMap);
			}
			*/
		if(model.asMap().size()>2) { //마샬링 방법2
//			return messageMap;
			return "jsonView"; //논리적인viewname이라고 생각함 -> 여기서 마샬링?! 대신 servlet-context에서  하기 컨테이너에 등록되 빈..
		}else {
//			return list(page, reply.getBo_no()); //redirect아니여도 pageVO내보낼수있음  
			return viewName; //redirect아니여도 pageVO내보낼수있음  
		}
			
//		return viewName; //결과내보내기위한 redirect else 쓸떄 지워야함 
	}
	
	@PutMapping
//	@RequestMapping(value="replyUpdate.do", method = RequestMethod.POST)
	@ResponseBody //+return messageMap
	public Object update(
			@PathVariable(required=true)String board_type,
			@RequestParam(required=false, defaultValue="1") int page
			, @Validated(InsertHint.class) Reply2VO reply
			, Errors errors, HttpServletResponse resp
			) throws IOException{
		/*String pageParam = req.getParameter("page");
		Reply2VO reply = new Reply2VO();
		try {
			BeanUtils.populate(reply, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		*/
		Map<String, Object> messageMap = new HashMap<>();
		messageMap.put("reply", reply);
//		boolean valid = validate(reply, messageMap, UpdateHint.class);
		boolean valid = !errors.hasErrors();
		String viewName = null;
		if(valid) {
			ServiceResult result = service.modifyReply(reply);
			if(ServiceResult.OK.equals(result)) {
				viewName = String.format(redirectPtrn, reply.getBo_no(), page);
			}else {
				messageMap.put("failed", true);
				messageMap.put("message", "비번 오류");
			}	
		}else {
			messageMap.put("failed", true);
			messageMap.put("message", "입력 데이터 확인 필요");

		}
		if(messageMap.size()>1) {
			return messageMap;
		}else {
			return list(page, reply.getBo_no());
		}
//		return viewName;
	}
	
	@DeleteMapping
//	@RequestMapping(value="replyDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public Object delete(
			@RequestParam(required=false, defaultValue="1") int page
			, @Validated(InsertHint.class) Reply2VO reply
			, Errors errors, HttpServletResponse resp
			) throws IOException{
		/*String pageParam = req.getParameter("page");
		Reply2VO reply = new Reply2VO();
		try {
			BeanUtils.populate(reply, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}*/
		Map<String, Object> messageMap = new HashMap<>();
		messageMap.put("reply", reply);
//		boolean valid = validate(reply, messageMap, DeleteHint.class);
		boolean valid = !errors.hasErrors();
		String viewName = null;
		if(valid) {
			ServiceResult result = service.removeReply(reply);
			if(ServiceResult.OK.equals(result)) {
				viewName = String.format(redirectPtrn, reply.getBo_no(), page);
			}else {
				messageMap.put("failed", true);
				messageMap.put("message", "비번 오류");
			}	
		}else {
			messageMap.put("failed", true);
			messageMap.put("message", "입력 데이터 확인 필요");

		}
		if(messageMap.size()>1) {
			return messageMap;
		}else {
			return list(page, reply.getBo_no());
		}
//		return viewName;
	}
	
	

}
