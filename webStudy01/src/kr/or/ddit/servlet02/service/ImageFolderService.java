package kr.or.ddit.servlet02.service;

import java.io.File;
//3. 요청 정보 생성  ->layerd architecture
//1) raw data = 자료 확보 
//2) data 가공해서 infromation으로 생성(logic) =>결국 information이 클라이언트에게 제공할 것 
public class ImageFolderService {
	public String[] getImageList(){
		File imgFolder = new File("D:/A_TeachingMaterial/7.JspSpring/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/webStudy01/images");
		String[] images = imgFolder.list((dir,name)->{return name.endsWith(".jpg")||name.endsWith(".gif");});
		return images;
	}
}
