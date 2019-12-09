package kr.or.ddit.recipeboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.recipeboard.service.IRecipeBoardService;
import kr.or.ddit.vo.RecipeBoardVO;
import kr.or.ddit.vo.RecipeTypeVO;

/**
 * @author 이유진
 * @since 2019. 11. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 5.      이유진       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class RecipeUpdateController {

	@Inject
	IRecipeBoardService service;
	
	@RequestMapping(value="/recipe/recipeUpdate.do",method=RequestMethod.GET)
	public String updateRecipeForm(@RequestParam("what") int recipeNo,Model model) {
		RecipeBoardVO recipe = new RecipeBoardVO();
		recipe.setRecipeNo(recipeNo);
		RecipeBoardVO result = service.retrieveBoard(recipe);
		List<RecipeTypeVO> recipeType=service.selectAllRecipeType();
		model.addAttribute("result",result);
		model.addAttribute("recipeType",recipeType);
		return "recipe/recipeForm";
	}
	
	@RequestMapping(value="/recipe/recipeUpdate.do",method=RequestMethod.POST)
	@ResponseBody
	public Map updateRecipe(@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("recipeNo") int recipeNo,
			@RequestParam("type") String type,Model model ) {
		RecipeBoardVO recipe = new RecipeBoardVO();
		recipe.setRecipeContent(content);
		recipe.setRecipeType(type);
		recipe.setRecipeTitle(title);
		recipe.setRecipeNo(recipeNo);
		ServiceResult result = service.modifyRecipe(recipe);
		String status = null;
		if (result.equals(ServiceResult.OK)) {
			status = "ok";
		}else {
			status ="fail";
		}
		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", status);
		map.put("recipeNo", recipeNo+"");
		
		return map;
		
	}
}
