package kr.or.ddit.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.controller.FileVO;

@Service
public class FileUploadService {
	@Inject //이렇게 쓰면 알아서 컨테이너 객체 주입
	WebApplicationContext container;
	ServletContext application ;
	
	@Value("#{appInfo.imagesUrl}") //로딩방법1
	private String saveFolderUrl ;
	@Value("#{appInfo['imagesClientUrl']}") //utill이용한 방법2
	private String clientUrl; //xml 프로퍼티 사용할수있어야해 imageUrl,clientUrl
	
	File saveFolder;
	@PostConstruct //객체생성이후에 실행되믄 콜백
	public void init()	{
		application=  container.getServletContext(); //돌아갈려면 실행코드로 돌아가야함- inject 실행되고 되야함 
		String folderPath = application.getRealPath(saveFolderUrl); //res랑 resources랑 분리해야앟ㅁ
		saveFolder = new File(folderPath); //url - servletcontext필요해 근데 없어...
		if(!saveFolder.exists()) saveFolder.mkdirs(); // 폴더생성 
	} //라이프사이드콜백으로  이거 실행되고 컨테이너 채움,
	
	public void upload(FileVO fileVO) throws IllegalStateException, IOException {
		//binary 미들티어에
		fileVO.transfer(saveFolder); //바이너리에 저장 끝남. 
		//meta 
		System.out.println("메타데이터 저.장!");
		
	}
}
