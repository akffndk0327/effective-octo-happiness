package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.vo.Attatch2VO;
import kr.or.ddit.wrapper.MultipartRequestWrapper;
import kr.or.ddit.wrapper.PartWrapper;

@CommandHandler
public class AttatctAndImageController {
	IBoardService service = new BoardServiceImpl();
	
	File saveFolder = new File("d:/saveFiles");
	//파일 다운 받기 
	@URIMapping("/board/download.do")
	public String download(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//메타데이터 save 파일 , 미들티어의 진자 파일
		//응답 스트링 
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
	
	@URIMapping(value="/board/imageUpload.do", method=HttpMethod.POST)
	public String upload(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		//업로드되고 잇는 파일데이터 잡기 
		//Wrapper형태로 전달하기
		if(req instanceof MultipartRequestWrapper) {
			PartWrapper uploadFile = ((MultipartRequestWrapper) req).getPartWrappper("upload");
			if(uploadFile!=null) {
				//업로드 하려면 저장해야하닌까 위치 지정해야지
				//위치잡고 
				String saveFolderURL="/boardImages";
				String saveFolderPath = req.getServletContext().getRealPath(saveFolderURL); // 서블릿컨텍스트 작븡ㅁ
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
				String saveURL = req.getContextPath()+saveFolderURL+"/"+savename;//서브사이트 절대경로생김 ->front img에서 써야함 
				//Map사용하기
				Map<String, Object> messageMap = new HashMap<String, Object>();
				messageMap.put("fileName",uploadFile.getFileName()); //원본파일명 뽑기
				messageMap.put("uploaded","1");
				messageMap.put("url",saveURL);
				resp.setContentType("application/json;charset=UTF-8");
				//마샬링하고 직렬화해서 내보ㄱ내기 
				ObjectMapper mapper = new ObjectMapper(); 
				try(
					PrintWriter out =resp.getWriter();
				){
					mapper.writeValue(out, messageMap);
				}
				
			}
		}
		
		return null;
		
	}
	
	
}
