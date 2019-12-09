package kr.or.ddit.search.controller;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * @author 이진희
 * @since 2019. 11. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 22.      이진희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */

import kr.or.ddit.search.service.ISearchService;
import kr.or.ddit.vo.AllVO;
import kr.or.ddit.vo.Resource2VO;
import kr.or.ddit.vo.SearchVO;
@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Inject
	ISearchService service;
	
	@RequestMapping("searchList.do")
	public String view(@RequestParam(required=false) String prdlstnm,Model model,
						@RequestParam(required=false) String similerWords
			) {
		if(StringUtils.isNotBlank(prdlstnm)) {
			List<AllVO> list = service.selectAllList(prdlstnm);
			List<Resource2VO> resoureList = service.selectURL();
			
			SearchVO searchVO = new SearchVO();
			searchVO.setSearchName(prdlstnm);
			service.insertKeyWord(searchVO);
			
			model.addAttribute("list", list);
			model.addAttribute("resoureList", resoureList);
			model.addAttribute("similerWords", similerWords);
		}
		
		return "search/searchList";
	}
	
	

}
