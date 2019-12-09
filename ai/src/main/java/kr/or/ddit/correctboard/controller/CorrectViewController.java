package kr.or.ddit.correctboard.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.correctboard.service.ICorrectBoardService;
import kr.or.ddit.vo.CodeVO;
import kr.or.ddit.vo.CorrectBoardVO;
import kr.or.ddit.vo.PagingInfoVO;

@Controller
@RequestMapping("/correct")
public class CorrectViewController {
	
	@Inject
	ICorrectBoardService service;
	
	@RequestMapping("correctList.do")
	public String listView(
			@RequestParam(name="correctType",required=true, defaultValue="C01" ) String correctType,
			@RequestParam(name="resId",required=false) String resId,
			@RequestParam(name="page",required=false,defaultValue="1") int currentPage
			,Model model
			) {
		
		PagingInfoVO<CorrectBoardVO> pagingVO = new PagingInfoVO<>(10, 5);
		CorrectBoardVO searchVO = new CorrectBoardVO();

		pagingVO.setCorrectType(correctType);
		pagingVO.setResId(resId);
		
		int totalRecord = service.selectCorrectCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<CorrectBoardVO> list = service.selectCorrectList(pagingVO);
		pagingVO.setDataList(list);
		
		model.addAttribute("pagingVO", pagingVO);
		
		List<CodeVO> codeList = service.selectCodeList();
		model.addAttribute("codeList",codeList);
		
		List<CodeVO> boardList = service.selectBoardList();
		model.addAttribute("boardList", boardList);
		
		
		return "correct/correctList";
	}
	
	@RequestMapping(value="correctList.do",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingInfoVO listForAjax(
			@RequestParam(name="correctType",required=true, defaultValue="C01") String correctType,
			@RequestParam(name="resId",required=false) String resId,
			@RequestParam(name="page",required=false,defaultValue="1") int currentPage
			,Model model
			) {
		listView(correctType,resId,currentPage,model);
		return (PagingInfoVO<CorrectBoardVO>) model.asMap().get("pagingVO");
	}
	
	
	
	@RequestMapping("correctView.do")
	public String correctView(@RequestParam(required=true) int correctNo,Model model) {
		CorrectBoardVO corr = new CorrectBoardVO();
		corr.setCorrectNo(correctNo);
		
		CorrectBoardVO correct = service.selectCorrect(corr);
		model.addAttribute("correct", correct);
		
		return "correct/correctView";
	}

}
