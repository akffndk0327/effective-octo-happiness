package kr.or.ddit.newsboard.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.newsboard.service.INewsService;

@Controller
public class NewsDeleteController {
	@Inject
	INewsService service;
	
	@RequestMapping(value="/news/newsDelete.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public int newsDelete(@RequestParam(required=true) int newsNo) {
		int cnt = service.removeNews(newsNo);
		return cnt;
	}
	
}
