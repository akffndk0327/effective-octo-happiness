package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.utils.MarshallingUtils;

@Controller
public class InsertBuyer{
	@Inject
	IBuyerService service;
	
	@RequestMapping(value="/buyer/InsertBuyer", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public String doPost(HttpSession session) throws ServletException, IOException {
//		HttpSession session = req.getSession();
//		req.setCharacterEncoding("UTF-8");
//		String accept = req.getHeader("Accept");
		
		BuyerVO vo = new BuyerVO();
		
		try {
			BeanUtils.populate(vo, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Map<String, String> errors = new HashMap<String, String>();
		req.setAttribute("errors", errors);

		int result = service.buyerInsert(vo);
		
		String viewName = null;
		if (accept.toLowerCase().contains("json")) {
			resp.setContentType("application/json;charset=UTF-8");
			String json = new MarshallingUtils().marshalling(vo);
			try (PrintWriter out = resp.getWriter();

			) {
				out.print(json);
			} 
		} else {
			viewName = "/buyer/buyer";
			req.setAttribute("vo", vo);
		}
		return viewName;
	}
}
