package kr.or.ddit.board.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.common.hints.UpdateHint;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.Board2VO;

@Controller
@RequestMapping(value="boardUpdate.do")
public class BoardUpdateController {
	@Inject
	IBoardService service;
	
	@RequestMapping(method=RequestMethod.GET) //기존 게시글 정보갖고있는 form 뜨우기 
	public String boardForm(@RequestParam(required=true)int what,Model model) {
//		String what = req.getParameter("what");
//		if(StringUtils.isBlank(what)) {
//			resp.sendError(400);
//			return null;
//		}
		Board2VO board = 
				service.retrieveBoard(new Board2VO((what)));
		model.addAttribute("board", board);
		return "board/boardForm" ;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String insert(@Validated(UpdateHint.class)@ModelAttribute("board")Board2VO board,
			Errors errors,
			Model model) {
//		Board2VO board = new Board2VO();
//		req.setAttribute("board", board);
//		try {
//			BeanUtils.populate(board, req.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			throw new RuntimeException(e);
//		}
		
		
//		Map<String, String> errors = new HashMap<String, String>();
//		req.setAttribute("errors", errors);
		
//		boolean valid = validate(board, errors);
		boolean valid = !errors.hasErrors();
		
		//첨부파일 
//		if(req instanceof MultipartRequestWrapper) {
//			PartWrapper[] bo_file = ((MultipartRequestWrapper) req).getPartWrappers("bo_file");
//			board.setBo_file(bo_file); //domain layer로 코드 보내니까 중복되는거 줄여 
//		}
		
		String viewName = null;
		String message = null;
		if (valid) {
			ServiceResult result = service.modifyBoard(board);
			switch (result) {
			case OK:
//				- OK   : redirect 
				viewName = "redirect:/board/boardView.do?what="+board.getBo_no();
				break;
			case INVALIDPASSWORD:
				message = "비번 오류";
				viewName = "board/boardForm";
				break;
			default:
				message = "서버 오류";
				viewName = "board/boardForm";
				break;
			}

		} else {
			viewName = "board/boardForm";
		}
		
		model.addAttribute("message", message);
		
		return viewName;
	}
	
}

