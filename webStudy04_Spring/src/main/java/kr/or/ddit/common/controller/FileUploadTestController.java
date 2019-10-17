package kr.or.ddit.common.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.wrapper.MultipartRequestWrapper;
import kr.or.ddit.wrapper.PartWrapper;
//여기가 서버사이드!!!!!!!! 망한 코드에요...흑흑
@Controller
public class FileUploadTestController {
	@RequestMapping(value="/file/upload.do",method=RequestMethod.POST)
	public String upload(HttpServletRequest req, HttpServletResponse reps) throws IOException, ServletException {		
		String uploader = req.getParameter("uploader");
//		Part req.getPart("uploader");
		System.out.println(uploader);
		
		//1004 파일 업로드 여부 확인하기
		if(req instanceof MultipartRequestWrapper) { //instanceof : 특정개체 확인 
			//1004
			MultipartRequestWrapper requestWrapper = (MultipartRequestWrapper) req;
//			Part filePart = req.getPart("uploadFile");  //메타정보를 db에 넣야하
			PartWrapper[] array = requestWrapper.getPartWrappers("uploadFile");
			
			//1.어디에? 웹리소스(URl생성됨.), ,파일시스템리소스 각경로의 형태, 웹을통해 접근할수잇냐 없냐
			//(/prodImages) 서블릿컨뎃스트
			String saveFolderURL="/prodImages"; //여기에 이미지 하나 저장됨 .! 
			String saveFolderPath = req.getServletContext().getRealPath(saveFolderURL); // 서블릿컨텍스트 작븡ㅁ
			File saveFolder  = new File(saveFolderPath);
			
			//존재여부 확인 ! 
			if(!saveFolder.exists()) saveFolder.mkdirs();
			
			
			//2.어떤이름으로 저장? = MultipartRequestWrapper 로 뺌.
			//1 저장명 필요
			//Content-Disposition: form-data; name="uploadFile"; filename=""
//		String header = filePart.getHeader("Content-Disposition");
//		int firstIdx = header.indexOf("filename");
//		int secondIdx = header.indexOf("=", firstIdx);
//		String originalFilename = header.substring(secondIdx+1)
//										.replace('"', ' ').trim(); //""사이의 파일명 들어옴
			List<String> saveFiles = new ArrayList<String>();
			for(PartWrapper partWrapper : array) {
				String originalFilename = partWrapper.getFileName(); //원본파일명 뽑기
				
				System.out.println(originalFilename);
				
				String savename = UUID.randomUUID().toString();
				File saveFile = new File(saveFolder,savename);
				String fileMIME =partWrapper.getContentType(); //업로드한 파일의 mime 돌아와 //wrapper는 파트의 정보 가지고있다 
				//검증하기 이미지가 아니면?! 
				if(!StringUtils.startsWith(fileMIME,"image/")) {//같은 코드 지만 null은 안떠
					reps.sendError(400);
					return null;
				}
//		long filesize = filePart.getSize();
				
				//저장하기 위한 파일이 필ㅎ요ㅕ해
				try(
					InputStream is = partWrapper.getInputStream();	
				){
					FileUtils.copyInputStreamToFile(is, saveFile);
				}//업로드 & 저장  끝
				//이미지 저장하기
				String saveFileURL = saveFolderURL+"/"+savename; //저장된 파일의 url
				saveFiles.add(saveFileURL);
				
			}
//			req.getSession().setAttribute("saveFileURL",saveFileURL); //url만 올라감
			req.getSession().setAttribute("saveFiles",saveFiles); //url만 올라감
			req.getSession().setAttribute("uploader",uploader);
		}
		
		return "redirect:/13/fileUploadForm.jsp";
		
	}
}
