package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.Attatch2VO;

@Controller
public class AttatctAndImageController {
	@Inject
	IBoardService service ;
	
	@Inject
	WebApplicationContext container;
	ServletContext application;
	
	File saveFolder;
	@PostConstruct
	public void init() {
		application = container.getServletContext();
		saveFolder = new File("d:/saveFiles");
	}
	
	//파일 다운 받기 
	@RequestMapping(value="/board/download.do")
	public String download(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//메타데이터 save 파일 , 미들티어의 진자 파일
//		응답 스트링 
		String what = req.getParameter("what"); // int 
		if(StringUtils.isBlank(what)) {
			resp.sendError(400);
			return null;
		}
		Attatch2VO attatch = service.downloadAttatch(Integer.parseInt(what));
		File downloadFile = new File(saveFolder,attatch.getAtt_savename());
		resp.setContentType("application/octect-stream"); //마임설정 octect : 8비트 바이트스트림이라는 의미
		resp.setHeader("Content-Length",attatch.getAtt_filesize()+""); //응답데이터으 ㅣ길이
		String filename = attatch.getAtt_filename();
//		filename = URLEncoder.encode(filename,"UTF-8");
		filename =  new String(filename.getBytes(),"ISO-8859-1"); //바이트 뒤에 바이터 붙여서 2바이트로 만듬
		resp.setHeader("Content-Disposition", "attatctment;filename=\""+filename+"\"");
		//식별자 1, inline : 바로 실행, attatchment: 파일 가져가돼 다운받아서 써라 v 이거 써야함 
		
		try(
			OutputStream os = resp.getOutputStream();
		){
			FileUtils.copyFile(downloadFile, os);
			
		}
		
		return null;
	}
	
	@RequestMapping(value="/board/imageUpload.do", method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE
			)
	public Map<String, Object> upload(@RequestParam(name="upload",required=true)MultipartFile uploadFile) throws IOException {
		//업로드되고 잇는 파일데이터 잡기 
		//Wrapper형태로 전달하기
//		Map<String, Object> messageMap = new HashMap<>();
//		if(req instanceof MultipartRequestWrapper) {
//			PartWrapper uploadFile = ((MultipartRequestWrapper) req).getPartWrappper("upload");
		Map<String, Object> messageMap = new HashMap<String, Object>();
			if(uploadFile!=null) {
				//업로드 하려면 저장해야하닌까 위치 지정해야지
				//위치잡고 
				String saveFolderURL="/boardImages";
//				String saveFolderPath = req.getServletContext().getRealPath(saveFolderURL); // 서블릿컨텍스트 작븡ㅁ
				String saveFolderPath = application.getRealPath(saveFolderURL); // 서블릿컨텍스트 작븡ㅁ
				File saveFolder = new File(saveFolderPath);
				if(!saveFolder.exists()) saveFolder.mkdirs();					
				//2. 저장하기
				//저장명 
				String savename = UUID.randomUUID().toString();
				try(
					InputStream is = uploadFile.getInputStream();
				){
					FileUtils.copyInputStreamToFile(is, new File(saveFolder,savename)); //예외는 던지기 
				}//
				String saveURL = application.getContextPath()+saveFolderURL+"/"+savename;//서브사이트 절대경로생김 ->front img에서 써야함 
				//Map사용하기
				messageMap.put("fileName",uploadFile.getOriginalFilename()); //원본파일명 뽑기
				messageMap.put("uploaded","1");
				messageMap.put("url",saveURL);
//				resp.setContentType("application/json;charset=UTF-8");
				
				//마샬링하고 직렬화해서 내보ㄱ내기 
//				ObjectMapper mapper = new ObjectMapper(); 
//				try(
//					PrintWriter out =resp.getWriter();
//				){
//					mapper.writeValue(out, messageMap);
//				}
				
			}
		
		
		return messageMap;
		
	}
	
	
}
