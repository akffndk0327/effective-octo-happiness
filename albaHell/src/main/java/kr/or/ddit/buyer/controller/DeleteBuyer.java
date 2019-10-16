package kr.or.ddit.buyer.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.enums.ServiceResult;

@Controller
public class DeleteBuyer{
	@Inject
	IBuyerService service ;

	@RequestMapping(value="/buyer/DeleteBuyer", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String doPost(HttpSession session, @RequestParam(name="buyer_id",required=true)String buyer_id) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
		
//		HttpSession session = req.getSession();
//		String buyer_id = req.getParameter("buyer_id");
//		if(session.isNew() || StringUtils.isBlank(buyer_id)) {
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
//			return "buyer";
//		}

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
