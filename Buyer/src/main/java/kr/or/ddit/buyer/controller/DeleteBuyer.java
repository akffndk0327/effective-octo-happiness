package kr.or.ddit.buyer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;

@CommandHandler
public class DeleteBuyer{
	IBuyerService service = BuyerServiceImpl.getInstance();

	@URIMapping(value="/buyer/DeleteBuyer", method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		String buyer_id = req.getParameter("buyer_id");
		if(session.isNew() || StringUtils.isBlank(buyer_id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return "buyer";
		}

		BuyerVO vo = new BuyerVO();
		vo.setBuyer_id(buyer_id);
		ServiceResult result = service.buyerDelete(buyer_id);
		
		String viewName = "buyer";
		String message = null;
		switch (result) {
		case INVALIDPASSWORD:
			message = "아이디 오류";
			session.setAttribute("message", message);
			break;
		case FAILED:
			message = "삭제 실패";
			session.setAttribute("message", message);
			break;
		case OK:
			session.invalidate();
			break;

		default:
			break;
		}
//		resp.sendRedirect(req.getContextPath() + viewName);
//		return "redirect:" + viewName;
		return viewName;
	}
}
