package kr.or.ddit.test;

import java.util.Arrays;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.vo.CollectionDIVO;

public class CollectionDIContextTest {
	public static void main(String[] args) {
		try(
//				classpath:kr/or/ddit/conf/variousDI-context.xml
		//컨테이너 계층 ㅕㅇ성
		//child container 에서 공통 사용될 공통 빈 등록 
		ConfigurableApplicationContext root = //부보 
				new ClassPathXmlApplicationContext("kr/or/ddit/conf/variousDI-context.xml");
		ConfigurableApplicationContext container = //w자식
				new ClassPathXmlApplicationContext
				(new String[] {"kr/or/ddit/conf/collectionDI-context.xml"},root); ///이미 classPAth로 받으닌까 경로에 classpath: 안써도 되
		ConfigurableApplicationContext container2 = //w자식
				new ClassPathXmlApplicationContext
				(new String[] {"kr/or/ddit/conf/simple-context.xml"},root);
				//임포트방법2 , 부모컨테이너 만들어서 상속받아서 쓰기. = > 모듈화 +빈 재활용 장점
		//3. 컨테이너 하나 더 복사해서 simple xml가져오기  
		//임포트방법1 : 분산시켜서 모듈화 가능 
		){
			CollectionDIVO vo1  =container.getBean("vo2",CollectionDIVO.class);
			System.out.println(Arrays.toString(vo1.getArray()));
			System.out.println(vo1.getList());
			System.out.println(vo1.getSet());
			System.out.println(vo1.getMap());
			System.out.println(vo1.getProps()); //securedService.xml 넣으닌까  프로퍼티 많ㅇ아짐 
		}
	}
}
