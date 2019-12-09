package kr.or.ddit.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.cart.service.ICartService;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.order.service.IOrderService;
import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.Order2VO;
import kr.or.ddit.vo.OrderDetailVO;

@Controller
public class OrderInsert {
	@Inject IOrderService service;
	@Inject ICartService carService;

	@RequestMapping("/order/orderInsert.do")
	public String InsertOrderForm(
				@RequestParam String memId,
				Model model
			) {
		
		List<CartVO> list = carService.retrieveCartList(memId);
		model.addAttribute("list", list);
		return "order/orderForm";
	}

	@RequestMapping(value="/order/orderInsert.do", method=RequestMethod.POST)
	public String InsertOrder(
			@Valid @ModelAttribute("order") Order2VO order,
			Errors errors,
			Model model
			) {
		boolean valid = !errors.hasErrors();
		String viewName = null;
		String message = null;
		model.addAttribute("message", message);
		List<OrderDetailVO> dtList = new ArrayList<>();
		for (int i = 0; i < order.getProdIds().length; i++) {
			OrderDetailVO vo = new OrderDetailVO();
			vo.setProdId(order.getProdIds()[i]);
			vo.setOrderQty(order.getProdQty()[i]);
			dtList.add(vo);
		}		
		order.setOrderDtList(dtList);
		if (valid) {
			ServiceResult result = service.createOrder(order);
			switch (result) {
			case FAILED:
				message = "서버 오류";
				viewName = "order/orderForm";
				break;
			default:
				message = "주문/결제 성공";
				model.addAttribute("message", message);
				String orderId = service.retrieveOrderId(order.getMemId());
				model.addAttribute("orderId", orderId);
				viewName = "order/orderResult";
				break;
			}

		} else {
			message = "주문/결제 실패";
			viewName = "order/orderForm";
		}
		return viewName;
	}
}
