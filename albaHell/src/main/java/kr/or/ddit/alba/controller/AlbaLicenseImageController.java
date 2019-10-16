package kr.or.ddit.alba.controller;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.alba.vo.Lic_albaVO;

@Controller
public class AlbaLicenseImageController {
	@Inject
	IAlbaService service ;
	@RequestMapping(value="/alba/licenseImage.do")
	public String licenseImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String al_id = req.getParameter("al_id");
		String lic_code = req.getParameter("lic_code");
		if(StringUtils.isBlank(al_id)||StringUtils.isBlank(lic_code)){
			resp.sendError(400);
			return null;
		}
		Lic_albaVO licAlba = new Lic_albaVO();
		licAlba.setAl_id(al_id);
		licAlba.setLic_code(lic_code);
		Lic_albaVO license = service.retrieveLicense(licAlba);
		resp.setContentType("application/octet-stream");
		byte[] imageData = license.getLic_image();
		if(imageData==null){
			try(
				FileInputStream fis = new FileInputStream(req.getServletContext().getRealPath("/images/noImage.png"));
			){		
				int size = fis.available();
				imageData = new byte[size];
				IOUtils.read(fis, imageData);
			}
		}
		try(
			OutputStream os = resp.getOutputStream();
			ByteArrayInputStream is = new ByteArrayInputStream(imageData);
		){
			IOUtils.copy(is, os);
		}
		return null;
	}
}