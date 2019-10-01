package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@CommandHandler
public class ProdViewController {
	@URIMapping("/prod/prodView.do")
	//view layer: prod/prodVies, model name 
	public String prodList(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		IProdService service =new ProdServiceImpl();
		String prod_id =req.getParameter("what");
		if(StringUtils.isBlank(prod_id)) {
			resp.sendError(400);
			return null;
		}
		ProdVO prod= service.retrieveProd(prod_id);
		req.setAttribute("prod", prod);
		return "prod/prodView";
		
	}
}
