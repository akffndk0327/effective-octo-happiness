package kr.or.ddit.cart.controller;

import java.util.List;

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
import kr.or.ddit.vo.Order2VO;

@Controller
@RequestMapping("/cart")
public class CartUpdate {
	@Inject ICartService service;

	@RequestMapping(value="cartUpdate.do", method=RequestMethod.POST, 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<CartVO> updateCart(
			@RequestParam int cartQty,
			@RequestParam String cartId,
			Model model,
			Authentication customUser
			) {
		CartVO cart = new CartVO();
		cart.setCartId(cartId);
		cart.setCartQty(cartQty);
		String loginId = customUser.getName();
		ServiceResult result = service.modifyCart(cart);
		switch (result) {
		case FAILED:
			break;
		default:
			List<CartVO> cartList = service.retrieveCartList(loginId);
			model.addAttribute("cart", cart);
			return cartList;
		}
		return null;
	}
	
	@RequestMapping(value="cartDelete.do", method=RequestMethod.POST, 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<CartVO> deleteCart(
			@RequestParam(required=true) String cartId,
			Model model
			,Authentication customUser
			) {
		String loginId = customUser.getName();
		String message = null;
		model.addAttribute("message", message);
		Order2VO order = new Order2VO();
		String[] delCartId = {cartId};
		order.setDelCartId(delCartId);
		ServiceResult result = service.removeCart(order);
		switch (result) {
		case FAILED:
			message = "서버 오류";
			break;
		default:
			message = "장바구니 삭제 성공";
			List<CartVO> cart =  service.retrieveCartList(loginId);
			model.addAttribute("cart", cart);
			return cart;
		}
		return null;
	}
}
