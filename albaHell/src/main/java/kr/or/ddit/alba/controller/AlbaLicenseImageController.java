package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.alba.vo.Lic_albaVO;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.MarshallingUtils;

@CommandHandler
public class AlbaLicenseImageController {
	IAlbaService service = new AlbaServiceImpl();
	
	@URIMapping(value = "/alba/licenseImage.do", method = HttpMethod.POST)
	public String AlbaForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Lic_albaVO licAlba = service.selectLicAlba(setLicAlba(req));
		
		Map<String, Object> result = new HashMap<String, Object>();
		if(licAlba.getLic_image() == null) {
			result.put("valid", new Boolean(true));
		} else {
			result.put("valid", new Boolean(false));
			result.put("base64", licAlba.getLic_image());
		}
		
		resp.setContentType("application/json;charset=UTF-8");
		String json = new MarshallingUtils().marshalling(result); //마샬링
		
		try(
			PrintWriter out = resp.getWriter();
		){
			out.println(json);
		}
		
		return null;
	}
	
	private Lic_albaVO setLicAlba(HttpServletRequest request) {
		String licCode = request.getParameter("lic_code");
		String alId    = request.getParameter("al_id");
		
		Lic_albaVO vo = new Lic_albaVO();
		vo.setLic_code(licCode);
		vo.setAl_id(alId);
		
		return vo;
	}
}