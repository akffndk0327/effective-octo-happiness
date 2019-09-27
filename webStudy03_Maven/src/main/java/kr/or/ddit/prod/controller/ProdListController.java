package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.MarshallingUtils;
import kr.or.ddit.vo.ProdVO;

//POJO
@CommandHandler
public class ProdListController {
	IProdService service = ProdServiceImpl.getInstance();

	@URIMapping("/prod/prodList.do")
	public String prodList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String accept = req.getHeader("Accept");
		// 처리
		List<ProdVO> list = service.retrieveProdList();
		// 검증
		if(accept.toLowerCase().contains("json")) {
	         resp.setContentType("application/json; charset=UTF-8");
	         
	         String json = new MarshallingUtils().marshalling(list);
	         
	         try (PrintWriter out = resp.getWriter();) {
	            out.println(json);
	         } 
	         
	         return null;
	      
		} else {
			String viewName = "prod/prodList";
			req.setAttribute("prodList", list);
			return viewName;
		}
	}
}
