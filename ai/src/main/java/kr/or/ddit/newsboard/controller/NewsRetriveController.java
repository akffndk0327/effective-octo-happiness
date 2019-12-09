package kr.or.ddit.newsboard.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.newsboard.service.INewsService;
import kr.or.ddit.vo.NewsVO;
import kr.or.ddit.vo.PagingInfoVO;

@Controller
@RequestMapping("/news")
public class NewsRetriveController {
	@Inject
	INewsService service;

	@RequestMapping("newsList.do")
	public String newsList( 
		@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
		Model model) {
		PagingInfoVO<NewsVO> pagingVO = new PagingInfoVO<NewsVO>(10, 5);
		int totalRecord = service.retrievBoardCount(pagingVO);
		
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<NewsVO> list = service.retrieveBoardList(pagingVO);

		
		pagingVO.setDataList(list);

		model.addAttribute("pagingVO", pagingVO);
		return "news/newsList";

	}
	
	
	
	
//	@RequestMapping(value="boardList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public PagingInfoVO listForAjax(
//			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
//			, Model model
//			){
//		newsList(currentPage, model);
//		return (PagingInfoVO<MemberVO>) model.asMap().get("pagingVO");
//	}
	
	
	@RequestMapping("/newsView.do")
	public ModelAndView newsView(@RequestParam(required = true) int newsNo){
		int cnt = service.modifyNewsHit(newsNo);
		NewsVO news = service.retrieveNews(newsNo);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("news/newsView");
		mav.addObject("news", news);
		return mav;
	}
	

}
