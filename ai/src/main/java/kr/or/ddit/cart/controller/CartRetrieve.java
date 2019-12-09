package kr.or.ddit.cart.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.cart.service.ICartService;
import kr.or.ddit.vo.CartVO;

@Controller
public class CartRetrieve {
	@Inject
	ICartService service;

	@RequestMapping("/cart/cartList.do")
	public String selectCartList(
			Authentication auth,
			Model model 
			) {
		String memId = auth.getName();
		List<CartVO> list = service.retrieveCartList(memId);
		model.addAttribute("cartVO", list);
		return "cart/cartList";
	}
	
}
