package kr.or.ddit.recipeboard.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.recipeboard.service.IRecipeBoardService;
import kr.or.ddit.vo.Like2VO;
import kr.or.ddit.vo.RecipeAttatchVO;
import kr.or.ddit.vo.RecipeBoardVO;
import kr.or.ddit.vo.RecipeTypeVO;

@Controller
public class RecipeInsertController {
	@Inject
	IRecipeBoardService service;
	@Inject
	WebApplicationContext container;
	@Inject
	ServletContext application;
	
	   @Value("#{appInfo.imgFolderRealPath}")
	   String saveFolderURL;
	   
	   File saveFolder;
	   

	@ResponseBody
	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public String filesave(@RequestParam("file") MultipartFile file) throws IOException {
		String saveURL=null;
		if (file != null) {
			RecipeAttatchVO attatch = new RecipeAttatchVO(file);
			String saveFolderURL1 = "/recipeImages";
//			String saveFolderPath = "D:/A_TeachingMaterial/7.JspSpring/workEgov/ai/src/main/webapp/recipeImages";
//			D:\A_TeachingMaterial\7.JspSpring\workEgov\ai\src\main\webapp\recipeImages
			String saveFolderPath = application.getRealPath(saveFolderURL1);
			File saveFolder = new File(saveFolderPath);
			if(!saveFolder.exists()) saveFolder.mkdirs();
			attatch.saveFile(saveFolder); //이미지 위에주소로 저장해씀
			saveURL = application.getContextPath() + saveFolderURL1 + "/" + attatch.getRecipeattSavename();
			
			System.out.println(saveFolderURL);
			System.out.println(saveFolderPath);
			System.out.println(saveURL);
		}
		return saveURL;
	}
	
	// 게시판 신규 작성으로 이동
		@RequestMapping(value = "recipe/recipeInsert.do", method = RequestMethod.GET)
		public String recipeForm(HttpServletRequest request, HttpServletResponse response, Model model) {
			List<RecipeTypeVO> recipeType = service.selectAllRecipeType();
			model.addAttribute("recipeType", recipeType);
			return "recipe/recipeForm";
		}
		
		// 게시판 신규 작성
		@RequestMapping(value = "recipe/recipeInsert.do", method = RequestMethod.POST)
		@ResponseBody
		public Map recipeInsert(
				@RequestParam("title") String title,
				@RequestParam("content") String content,
				@RequestParam("memId") String memId,
				@RequestParam("type") String type,Model model) {
			RecipeBoardVO recipe = new RecipeBoardVO();
			recipe.setMemId(memId);
			recipe.setRecipeTitle(title);
			recipe.setRecipeType(type);
			recipe.setRecipeContent(content);
			
			ServiceResult result  = service.createRecipe(recipe);
			
			String status= null;
			if (result.equals(ServiceResult.OK)) {
				status = "ok";
			}else {
				status ="fail";
			}
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("status", status);
			map.put("recipeNo", service.selectRecentRecipe()+"");
			
			return map;
		}
		
		//레시피 찜하기
		@RequestMapping(value = "recipe/recipeLike.do", method = RequestMethod.POST)
		@ResponseBody
		public Map recipeLike(
				@RequestParam("recipeNo") int recipeNo,
				@RequestParam("loginId") String loginId,
				@RequestParam("type") String type,Model model) {
			Like2VO like = new Like2VO();
			like.setMemId(loginId);
			like.setRecipeNo(recipeNo);
			
			ServiceResult result;
			if(type.equals("heart")) {
				result  = service.deleteLike(like);
			}else {
				result  = service.insertLike(like);
			}
			
			String status= null;
			if (result.equals(ServiceResult.OK)) {
				status = "ok";
			}else {
				status ="fail";
			}
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("status", status);
			map.put("type", type);
			
			return map;
		}
		
}
