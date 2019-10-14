package kr.or.ddit.test;

import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.Resource;

import kr.or.ddit.vo.VariousDIVO;

public class VariousDIContextTest {
	public static void main(String[] args) throws IOException { //application entry point
		ConfigurableApplicationContext container =
				new GenericXmlApplicationContext("classpath:kr/or/ddit/conf/variousDI-context.xml");
		 //xml이 어디에잇는지 구ㅠ현항ㄴ되.
		
		VariousDIVO vo1 = container.getBean("variousDIVO1",VariousDIVO.class); //생성자 주입
		VariousDIVO vo2 = container.getBean("variousDIVO2",VariousDIVO.class); //setter 주입 
		
		System.out.println(vo1 == vo2); //서로의 해쉬코드 비교  true : 싱글톤 같은객체이다. true ->false 싱글톤이 대상이 bean이다
		System.out.println(vo1);
		System.out.println(vo1.getFile().exists());//파일객체 받아올수있어 => true: 파일객체잘 만ㄷ르어짐. 프로퍼티 edior 가 실행됬음을 알아봣댜.
		System.out.println(vo1.getFile().getAbsolutePath()); //xml에서 file 경로 바꿈 => 경로를 동적으로 바꿔서 보여줌 
		//xml에서의 문자열- 프로퍼티 edior사용 - 주소 보여줌
		
//		모든 컨테이너 구현체는 그 자체로 ResourceLoader 이다. => 모든컨에티너가 resourceloader를 구현하고 있다.
//		smart resource loader를 사용시,
//		classpath:, file:, http:(URI scheme)의 접두어 형태로
//		리소스의 종류를 식별하여 원하는 파일(이미지, 음악, 동영상 등 ) 로딩할 수 있음 
		Resource res1 =  container.getResource("file://d:/contents/Desert.jpg"); //파일시스템경로 
		System.out.println(res1.getClass().getSimpleName());
		Resource res2 =  container.getResource("classpath:kr/or/ddit/images/Lighthouse.jpg"); //내부패키지에서 찾을 때 
		System.out.println(res2.getClass().getSimpleName());
		Resource res3 = container.getResource("file://d:/contents/Desert.jpg");
		System.out.println(res3.getClass().getSimpleName());
		Resource res4 = container.getResource
				("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
		System.out.println(res4.getClass().getSimpleName()); 
		System.out.println(res4.contentLength()+""); //예외던져
		System.out.println(res4.exists());
		System.out.println(res4.getFilename());
//		System.out.println(res4.getFile()); //File System에서 가져올수있어 서 암안됨
		System.out.println(res1.getFile()); //res1오류 이유 : 접두어가 없어서 file://넣어주기 
		//구현체 동적으로 처리  Resource로 
		
		
	}
}
