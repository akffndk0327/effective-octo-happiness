package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.MarshallingUtils;

@CommandHandler
public class UpdateBuyer{
	IBuyerService service = BuyerServiceImpl.getInstance();
	

	@URIMapping(value="/buyer/UpdateBuyer", method=HttpMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		BuyerVO buyer = new BuyerVO();
		req.setAttribute("buyer", buyer);
		try {
			BeanUtils.populate(buyer, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		Map<String, String> errors = new HashMap<String, String>();
		req.setAttribute("errors", errors);
		boolean valid = validate(buyer, errors);
		
		String viewName = null;
		String message = null;
		
		if(valid) {
			ServiceResult result = service.buyerUpdate(buyer);
			switch (result) {
			case OK:
				message = "수정성공";
				viewName = "redirect:/buyer/DetailBuyer?what="+buyer.getBuyer_id();
				break;
			default:
				message ="서버오류";
				viewName ="buyer/buyerForm"; // 수정화면 ?
				break;
			}
		}else {
			viewName = "buyer/buyerForm"; //수정화면 ?
			
		}
		req.getSession().setAttribute("message", message);
		return viewName;
		
	}

	private boolean validate(BuyerVO vo, Map<String, String> errors) { // required가 필요한 부분은
		boolean valid = true;
		
		if (StringUtils.isBlank(vo.getBuyer_id())) {
			valid = false;
			errors.put("buyer_id", "거래처 아이디 누락");
		}
		if (StringUtils.isBlank(vo.getBuyer_name())) {
			valid = false;
			errors.put("buyer_name", "거래처명 누락");
		}
		if (StringUtils.isBlank(vo.getBuyer_lgu())) {
			valid = false;
			errors.put("mem_name", "대분류 누락");
		}
		if (StringUtils.isBlank(vo.getBuyer_bankno())) {
			valid = false;
			errors.put("mem_zip", "은행 구분 id 누락");
		}
		if (StringUtils.isBlank(vo.getBuyer_bankname())) {
			valid = false;
			errors.put("mem_add1", "은행명 누락");
		}
		if (StringUtils.isBlank(vo.getBuyer_mail())) {
			valid = false;
			errors.put("mem_add2", "이메일 누락");
		}
		if (StringUtils.isBlank(vo.getBuyer_add1())) {
			valid = false;
			errors.put("mem_mail", "주소 누락");
		}

		return valid;
	}

}
