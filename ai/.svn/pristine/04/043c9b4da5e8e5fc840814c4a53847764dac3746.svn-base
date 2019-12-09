package kr.or.ddit.newsboard.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.mail.Multipart;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.newsboard.service.INewsService;
import kr.or.ddit.vo.NewsVO;

@Controller
@RequestMapping("/news/newsUpdate.do")
public class NewsUpdateController {
	@Inject
	INewsService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView newsUpdateForm(@RequestParam(required=true) int newsNo){
		NewsVO news = service.retrieveNews(newsNo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("news",news);
		mav.setViewName("news/newsForm");
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String newsUpdate(@ModelAttribute("news") NewsVO newsVO) throws IOException{
		newsVO.setNewsTitle(newsVO.getNewsTitle());
		newsVO.setNewsContent(newsVO.getNewsContent());
		newsVO.setNewsNo(newsVO.getNewsNo());
		int result = service.modifyNews(newsVO);
		return "redirect:/news/newsView.do?newsNo="+newsVO.getNewsNo();
	}
	
}
