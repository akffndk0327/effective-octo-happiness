package kr.or.ddit.diet.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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

/**
 * 
 * @author 박슬기
 * @since 2019. 11. 6.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * 
 * --------     --------    ----------------------
 * 2019. 11. 6.      박슬기       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 *      </pre>
 */
@Controller
public class DietUpdate {
	@Inject
	IDietService service;

	@RequestMapping(value = "diet/dietUpdate.do")
	public ModelAndView DietView(@RequestParam(required = true) String monthlyId,
			@RequestParam(required = true) String memId) {
		ModelAndView mav = new ModelAndView();

		List<OnedayVO> o_list = service.retrieveDiet(monthlyId);
		List<MenuVO> t_list = service.retrievetMenuList();
		List<MenuAllergyVO> all_list = service.retrievetAllergyMenuList(memId);

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

		mav.addObject("writer", memId);
		mav.addObject("o_list", o_list);
		mav.setViewName("diet/dietUpdateForm");

		return mav;
	}

	@RequestMapping(value = "diet/dietMonthlyUpdate.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Integer DietUpdate(@RequestParam(name = "use", required = true) String use,
			@RequestParam(name = "title", required = true) String title,
			@RequestParam(name = "id", required = true) String id,
			@RequestParam(name = "pass", required = true) String pass) {
		MonthlyVO monthlyVO = new MonthlyVO();
		monthlyVO.setMonthlyId(id);
		monthlyVO.setMonthlyUse(use);
		monthlyVO.setMonthlyTitle(title);
		if (use.equals("N")) {
			monthlyVO.setMonPass(pass);
		}
		int result = service.modifyMonthly(monthlyVO);

		return result;
	}

	@RequestMapping(value = "diet/dietOnedayUpdate.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public OnedayVO DietUpdate(@RequestParam(name = "beforeRice", required = true) String beforeRice,
			@RequestParam(name = "beforeKim", required = true) String beforeKim,
			@RequestParam(name = "beforeGook", required = true) String beforeGook,
			@RequestParam(name = "beforeBan", required = true) String beforeBan,
			@RequestParam(name = "beforeBan1", required = true) String beforeBan1,
			@RequestParam(name = "afterRice", required = true) String afterRice,
			@RequestParam(name = "afterKim", required = true) String afterKim,
			@RequestParam(name = "afterGook", required = true) String afterGook,
			@RequestParam(name = "afterBan", required = true) String afterBan,
			@RequestParam(name = "oneId", required = true) String id) {

		String afterBan1 = afterBan.substring(0, 4);
		afterBan = afterBan.substring(5);

		
		OnedayVO onedayVO = new OnedayVO();
		int result = 0;
		if (!beforeRice.equals(afterRice)) {
			onedayVO.setMenuId(beforeRice);
			onedayVO.setOnedayId(id);
			onedayVO.setAfterMenu(afterRice);
			result = service.modifyOneday(onedayVO);
		} 
		if (!beforeKim.equals(afterKim)) {
			onedayVO.setMenuId(beforeKim);
			onedayVO.setOnedayId(id);
			onedayVO.setAfterMenu(afterKim);
			result += service.modifyOneday(onedayVO);
		} 
		
		if (!beforeGook.equals(afterGook)) {
			onedayVO.setMenuId(beforeGook);
			onedayVO.setOnedayId(id);
			onedayVO.setAfterMenu(afterGook);
			result += service.modifyOneday(onedayVO);
		}
		
		if (!beforeBan.equals(afterBan)) {
			onedayVO.setMenuId(beforeBan);
			onedayVO.setOnedayId(id);
			onedayVO.setAfterMenu(afterBan);
			result += service.modifyOneday(onedayVO);
		} 
		
		if (!beforeBan1.equals(afterBan1)) {
			onedayVO.setMenuId(beforeBan1);
			onedayVO.setOnedayId(id);
			onedayVO.setAfterMenu(afterBan1);
			result += service.modifyOneday(onedayVO);
		}
		return onedayVO;
	}
}
