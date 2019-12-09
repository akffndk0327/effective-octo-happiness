package kr.or.ddit.recipeboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
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

@Controller
public class RecipeDeleteController {
	@Inject
	IRecipeBoardService service;
	
	@RequestMapping(value="recipe/recipeDelete.do",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, String> recipeDelete(@RequestParam("what") int recipeNo,Model model) {
		RecipeBoardVO recipe = new RecipeBoardVO();
		recipe.setRecipeNo(recipeNo);
		ServiceResult result = service.deleteRecipe(recipe);
		String status = null;
		if (result.equals(ServiceResult.OK)) {
			status = "ok";
		}else {
			status ="fail";
		}
		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", status);
		return map;
	}
	
	@RequestMapping(value = "recipe/recipeDelete.do",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public int recipeDeleteList(@RequestParam(required = true) String[] deleteList) {
		for (int i = 0; i < deleteList.length; i++) {
			RecipeBoardVO vo = new RecipeBoardVO();
			vo.setRecipeNo(Integer.parseInt(deleteList[i]));
			service.deleteRecipe(vo);
			System.out.println(deleteList[i]);
		}
		return 0;
	}
	
}
