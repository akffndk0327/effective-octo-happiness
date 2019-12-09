package kr.or.ddit.recipeboard.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.jboss.logging.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import kr.or.ddit.recipeboard.service.IRecipeBoardService;
import kr.or.ddit.recipereply.service.IReplyService;
import kr.or.ddit.vo.Like2VO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.RecipeBoardVO;
import kr.or.ddit.vo.RecipeTypeVO;
import kr.or.ddit.vo.ReplyVO;

/**
 * @author 이유진
 * @since 2019. 11. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 4.      이유진       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 *      </pre>
 */

@Controller
public class RecipeRetrieveController {

	@Inject
	IRecipeBoardService service;

	@Inject
	IReplyService rservice;

	@RequestMapping("/recipe/recipeList.do")
	public String RecipeList(@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false) String searchType, @RequestParam(required = false) String searchWord,
			Model model, Authentication loginId) {
		PagingInfoVO<RecipeBoardVO> pagingVO = new PagingInfoVO<RecipeBoardVO>(10, 5);
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);

		RecipeBoardVO searchVO = new RecipeBoardVO();
		pagingVO.setSearchVO(searchVO);
		pagingVO.setSearchMap(searchMap);

		Logger l = Logger.getLogger(RecipeRetrieveController.class);

		int totalRecord = service.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<RecipeBoardVO> list = service.retrieveBoardList(pagingVO);
		List<RecipeTypeVO> reType = service.selectAllRecipeType();
		pagingVO.setDataList(list);

		for (int i = 0; i < list.size(); i++) {
			String str = pagingVO.getDataList().get(i).getRecipeContent();
			if (str.contains("/ai/recipeImages/")) {
				if (str.indexOf("style=") < str.indexOf("src=")) {
					String target = "/ai/";
					int target_num = str.indexOf(target);
					String result = str.substring(target_num, (str.substring(target_num).indexOf("\"") + target_num));
					list.get(i).setRecipeContent(result);
				} else {
					String target = "src=";
					int target_num = str.indexOf(target);
					String result = str.substring(target_num + 4,
							(str.substring(target_num).indexOf("style") + target_num));
					result = result.substring(1, result.length() - 2);
					list.get(i).setRecipeContent(result);
				}
			} else if (str.contains("http://") || str.contains("https://") || str.contains("data:")) {
				String target = "src=";

				int target_num = str.indexOf(target);

				if (!str.contains("alt=")) {
					String start = str.substring(0, target_num + 5);
					str = str.substring(target_num + 5);

					int end = str.indexOf("\"");
					String last = str.substring(end);
					str = str.substring(0, end);

					str = start.concat(str.concat("\" alt=\"").concat(last));
				}
				System.out.println("@ " + str);
				String result = str.substring(target_num + 4, (str.substring(target_num).indexOf("alt") + target_num));
				result = result.substring(1, result.length() - 2);
				System.out.println(result);
				list.get(i).setRecipeContent(result);
			} else {
				list.get(i).setRecipeContent("");
			}
			System.out.println();
		}
		String id = null;
		if (loginId != null) {
			id = loginId.getName();
		}
		List<Like2VO> like = service.selectLike(id);

		if (loginId != null) {
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < like.size(); j++) {
					if (list.get(i).getRecipeNo() == like.get(j).getRecipeNo()) {
						list.get(i).setLike("Y");
					}
				}
			}
		}

		pagingVO.setDataList(list);
		model.addAttribute("pagingVO", pagingVO);
		model.addAttribute("like", like);
		model.addAttribute("reType", reType);
		return "recipe/recipeList";

	}

	@RequestMapping("/recipe/recipeSearch.do")
	public String RecipeSerach(@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false) String searchType, @RequestParam(required = false) String searchWord,
			Model model) {
		System.out.println(searchType);
		System.out.println(searchWord);
		PagingInfoVO<RecipeBoardVO> pagingVO = new PagingInfoVO<RecipeBoardVO>(10, 5);
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchWord", searchWord);

		RecipeBoardVO searchVO = new RecipeBoardVO();
		searchVO.setRecipeType(searchType);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setSearchMap(searchMap);

		int totalRecord = service.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<RecipeBoardVO> list = service.retrieveBoardList(pagingVO);
		List<RecipeTypeVO> reType = service.selectAllRecipeType();
		pagingVO.setDataList(list);

		for (int i = 0; i < list.size(); i++) {
			String str = pagingVO.getDataList().get(i).getRecipeContent();
			str.length();
			if (str.contains("/ai/recipeImages/")) {
				if (str.indexOf("style=") < str.indexOf("src=")) {
					String target = "/ai/";
					int target_num = str.indexOf(target);
					String result = str.substring(target_num, (str.substring(target_num).indexOf("\"") + target_num));
					list.get(i).setRecipeContent(result);
				} else {
					String target = "src=";
					int target_num = str.indexOf(target);
					String result = str.substring(target_num + 4,
							(str.substring(target_num).indexOf("style") + target_num));
					result = result.substring(1, result.length() - 2);
					list.get(i).setRecipeContent(result);
				}
			} else if (str.contains("https://") || str.contains("data:")) {
				String target = "src=";
				int target_num = str.indexOf(target);
				String result = str.substring(target_num + 4, (str.substring(target_num).indexOf("alt") + target_num));
				result = result.substring(1, result.length() - 2);
				list.get(i).setRecipeContent(result);
			} else {
				list.get(i).setRecipeContent("");
			}
		}

		pagingVO.setDataList(list);
		model.addAttribute("pagingVO", pagingVO);
		model.addAttribute("reType", reType);
		return "recipe/recipeList";

	}

	@RequestMapping("recipe/recipeView.do")
	public String recipeView(@RequestParam(required = true) int what, Model model,
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage, Authentication loginId)
			throws JsonParseException, JsonMappingException, IOException {
		RecipeBoardVO vo = new RecipeBoardVO();
		vo.setRecipeNo(what);
		String id = null;
		if (loginId != null) {
			id = loginId.getName();
		}
		vo.setMemId(id);
		RecipeBoardVO recipe = service.retrieveBoard(vo);
		model.addAttribute("recipe", recipe);

		PagingInfoVO<ReplyVO> pagingVO = new PagingInfoVO<ReplyVO>(10, 5);

		int totalRecord = rservice.selectReplyCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		pagingVO.setPk(what + "");
		List<ReplyVO> list = rservice.retrieveReply(pagingVO);
		pagingVO.setDataList(list);
		model.addAttribute("pagingVO", pagingVO);

		return "recipe/recipeView";
	}

}
