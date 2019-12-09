package kr.or.ddit.order.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.order.service.IOrderService;

@Controller
public class OrderUpdate {
	@Inject IOrderService service;
	
	//배송완료
	@RequestMapping("/order/orderUpdate.do")
	public String updateOrder(
			@RequestParam(required=true) String orderId,
			RedirectAttributes redirect,
			Model model
			) {
		String viewName = null;
		String message = null;
		model.addAttribute("message", message);
		ServiceResult result = service.modifyOrder(orderId);
		switch (result) {
		case FAILED:
			message = "서버 오류";
			model.addAttribute("message", message);
			viewName = "order/orderEmpList";
			break;
		default:
			message = "배송완료 처리되었습니다.";
			redirect.addFlashAttribute("message", message);
			viewName = "redirect:/order/orderEmpList.do";
			break;
		}
		return viewName;
	}
	

	//주문 취소
	@RequestMapping("/order/orderDelete.do")
	public String deleteOrder(
			@RequestParam(required=true) String orderId,
			RedirectAttributes redirect,
			Model model
			) {
		String viewName = null;
		String message = null;
		model.addAttribute("message", message);
		ServiceResult result = service.removeOrder(orderId);
		switch (result) {
		case FAILED:
			message = "서버 오류";
			model.addAttribute("message", message);
			viewName = "order/orderList.do";
			break;
		default:
			message = "주문취소 처리 되었습니다.";
			redirect.addFlashAttribute("message", message);
			viewName = "redirect:/order/orderList.do";
			break;
		}
		return viewName;
	}
}
