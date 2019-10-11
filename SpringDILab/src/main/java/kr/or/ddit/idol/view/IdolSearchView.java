package kr.or.ddit.idol.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.idol.dao.IIdolSearchDAO;
import kr.or.ddit.idol.dao.IdolSearchDAO_Oracle;
import kr.or.ddit.idol.service.IIdolSearchService;
import kr.or.ddit.idol.service.IdolSearchServiceImpl;

public class IdolSearchView {
	public static void main(String[] args) {
//		IIdolSearchDAO dao = new IdolSearchDAO_Oracle(); //다오ㄱ ㅏ전략 ?
//		//서비스연결
//		IIdolSearchService service = new IdolSearchServiceImpl(dao ); //전략주입 받고있음
		//위에 2개 스프링으로 넘기기 
		
		//2. 스프링컨테이너 연결하기 (객체생성)
		ApplicationContext contaitner = 
				new ClassPathXmlApplicationContext("kr/or/ddit/idol.conf/idol-context.xml");  //리소스에있는 설정파일 위치 알려줘야해 classpath ㅣㄹ소스형탱.ㅁ 
		IIdolSearchService service = contaitner.getBean(IIdolSearchService.class);
		// => 결합력 없음 ! xml이 결합력 떠않음. 컴파일도 필요없음 .! ^_^
		
		String code = "I001";
		String info = service.readIdol(code);
		System.out.println(info);
	}
	//전략주입자 역할하는애가 결합력 ㄷ ㅏ 떠않음.
	
}
