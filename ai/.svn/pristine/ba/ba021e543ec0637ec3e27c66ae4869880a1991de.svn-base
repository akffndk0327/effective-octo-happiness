package kr.or.ddit.refund.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.refund.service.IRefundService;

@Controller
public class RefundUpdate {
	@Inject IRefundService service;

	//환불 승인 처리
	@RequestMapping(value="/refund/refundUpdateApproval.do", method=RequestMethod.GET)
	public String updateRefundApprovalYes(
		@RequestParam(required=true) String orderId,
		RedirectAttributes redirect,
		Model model
		) {
		String viewName = null;
		String message = null;
		model.addAttribute("message", message);
		ServiceResult result = service.updateOrderRefundYes(orderId);
		switch (result) {
		case FAILED:
			message = "서버 오류";
			model.addAttribute("message", message);
			viewName = "order/orderEmpList";
			break;
		default:
			message = "환불 승인 처리되었습니다.";
			redirect.addFlashAttribute("message", message);
			viewName = "redirect:/order/orderEmpList.do";
			break;
		}
		return viewName;
	}
	
	//환불 거절 처리
	@RequestMapping(value="/refund/refundUpdateApproval.do", method=RequestMethod.POST)
	public String updateRefundApprovalNo(
			@RequestParam(required=true) String orderId,
			RedirectAttributes redirect,
			Model model
			) {
		String viewName = null;
		String message = null;
		model.addAttribute("message", message);
		ServiceResult result = service.updateOrderRefundNo(orderId);
		switch (result) {
		case FAILED:
			message = "서버 오류";
			model.addAttribute("message", message);
			viewName = "order/orderEmpList";
			break;
		default:
			message = "환불 거부 처리되었습니다.";
			redirect.addFlashAttribute("message", message);
			viewName = "redirect:/order/orderEmpList.do";
			break;
		}
		return viewName;
	}
	
}
