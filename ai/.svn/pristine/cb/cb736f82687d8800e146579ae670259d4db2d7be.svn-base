package kr.or.ddit.correctboard.controller;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.correctboard.service.ICorrectBoardService;
import kr.or.ddit.vo.CorrectAttatchVO;

/**
 * @author 이진희
 * @since 2019. 11. 13.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 13.      이진희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class AttatchController {
	
	@Inject
	ICorrectBoardService service;
	
	@Inject
	WebApplicationContext container;
	ServletContext application;
	
	@PostConstruct
	public void init() {
		application = container.getServletContext();
	}
	
	@RequestMapping("/correct/download.do")
	public String download(@RequestParam(required=true) int coattId,Model model) {
		
		CorrectAttatchVO attatch = service.downloadAttatch(coattId);
		model.addAttribute("attatch", attatch);
		return "downloadView";
		
	}
	
	@RequestMapping(value="/correct/imageUpload.do",
			method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> upload(
			@RequestPart(required=true,name="upload") MultipartFile uploadFile
			) throws IOException{
		Map<String, Object> messageMap = new HashMap<>();
		if(uploadFile!=null) {
			String saveFolderURL = "/images";
			String saveFolderPath = application.getRealPath(saveFolderURL);
			File saveFolder = new File(saveFolderPath);
			if(!saveFolder.exists()) saveFolder.mkdirs();
			String savename = UUID.randomUUID().toString();
			
			try(
				InputStream is = uploadFile.getInputStream();
			){
				FileUtils.copyInputStreamToFile(is,new File(saveFolder, savename));
			}
			String saveURL = application.getContextPath() + saveFolderURL + "/" +savename;
			messageMap.put("fileName", uploadFile.getOriginalFilename());
			messageMap.put("uploaded",1);
			messageMap.put("url", saveURL);
		}
		
		return messageMap;
	}
	
}
