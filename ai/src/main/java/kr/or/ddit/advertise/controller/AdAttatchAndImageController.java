package kr.or.ddit.advertise.controller;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.advertise.service.IAdvertiseService;
import kr.or.ddit.vo.AdattatchVO;

@Controller
public class AdAttatchAndImageController {
	@Inject
	IAdvertiseService service;
	
	@Inject
	WebApplicationContext container;
	ServletContext application;

	@Value("#{appInfo.imgFolderRealPath}")
	String saveFolderURL;
	File saveFolder;

	@PostConstruct
	public void init() {
		application = container.getServletContext();
		saveFolder = new File(saveFolderURL);
		if (!saveFolder.exists())
			saveFolder.mkdirs();
	}

	// 미리보기 할 수 잇음 .
	@RequestMapping("/advertise/addownload.do")
	public String download(@RequestParam(required = true) String what, Model model) {
		AdattatchVO attatch = service.downloadAttatch(what);
		model.addAttribute("attatch", attatch);
		return "addownloadView";
	}

//	@RequestMapping(value = "/advertise/imageUpload.do", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ResponseBody
//	public Map<String, Object> adupload(@RequestPart(required = true, name = "upload") MultipartFile uploadFile)
//			throws IOException {
//		Map<String, Object> messageMap = new HashMap<>();
//		if (uploadFile != null) {
//			
//			String savename = UUID.randomUUID().toString();
////			try (InputStream is = uploadFile.getInputStream();) {
////				FileUtils.copyInputStreamToFile(is, new File(saveFolder, savename));
////			}
//			AdattatchVO vo = new AdattatchVO(uploadFile);
//			vo.saveFile(saveFolder);
//			
//			String saveURL = application.getContextPath() + saveFolderURL + "/" + savename;
//			messageMap.put("fileName", uploadFile.getOriginalFilename());
//			messageMap.put("uploaded", 1);
//			messageMap.put("url", saveURL);
//		}
//		return messageMap;
//	}
}
