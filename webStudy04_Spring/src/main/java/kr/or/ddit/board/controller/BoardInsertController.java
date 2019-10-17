package kr.or.ddit.board.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.Board2VO;

@Controller
@RequestMapping("/board")
public class BoardInsertController {
	@Inject
	IBoardService service ;
	
	@RequestMapping("boardInsert.do")
	public String boardForm() {
		return "board/boardForm";
	}
	 @RequestMapping(value="boardInsert.do", method=RequestMethod.POST)
	public String insert(
			@ModelAttribute("board")Board2VO board,
			Errors errors,
			Model model	) {
		/*Board2VO board = new Board2VO();
		model.addAttribute("board", board);
		try {
			BeanUtils.populate(board, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}*/
		
		
/*		Map<String, String> errors = new HashMap<String, String>();
		Map<String, Object> errors2 = new HashMap<String, String>();
		errors.put("reply",reply);
		model.addAttribute("errors", errors);*/
		
//		boolean valid = validate(board, errors);
		boolean valid = !errors.hasErrors();
		
/*		이미지
		if(req instanceof MultipartRequestWrapper) {
			PartWrapper[] bo_files = ((MultipartRequestWrapper) req).getPartWrappers("bo_file");
			//attachVO 만들기?
			board.setBo_file(bo_files);
		}
		미들티어에 넣어
		*/ 
		
		
		String viewName = null;
		String message = null;
		 if (valid) {
	         ServiceResult result = service.createBoard(board);
	         System.out.println(result);
	         switch (result) {
	         case OK:
//	            - OK   : redirect 
	            viewName = "redirect:/board/boardView.do?what="+board.getBo_no();
	            break;
	         default:
	            message = "서버 오류";
	            viewName = "board/boardForm";
	            break;
	         }

	      } else {
	    	  System.out.println("hi!");
	         viewName = "board/boardForm";
	      }
	      
	      model.addAttribute("message", message);
	      
	      return viewName;
	   }

	}



