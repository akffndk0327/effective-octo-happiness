package kr.or.ddit.chemicals.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.chemicals.service.IChemicalsService;
import kr.or.ddit.vo.ChemicalsVO;
import kr.or.ddit.vo.RawMaterialVO;

/**
 * @author 최서희
 * @since 2019. 11. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 19.      최서희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class ChemicalsRetrieve {
	@Inject IChemicalsService service;
	
	@RequestMapping(value="/chemical/chemicalList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ChemicalsVO> selectChemicals(
			@RequestParam(required = false) String name,
			Model model
			) {
		List<ChemicalsVO> cheList = service.retrieveChemicals(name);
		model.addAttribute("cheList", cheList);
		return cheList;
	}
	
	@RequestMapping(value="/chemical/rawmaterialList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<RawMaterialVO> selectRawMaterial(
			@RequestParam(required = false) String name,
			Model model
			) {
		List<RawMaterialVO> rawList = service.retrieveRawMaterial(name);
		model.addAttribute("cheList", rawList);
		return rawList;
	}
	
}
