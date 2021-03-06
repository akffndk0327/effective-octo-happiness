
package kr.or.ddit.newsboard.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.common.hints.InsertHint;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.newsboard.service.INewsService;
import kr.or.ddit.vo.NewsVO;

//@PreAuthorize(권한이 관리자이면 들어와)
@Controller
@RequestMapping("/news/newsInsert.do")
public class NewsInsertController {
	@Inject
	INewsService service;
	
	//작성화면 띄우기 
	@RequestMapping(method=RequestMethod.GET)
	public String newsInsertForm(){ //매개변수.,.,,????? 고민....
		return "news/newsForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String newsInsert(@ModelAttribute("news") NewsVO newsVO) {
		newsVO.setNewsTitle(newsVO.getNewsTitle());
		newsVO.setNewsContent(newsVO.getNewsContent());
		int result = service.insertNews(newsVO);
		return "redirect:/news/newsList.do";
	}
}
