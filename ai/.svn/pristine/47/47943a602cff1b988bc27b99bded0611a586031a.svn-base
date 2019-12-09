package kr.or.ddit.diet.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.mail.search.IntegerComparisonTerm;
import javax.print.attribute.standard.Media;

import org.apache.poi.hpsf.Array;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.diet.service.IDietService;
import kr.or.ddit.vo.MenuAllergyVO;
import kr.or.ddit.vo.MenuVO;
import kr.or.ddit.vo.MonthlyVO;
import kr.or.ddit.vo.OnedayVO;
import kr.or.ddit.vo.WeightVO;

/**
 * 
 * @author 박슬기
 * @since 2019. 11. 6.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * 
 * --------     --------    ----------------------
 * 2019. 11. 6.      박슬기       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class DietInsert {
	@Inject
	IDietService service;
	
	@RequestMapping("diet/dietInsert.do")
	public ModelAndView DietForm(Authentication customUser) {
		String memId = customUser.getName();
		ModelAndView mav = new ModelAndView();
		List<MenuVO> t_list = service.retrievetMenuList();
		List<MenuAllergyVO> all_list = service.retrievetAllergyMenuList(memId);
		List<MenuVO> rec_list = service.retrieveRecommend();
		Map<String, String> option = new HashMap<>();
		for (int i = 0; i < t_list.size(); i++) {
			for (int j = 0; j < all_list.size(); j++) {
				if (t_list.get(i).getMenuId().equals(all_list.get(j).getMenuId())) {
					option.put("allergy", t_list.get(i).getMenuId());
					t_list.get(i).setCheck("true");
				}
			}
		}
		
		List<MenuVO> r_list = new ArrayList<>();
		List<MenuVO> k_list = new ArrayList<>();
		List<MenuVO> g_list = new ArrayList<>();
		List<MenuVO> b_list = new ArrayList<>();

		for (int i = 0; i < t_list.size(); i++) {
			if (t_list.get(i).getMenuId().contains("B")) {
				b_list.add(t_list.get(i));
			} else if (t_list.get(i).getMenuId().contains("G")) {
				g_list.add(t_list.get(i));
			} else if (t_list.get(i).getMenuId().contains("R")) {
				r_list.add(t_list.get(i));
			} else {
				k_list.add(t_list.get(i));
			}
		}
		mav.addObject("b_list", b_list);
		mav.addObject("g_list", g_list);
		mav.addObject("r_list", r_list);
		mav.addObject("k_list", k_list);
		mav.addObject("rec_list", rec_list);
		
		mav.setViewName("diet/dietInsertForm");
		return mav;
	}
	
	@RequestMapping(value = "diet/dietMonthlyInsert.do", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Integer MonthlyInsert(Authentication customUser,
			@RequestParam(name = "use", required = true) String monthlyUse,
			@RequestParam(name = "title", required = true) String monthlyTitle,
			@RequestParam(name = "pass", required = true) String monPass) {

		String memId = customUser.getName();
		MonthlyVO monthlyVO = new MonthlyVO();
		monthlyVO.setMemId(memId);
		monthlyVO.setMonthlyUse(monthlyUse);
		if (monthlyUse.equals("N")) {
			monthlyVO.setMonPass(monPass);
		}
		monthlyVO.setMonthlyTitle(monthlyTitle);
		int result = service.createMonthly(monthlyVO);
		return result;
	}

	
	@RequestMapping(value = "diet/dietOnedayInsert.do", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Integer OnedayInsert(
			@RequestParam(name = "MenuArray", required=true) String[] MenuArray) {
		int cnt = service.createOneday(MenuArray);   
		return cnt;
	}

	@RequestMapping(value="diet/recommendInsert.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<MenuVO[]> Test(Authentication customUser,
			@RequestParam(name = "period", required=true) int period){
		String memId = customUser.getName();
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("MM");
		String month = date.format(today);
		String season = null;
		switch(month) {
		case "12":
		case "1":
		case "2":
			season = "겨울";
			break;
		case "3":
		case "4":
		case "5":
			season = "봄";
			break;
		case "6":
		case "7":
		case "8":
			season = "여름";
			break;
		default:
			season="가을";
		}
		
		//알러지 증상을 가지고있는 리스트
		List<MenuAllergyVO> all_list = service.retrieveMemAllergyMenuList(memId);
		
		//전체리스트
		List<MenuVO> t_list = service.retrievetMenuList();
		
		//가중치 목록
		List<WeightVO> w_list = service.retrieveWeight();
		int Allergy = 0;
		int Season = 0;
		int Favorite = 0;
		for (int i = 0; i < w_list.size(); i++) {
			if (w_list.get(i).getWeightId().equals("w1")) {
				Allergy = w_list.get(i).getWeightValue();
			}else if(w_list.get(i).getWeightId().equals("w2")) {
				Season = w_list.get(i).getWeightValue();
			}else {
				Favorite = w_list.get(i).getWeightValue();
			}
		}

		
		//알러지 증상만 이용하여 가중치 넣기
			for (int i = 0; i < t_list.size(); i++) {
				for (int j = 0; j < all_list.size(); j++) {
					if (t_list.get(i).getMenuId().equals(all_list.get(j).getMenuId())) {
						t_list.get(i).setMenuWeight(t_list.get(i).getMenuWeight() * Allergy);
					}
				}
			}
		
		//계절에 따른 가중치
			for (int i = 0; i < t_list.size(); i++) {
				if (t_list.get(i).getSeason() != season) {
					t_list.get(i).setMenuWeight(t_list.get(i).getMenuWeight() * Season);
				}
			}
		
		List<MenuVO> r_list = service.retrieveCountRice(memId);
		List<MenuVO> k_list = service.retrieveCountKim(memId);
		List<MenuVO> g_list = service.retrieveCountGook(memId);
		List<MenuVO> b_list = service.retrieveCountBan(memId);
		
		//식단 작성이력이있는 회원의 상위 메뉴별 가중치
			if (r_list.size() >= 5) {
				for (int j = 0; j < 5; j++) {
					for (int i = 0; i < t_list.size(); i++) {
						if (r_list.get(j).getMenuId() != t_list.get(i).getMenuId()) {
							t_list.get(i).setMenuWeight(t_list.get(i).getMenuWeight() * Favorite);
						}
					}
				}
			}
			if (k_list.size() >= 5) {
				for (int j = 0; j < 5; j++) {
					for (int i = 0; i < t_list.size(); i++) {
						if (k_list.get(j).getMenuId()!=t_list.get(i).getMenuId()) {
							t_list.get(i).setMenuWeight(t_list.get(i).getMenuWeight() * Favorite);
						}
					}
				}
			}
			if (g_list.size() >= 5) {
				for (int j = 0; j < 5; j++) {
					for (int i = 0; i < t_list.size(); i++) {
						if (g_list.get(j).getMenuId() != t_list.get(i).getMenuId()) {
							t_list.get(i).setMenuWeight(t_list.get(i).getMenuWeight() * Favorite);
						}
					}
				}
			}
			if (g_list.size() >= 5) {
				for (int j = 0; j < 5; j++) {
					for (int i = 0; i < t_list.size(); i++) {
						if (b_list.get(j).getMenuId().equals(t_list.get(i).getMenuId())) {
							t_list.get(i).setMenuWeight(t_list.get(i).getMenuWeight() * Favorite);
						}

					}
				}
			}
		
		r_list = new ArrayList<>();
		k_list = new ArrayList<>();
		g_list = new ArrayList<>();
		b_list = new ArrayList<>();
		
		
		//분류별 나누기
		for (int i = 0; i < t_list.size(); i++) {
			if (t_list.get(i).getMenuId().contains("B")) {
				b_list.add(t_list.get(i));
			} else if (t_list.get(i).getMenuId().contains("G")) {
				g_list.add(t_list.get(i));
			} else if (t_list.get(i).getMenuId().contains("R")) {
				r_list.add(t_list.get(i));
			} else {
				k_list.add(t_list.get(i));
			}
		}
		
		Collections.sort(r_list);
		Collections.sort(k_list);
		Collections.sort(g_list);
		Collections.sort(b_list);
		
		for (int i = 0; i < b_list.size(); i++) {
			System.err.println(b_list.get(i).getMenuWeight()+","+b_list.get(i).getCheck());
		}
		
		
		List<MenuVO[]> dietList = new ArrayList<>();
		int index = 0;
		for (int i = 0; i < period; i++) {
			int random = (int) (Math.random()*k_list.size());
			MenuVO[] Onedaydiet = new MenuVO[5];  
			
			//하루치 식단에 밥넣기
			MenuVO diet = new MenuVO();
			diet.setMenuId(r_list.get(i).getMenuId());
			diet.setMenuName(r_list.get(i).getMenuName());
			Onedaydiet[0] = diet;
			
			//하루치 식단에 김치넣기
			diet = new MenuVO();
			diet.setMenuId(k_list.get(random).getMenuId());
			diet.setMenuName(k_list.get(random).getMenuName());
			Onedaydiet[1] = diet;
			
			//하루치 식단에 국넣기
			diet = new MenuVO();
			diet.setMenuId(g_list.get(i).getMenuId());
			diet.setMenuName(g_list.get(i).getMenuName());
			Onedaydiet[2] = diet;
			
			//하루치 식단에 반찬넣기
			diet = new MenuVO();
			diet.setMenuId(b_list.get(index).getMenuId());
			diet.setMenuName(b_list.get(index++).getMenuName());
			Onedaydiet[3] = diet;
			
			//하루치 식단에 반찬넣기
			diet = new MenuVO();
			diet.setMenuId(b_list.get(index).getMenuId());
			diet.setMenuName(b_list.get(index++).getMenuName());
			Onedaydiet[4] = diet;
			dietList.add(Onedaydiet);
		}
		
	
		return dietList;
	}
	
}
