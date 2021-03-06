package kr.or.ddit.cart.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.cart.service.ICartService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.CartVO;

@Controller
public class CartInsert {
	
	@Inject ICartService service;
	
	@RequestMapping(value="/cart/cartInsert.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String createCart(
			@RequestParam(required=true) String prodId,
			@RequestParam(required=true) int cartQty,
			Authentication auth,
			Model model
			) {
		String memId = auth.getName();
		String viewName = null;
		String message = null;
		model.addAttribute("message", message);
		
		CartVO cart = new CartVO();
		cart.setProdId(prodId);
		cart.setMemId(memId);
		cart.setCartQty(cartQty);
		ServiceResult result = service.createCart(cart);
		
		switch (result) {
		case FAILED:
			message = "서버 오류";
			viewName = "prod/proddView.do?what="+prodId;
			break;
		default:
			break;
		}
		
		return viewName;
		
	}
}
