package kr.or.ddit.dailysupplies.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.dailysupplies.service.IDailySupplyService;

@Controller
public class ImageController {
	@Inject
	IDailySupplyService service;

	@Inject
	WebApplicationContext container;
	ServletContext application;

	@PostConstruct
	public void init() {
		application = container.getServletContext();
	}

	// ck 에디터 이미지 업로드한거 저장하기
	@RequestMapping(value = "/ds/imageUpload.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> upload(@RequestParam(name = "upload", required = true) MultipartFile uploadFile)
			throws IOException {
		Map<String, Object> messageMap = new HashMap<String, Object>();
		if (uploadFile != null) {
			// 업로드 하려면 저장해야하닌까 위치 지정해야지
			// 위치잡고
			String saveFolderURL = "/dsImages";
			String saveFolderPath = application.getRealPath(saveFolderURL); // 서블릿컨텍스트 작븡ㅁ
			File saveFolder = new File(saveFolderPath);
			if (!saveFolder.exists())
				saveFolder.mkdirs();
			// 2. 저장하기
			// 저장명
			String savename = UUID.randomUUID().toString();
			try (InputStream is = uploadFile.getInputStream();) {
				FileUtils.copyInputStreamToFile(is, new File(saveFolder, savename)); // 예외는 던지기
			}
			String saveURL = application.getContextPath() + saveFolderURL + "/" + savename;
			// 서브사이트 절대경로생김 ->front img에서 써야함

			// Map사용하기
			messageMap.put("fileName", uploadFile.getOriginalFilename()); // 원본파일명 뽑기
			messageMap.put("uploaded", "1");
			messageMap.put("url", saveURL);

		}

		return messageMap;

	}
}
