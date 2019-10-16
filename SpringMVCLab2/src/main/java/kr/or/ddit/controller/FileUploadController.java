	package kr.or.ddit.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.service.FileUploadService;

@Controller //등록 & 정보수집
@RequestMapping("/fileupload")
public class FileUploadController {
	@Inject //하위에서 상위으 ㅣ빈 주입받으루싱ㅆ어 
	FileUploadService service;
	
	
	
	@RequestMapping(method=RequestMethod.GET)// //업로드 폼 필요해
	public String get() {
		return "file/form";
	}
	
	@RequestMapping(method=RequestMethod.POST) //온 예외는 어댑터가 가져감 
	public String post(FileVO fileVO, //리쿼스트 스코프가 아닌 파라미터로 븉여버림 왜? 리다이렉션되도 파라미터 안사라져서
				RedirectAttributes redirectAttributes) throws IllegalStateException, IOException { //업로드 파일를 잡아서 처리
		//핸들러 어댑터가 알아서 함.
		//에외2 파일이 안들어갈수잇어 파라미터 리콰이어드 false
		//업로드 하기 전에 어디에 어떤 이름으로 할지 결정학 ㅣ! 
//		String savename = UUID.randomUUID().toString();
//		fileVO.getUploadFile().transferTo(new File(saveFolder,savename)); //여기서 파일업로드 끝 
//		String saveUrl =clientUrl+savename; //위에서 폴더만들게 
		
		//1015 오후쉅
		service.upload(fileVO);
		
		redirectAttributes.addFlashAttribute("fileVO",fileVO); //내가 안지워도 자동으로 지워줌 saveUrl -> fileVO
		//입출렫ㄱ스트림받어
		//img 태그 갖는 view 필요 
//		Redirect로  fileView.jsp로 이동 
//		return "redirect:/fileView.jsp"; //webapp아래에 있어야함 톰캣으로 타서 바로 탐 스프링동작x
		return "redirect:/fileupload"; //디스패쳐타고  스프링도 옴
//		redirectAttributes 구조를 사용하기 위해서느 ㄴ스프링의 FrontController를 타야함.
		//모델데이터 가져가야해 
//		세션스코프 받아야해 어댑터에서 -세션에서 받으면 나중에 지워야함... flashattribute...
		
		
	}
}
