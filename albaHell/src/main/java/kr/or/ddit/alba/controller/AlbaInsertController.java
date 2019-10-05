package kr.or.ddit.alba.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.alba.dao.IAlbaDao;
import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.alba.vo.AlbaVO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;

@CommandHandler
public class AlbaInsertController {
	IAlbaService service = new AlbaServiceImpl();

	@URIMapping(value = "/alba/albaInsert.do", method = HttpMethod.GET)
	public String InsertForm(HttpServletRequest req, HttpServletResponse resp) {
		return "alba/albaForm";

	}

	@URIMapping(value = "/alba/albaInsert.do", method = HttpMethod.POST)
	public String AlbaInsert(HttpServletRequest req, HttpServletResponse resp) {
		AlbaVO alba = new AlbaVO();
		req.setAttribute("alba", alba);

		try {
			BeanUtils.populate(alba, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}

//		//사진 등록 
//		if (req instanceof MultipartRequestWrapper) {
//			PartWrapper partWrapper = ((MultipartRequestWrapper) req).getPartWrappper("prod_image");
//			if (partWrapper != null) {
//				// 1. 저장위치
//				String saveFolderURL = "/licImages"; // 여기에 이미지 하나 저장됨 .!
//				String saveFolderPath = req.getServletContext().getRealPath(saveFolderURL);
//				File saveFolder = new File(saveFolderPath);
//				// 존재여부 확인 !
//				if (!saveFolder.exists())
//					saveFolder.mkdirs();
//				// 2.저장명 원본파일명 쓰지마
//				String savename = UUID.randomUUID().toString();
//				try (InputStream is = partWrapper.getInputStream();) {
//					FileUtils.copyInputStreamToFile(is, new File(saveFolder, savename)); // 여기서 복사 떠짐
//				} // 저장명이라는 메타데이터 생김
//				alba.set(savename); // 입력받지 않은 이미지 경로가 생김 그ㅓ나 이미지는 웹서버상에 images에 저장됨.
//			
//			}
		Map<String, String> errors = new HashMap<String, String>();
		req.setAttribute("errors", errors);

		boolean valid = validate(alba, errors);
		String viewName = null;
		String message = null;
		if (valid) {
			ServiceResult result = service.insertAlba(alba);
			switch (result) {
			case OK:
				viewName = "redirect:/alba/albaView.do?what=" + alba.getAl_id();
				break;

			default:
				message = "서버오류 ";
				viewName = "alba/albaForm";
				break;
			}
		} else {
			viewName = "alba/albaForm";
		}
		req.setAttribute("message", message);

		return null;
	}

	private boolean validate(AlbaVO alba, Map<String, String> errors) {
		boolean valid = true;
		if (StringUtils.isBlank(alba.getAl_id())) {
			valid = false;
			errors.put("al_id", "AL_ID 누락");
		}
		if (StringUtils.isBlank(alba.getAl_name())) {
			valid = false;
			errors.put("al_name", "AL_NAME 누락");
		}
		if (StringUtils.isBlank(Integer.toString(alba.getAl_age()))) {
			valid = false;
			errors.put("al_age", "AL_AGE 누락");
		}
		if (StringUtils.isBlank(alba.getAl_address())) {
			valid = false;
			errors.put("al_address", "AL_ADDRESS 누락");
		}
		if (StringUtils.isBlank(alba.getAl_hp())) {
			valid = false;
			errors.put("al_hp", "AL_HP 누락");
		}
		if (StringUtils.isBlank(alba.getGr_code())) {
			valid = false;
			errors.put("gr_code", "GR_CODE 누락");
		}
		if (StringUtils.isBlank(alba.getAl_gen())) {
			valid = false;
			errors.put("al_gen", "AL_GEN 누락");
		}
		if (StringUtils.isBlank(alba.getAl_btype())) {
			valid = false;
			errors.put("al_btype", "AL_BTYPE 누락");
		}
		if (StringUtils.isBlank(alba.getAl_mail())) {
			valid = false;
			errors.put("al_mail", "AL_MAIL 누락");
		}
		return valid;

	}
}
