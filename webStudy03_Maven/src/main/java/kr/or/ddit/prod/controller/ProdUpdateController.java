package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.ProdVO;

@CommandHandler
public class ProdUpdateController {
	IProdService service = new ProdServiceImpl();

	@URIMapping("/prod/prodUpdate.do")
	public String updateForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String prod_id = req.getParameter("what");
		if (StringUtils.isBlank(prod_id)) {
			resp.sendError(400);
			return null;
		}
		ProdVO prod = service.retrieveProd(prod_id);
		req.setAttribute("prod", prod);
		return "prod/prodForm";
	}

	@URIMapping(value = "/prod/prodUpdate.do", method = HttpMethod.POST)
	public String Update(HttpServletRequest req, HttpServletResponse reps) throws IOException, ServletException  {
		
		ProdVO prod = new ProdVO();
		req.setAttribute("prod", prod);
		try {
			BeanUtils.populate(prod, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		//1002
		//실제파일이 업로드 되있는지 확인 필요해
		Part part= req.getPart("prod_image");
		long size = part.getSize(); //파일이 선택됬으면 0보단 큼
		if (size > 0) { // 파일이 업로드 되었다

			// 1. 저장위치
			String saveFolderURL = "/prodImages"; // 여기에 이미지 하나 저장됨 .!
			String saveFolderPath = req.getServletContext().getRealPath(saveFolderURL);
			File saveFolder = new File(saveFolderPath);
			// 존재여부 확인 !
			if (!saveFolder.exists())
				saveFolder.mkdirs();
			// 2.저장명 원본파일명 쓰지마
			String savename = UUID.randomUUID().toString();
			try (InputStream is = part.getInputStream();) {
				FileUtils.copyInputStreamToFile(is, new File(saveFolder, savename)); // 여기서 복사 떠짐
			} // 저장명이라는 메타데이터 생김
			prod.setProd_img(savename); // 입력받지 않은 이미지 경로가 생김 그ㅓ나 이미지는 웹서버상에 images에 저장됨.
		}
		
		
		// 분석 검증
		Map<String, String> errors = new HashMap<String, String>();
		req.setAttribute("errors", errors);
		boolean valid = validate(prod, errors);
		
		String viewName = null;
		String message = null;
		if(valid) { //빈항목 잇나없나
			//1.서비스 받기.2. 메서드 호출, 3 통과
			ServiceResult result = service.modifyProd(prod);
			switch (result) {
			case OK:
				message = "수정성공";
				viewName = "redirect:/prod/prodView.do?what="+prod.getProd_id();
				break;
			default:
				message ="서버오류";
				viewName ="prod/prodForm";
				break;
			}
		}else {
			viewName = "prod/prodForm";
			
		}
		req.getSession().setAttribute("message", message);
		return viewName;
		
	}

	private boolean validate(ProdVO prod, Map<String, String> errors) {
		boolean valid = true; // 무조건 통과
//		if (StringUtils.isBlank(prod.getProd_id())) {
//			valid = false;
//			errors.put("prod_id", "PROD_ID 누락");
//		}
		if (StringUtils.isBlank(prod.getProd_name())) {
			valid = false;
			errors.put("prod_name", "PROD_NAME 누락");
		}
		if (StringUtils.isBlank(prod.getProd_lgu())) {
			valid = false;
			errors.put("prod_lgu", "PROD_LGU 누락");
		}
		if (StringUtils.isBlank(prod.getProd_buyer())) {
			valid = false;
			errors.put("prod_buyer", "PROD_BUYER 누락");
		}
		if (prod.getProd_cost()<0) {
			valid = false;
			errors.put("prod_cost", "구매가 누락");
		}
		if (prod.getProd_price()<0) {
			valid = false;
			errors.put("prod_price", "판매가 누락");
		}
		if (prod.getProd_sale()<0) {
			valid = false;
			errors.put("prod_sale", "세일가 누락");
		}
		if (StringUtils.isBlank(prod.getProd_outline())) {
			valid = false;
			errors.put("prod_outline", "PROD_OUTLINE 누락");
		}
//		if (StringUtils.isBlank(prod.getProd_img())) {
//			valid = false;
//			errors.put("prod_img", "PROD_IMG 누락");
//		}
		if (prod.getProd_totalstock()<0) {
			valid = false;
			errors.put("prod_totalstock", "PROD_TOTALSTOCK 누락");
		}
		if (prod.getProd_properstock()<0) {
			valid = false;
			errors.put("prod_properstock", "PROD_PROPERSTOCK 누락");
		}
		return valid;
	}
}
