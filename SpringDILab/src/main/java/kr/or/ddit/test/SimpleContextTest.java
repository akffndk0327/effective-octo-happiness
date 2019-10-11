package kr.or.ddit.test;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.idol.dao.IIdolSearchDAO;
import kr.or.ddit.idol.service.IIdolSearchService;

//ㅕ이게 엔트리포인트 됨
public class SimpleContextTest { 
	public static void main(String[] args) {
		//객체 만들기
		ConfigurableApplicationContext container =
				new ClassPathXmlApplicationContext("kr/or/ddit/conf/simple-context.xml");
		
//		IIdolSearchService service = container.getBean("service3",IIdolSearchService.class); //필요한거 주입받은ㄱ...? / 타입 Object
//		System.out.println(service.readIdol("I002")); //갑자기?
//		IIdolSearchDAO dao1 = container.getBean(IIdolSearchDAO.class);
//		IIdolSearchDAO dao2 = container.getBean(IIdolSearchDAO.class); //하나의 빈에 2번 주입받음 
//		System.out.println(dao1); //kr.or.ddit.idol.dao.IdolSearchDAO_Mysql@3bfdc050
//		System.out.println(dao2);	//kr.or.ddit.idol.dao.IdolSearchDAO_Mysql@3bfdc050 => 같은 객체이다.
//		System.out.println(dao1 == dao2); 
//		//싱글ㅋ톤패터은의 대상은 class 이고 스프링에서는 bean이 대상임 .
//		//scope를 싱글톤 -> proto : 주입할때마다 새로운 객체 만들어짐 -> 객체 생성 시점ㅇ ㅣ뒤로 밀림.
//		
//		
//		
//		
//		//메모리를 아끼자
//		container.close(); //컨테이너의 데이터 가비지컬렉션 시켜서 다 삭제?
//		
	}
}
