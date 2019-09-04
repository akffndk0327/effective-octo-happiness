package kr.or.ddit.servlet02.service;

import java.io.File;
//3. 요청 정보 생성  ->layerd architecture
//1) raw data = 자료 확보 
//2) data 가공해서 infromation으로 생성(logic) =>결국 information이 클라이언트에게 제공할 것 
public class ImageListService {
	public String[] getImageList(){
		File imgFolder = new File("d:/contents");
		String[] images = imgFolder.list((dir,name)->{return name.endsWith(".jpg")||name.endsWith(".gif");});
		//=> 모델데이터 생성함. 모델은 images임 ! 아직 서블릿에 머물러잇어서 jsp로 보내야되 => scope
				//서비스로 보내기 
		return images;
	}
}
