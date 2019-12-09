package kr.or.ddit.refund.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.refund.service.IRefundService;
import kr.or.ddit.vo.RefundVO;

@Controller
public class RefundInsert {
	@Inject IRefundService service;
	
	//결제 환불 신청
	@RequestMapping("/refund/refundInsert.do")
	public String insertRefund(
			@ModelAttribute("refund") RefundVO refund,
			RedirectAttributes redirect,
			Model model
			) {
		String viewName = null;
		String message = null;
		model.addAttribute("message", message);
		
		ServiceResult result = service.createOrderRefund(refund);
		switch (result) {
		case FAILED:
			message = "서버 오류";
			model.addAttribute("message", message);
			viewName = "order/orderList";
			break;
		default:
			message = "환불신청 처리 되었습니다.";
			redirect.addFlashAttribute("message", message);
			viewName = "redirect:/order/orderList.do";
			break;
		}
		return viewName;
	}

}
