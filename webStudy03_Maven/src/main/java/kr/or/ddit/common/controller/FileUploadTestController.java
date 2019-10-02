package kr.or.ddit.common.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
//여기가 서버사이드!!!!!!!! 망한 코드에요...흑흑
@CommandHandler
public class FileUploadTestController {
	@URIMapping(value="/file/upload.do",method=HttpMethod.POST)
	public String upload(HttpServletRequest req, HttpServletResponse reps) throws IOException, ServletException {		
		String uploader = req.getParameter("uploader");
//		Part req.getPart("uploader");
		System.out.println(uploader);
		
		Part filePart = req.getPart("uploadFile");  //메타정보를 db에 넣야하
		//1.어디에? 웹리소스, ,파일시스템리소스 각경로의 형태, 웹을통해 접근할수잇냐 없냐
		//(/prodImages) 서블릿컨뎃스트
		String saveFolderURL="/prodImages"; //여기에 이미지 하나 저장됨 .! 
		String saveFolderPath = req.getServletContext().getRealPath(saveFolderURL);
		File saveFolder  = new File(saveFolderPath);
		
		//존재여부 확인 ! 
		if(!saveFolder.exists()) saveFolder.mkdirs();
		
		//2.어떤이름으로 저장?
		//Content-Disposition: form-data; name="uploadFile"; filename=""
		String header = filePart.getHeader("Content-Disposition");
		int firstIdx = header.indexOf("filename");
		int secondIdx = header.indexOf("=", firstIdx);
		String originalFilename = header.substring(secondIdx+1)
										.replace('"', ' ').trim(); //""사이의 파일명 들어옴
		System.out.println(originalFilename);
		String savename = UUID.randomUUID().toString();
		File saveFile = new File(saveFolder,savename);
		String fileMIME =filePart.getContentType(); //업로드한 파일의 mime 돌아와
		//검증하기 이미지가 아니면?! 
		if(!StringUtils.startsWith(fileMIME,"image/")) {//같은 코드 지만 null은 안떠
			reps.sendError(400);
			return null;
		}
//		long filesize = filePart.getSize();
			
		//저장하기 위한 파일이 필ㅎ요ㅕ해
		try(
			InputStream is = filePart.getInputStream();	
		){
			FileUtils.copyInputStreamToFile(is, saveFile);
		}//업로드 & 저장  끝
		//이미지 저장하기
		String saveFileURL = saveFolderURL+"/"+savename; //저장된 파일의 url
		req.getSession().setAttribute("saveFileURL",saveFileURL); //url만 올라감
		req.getSession().setAttribute("uploader",uploader);
		return "redirect:/13/fileUploadForm.jsp";
		
	}
}
