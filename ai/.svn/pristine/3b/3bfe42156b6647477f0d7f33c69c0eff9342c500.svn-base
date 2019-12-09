package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.vo.LprodVO;
import kr.or.ddit.vo.ProdRejectVO;
import kr.or.ddit.vo.ProdVO;
/**
 * @author 최서희
 * @since 2019. 11. 6.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                     수정자              수정내용
 * -------------   --------    ----------------------
 * 2019. 11. 6.     최서희             최초작성
 * 2019. 11. 14.    최서희		 제품 판매중지/재판매/신청반려 기능 구현             
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class ProdUpdate {
	@Inject IProdService service;
	
	//제품 등록 신청 반려
	@RequestMapping(value="/prod/prodUpdateReject.do", method=RequestMethod.POST)
	public String updateProdReject(
			@ModelAttribute("prodReject") ProdRejectVO prodRejectVO,
			RedirectAttributes redirect,
			Model model
			) {
		String viewName = null;
		String message = null;
		model.addAttribute("message", message);
		ServiceResult result = service.createProdReject(prodRejectVO);
		switch (result) {
		case FAILED:
			message = "서버 오류";
			model.addAttribute("message", message);
			viewName = "prod/prodAdminList.do";
			break;
		default:
			message = "기업회원의 제품 등록 신청이 반려 되었습니다.";
			redirect.addFlashAttribute("message", message);
			viewName = "redirect:/prod/prodAdminList.do";
			break;
		}
		return viewName;
	}
	
	//판매중인 제품 판매중지
	@RequestMapping("/prod/prodUpdateStop.do")
	public String updateProdStop(
			@RequestParam(required=true) String prodId,
			@RequestParam(required=true) String authorId,
			RedirectAttributes redirect,
			Model model
			) {
		String viewName = null;
		String message = null;
		model.addAttribute("message", message);
		ServiceResult result = service.modifyProdStop(prodId);
		switch (result) {
		case FAILED:
			message = "서버 오류";
			model.addAttribute("message", message);
			viewName = "prod/prodAdminList.do";
			break;
		default:
			if("ROLE_EMP".equals(authorId)) {
				message = "해당 제품이 판매중지 되었습니다. 재판매를 원하실 경우 관리자에게 문의하세요★";
				redirect.addFlashAttribute("message", message);
				viewName = "redirect:/prod/prodEmpList.do";
			}else if("ROLE_ADMIN".equals(authorId)){
				message = "해당 제품이 판매중지 되었습니다.";
				redirect.addFlashAttribute("message", message);
				viewName = "redirect:/prod/prodAdminList.do";
			}
			break;
		}

		return viewName;
		
	}
	//판매중지 제품 재판매
	@RequestMapping("/prod/prodUpdateStatus.do")
	public String updateProdStatus(
			@RequestParam(required=true) String prodId,
			RedirectAttributes redirect,
			Model model
			) {
		String viewName = null;
		String message = null;
		model.addAttribute("message", message);
		ServiceResult result = service.modifyProdStatus(prodId);
		switch (result) {
		case FAILED:
			message = "서버 오류";
			model.addAttribute("message", message);
			viewName = "prod/prodAdminList.do";
			break;
		default:
			message = "재판매가 시작되었습니다.";
			redirect.addFlashAttribute("message", message);
			viewName = "redirect:/prod/prodAdminList.do";
			break;
		}
		return viewName;
	}
	
	
	//제품 내용 수정
	@RequestMapping("/prod/prodUpdate.do")
	public String updateProdForm(
			@RequestParam(required=true) String prodId,
			Model model
			) {
		model.addAttribute("prodId", prodId);
		return "prod/prodUpdateForm";
	}
	
	@RequestMapping(value="/prod/prodUpdate.do", method=RequestMethod.POST)
	public String updateProd(
			@ModelAttribute("prod") ProdVO prod,
			RedirectAttributes redirect,
			Model model
			) throws IOException {
		String viewName = null;
		String message = null;
		model.addAttribute("message", message);
		
		//blob저장
		prod.setProd_image(prod.getProdImage());
		
		ServiceResult result = service.modifyProd(prod);
		switch (result) {
		case FAILED:
			message = "서버 오류";
			List<LprodVO> lprodList = service.retrieveLprodAList();
			model.addAttribute("lprodList", lprodList);
			viewName = "prod/prodUpdateForm";
			break;
		default:
			message = "상품 수정 성공";
			redirect.addFlashAttribute("message", message);
			viewName = "redirect:/prod/prodList.do";
			break;
		}

		return viewName;
	}
	
	

}
