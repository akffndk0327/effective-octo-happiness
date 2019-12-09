package kr.or.ddit.faq.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.faq.service.IFaqService;
import kr.or.ddit.vo.FaqVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PagingInfoVO;

@Controller
public class FAQRetrieve {
	@Inject IFaqService service;

	@RequestMapping("/faq/faqList.do")
	public String selectFaqList(
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage, 
			Model model
			) {
		PagingInfoVO<FaqVO> pagingVO = new PagingInfoVO<>(5, 5);
		int totalRecord = service.retrieveFaqCount();
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<FaqVO> list = service.retrieveFaqList(pagingVO);
		pagingVO.setDataList(list);
		model.addAttribute("pagingVO", pagingVO);
		return "faq/faqList";
	}
	
	@RequestMapping(value="/faq/faqList.do", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public PagingInfoVO<NoticeVO> selectNoticeListAjax(
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage, 
			Model model
			) {
		selectFaqList(currentPage, model);
		return (PagingInfoVO<NoticeVO>) model.asMap().get("pagingVO");
	}
	
	
}
