package kr.or.ddit.notice.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PagingInfoVO;

@Controller
public class NoticeRetrieve {
	@Inject INoticeService service;

	@RequestMapping("/notice/noticeList.do")
	public String selectNoticeList(
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage, 
			Model model
			) {
		PagingInfoVO<NoticeVO> pagingVO = new PagingInfoVO<>(7, 5);
		int totalRecord = service.retrieveNoticeCount();
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<NoticeVO> list = service.retrieveNoticeList(pagingVO);
		pagingVO.setDataList(list);
		model.addAttribute("pagingVO", pagingVO);
		return "notice/noticeList";
	}
	
	@RequestMapping(value="/notice/noticeList.do", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public PagingInfoVO<NoticeVO> selectNoticeListAjax(
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage, 
			Model model
			) {
		selectNoticeList(currentPage, model);
		return (PagingInfoVO<NoticeVO>) model.asMap().get("pagingVO");
	}
	
	@RequestMapping("/notice/noticeView.do")
	public String selectNoticeView(
			@RequestParam(required=true) int noticeNo, 
			Model model
			) {
		NoticeVO notice = service.retrieveNotice(noticeNo);
		model.addAttribute("notice", notice);
		return "notice/noticeView";
	}
}
