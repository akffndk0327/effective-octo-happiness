package kr.or.ddit.common.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadTestController {
	@Inject
	WebApplicationContext container;
	
//	1. 저장위치 (/prodImages)
	String saveFolderURL = "/prodImages";
	private File saveFolder;
	
	@PostConstruct
	public void init() {
		String saveFolderPath = container.getServletContext().getRealPath(saveFolderURL);
		saveFolder = new File(saveFolderPath);
		if(!saveFolder.exists()) saveFolder.mkdirs();
	}
	
	@RequestMapping(value="/file/upload.do", method=RequestMethod.POST)
	public String upload(
			@RequestParam(required=false) String uploader
			, MultipartFile[] uploadFile
			, HttpServletResponse resp
			, HttpSession session
		) throws IOException {
		System.out.println(uploader);

//		2. 저장명
		if(uploadFile!=null) {
			List<String> saveFiles = new ArrayList<>();
			for(MultipartFile file : uploadFile ) {
				
				String originalFilename = file.getOriginalFilename();
				System.out.println(originalFilename);
				String savename = UUID.randomUUID().toString();
				File saveFile = new File(saveFolder, savename);
				String fileMIME = file.getContentType();
				if(!StringUtils.startsWith(fileMIME, "image/")) {
					resp.sendError(400);
					return null;
				}
				long filesize = file.getSize();
				try(
						InputStream is = file.getInputStream();	
						){
					FileUtils.copyInputStreamToFile(is, saveFile);
				}
				
				String saveFileURL = saveFolderURL+"/"+savename;
				saveFiles.add(saveFileURL);
			}
			
			session.setAttribute("saveFiles", saveFiles);
			session.setAttribute("uploader", uploader);
			
		}
		
		return "redirect:/13/fileUploadForm.jsp";
	}
}







