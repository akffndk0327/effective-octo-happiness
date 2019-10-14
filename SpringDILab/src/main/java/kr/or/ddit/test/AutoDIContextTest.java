package kr.or.ddit.test;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.idol.service.IIdolSearchService;

public class AutoDIContextTest {
	public static void main(String[] args) {
		ConfigurableApplicationContext container = 
				new  ClassPathXmlApplicationContext("kr/or/ddit/conf/autoDi-context.xml");
		
		int count = container.getBeanDefinitionCount(); //빈이 몇개 들어있나 확인가능
		System.out.println(count); //2개는 다오, 서비스, 6개는 post processor
		String[] names = container.getBeanDefinitionNames();
		for(String name : names ) {
			System.err.println(name);
		}
		IIdolSearchService service = container.getBean(IIdolSearchService.class);
		String info = service.readIdol("I001");
		System.out.println(info);
	}
}
