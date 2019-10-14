package kr.or.ddit.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.idol.dao.IIdolSearchDAO;
import kr.or.ddit.idol.dao.IdolSearchDAO_Mysql;
import kr.or.ddit.idol.service.IIdolSearchService;
import kr.or.ddit.idol.service.IdolSearchServiceImpl;

//xml 설정파일 대신 만드는거 => 파일 없어도 되고 밑에 처럼 쓰면 직관적이고 쉬움 .
@Configuration
@Lazy //아무것도 객체생성 안되.
@ComponentScan(basePackages="kr.or.ddit")

//자바config와 xml으 ㄹ섞기
@ImportResource("classpath:kr/or/ddit/conf/props-context.xml") // 방식이 다를 때 xml여러개 넘긼 ㅜ있어  
public class JavaConfigContext {
	//dao, service
//	@Bean
//	public IIdolSearchDAO mySqlDAO() { // 컨테이너가 관리하는 bean 이 됨.
//		return new IdolSearchDAO_Mysql();
//	}
//	
//	@Bean
//	@Scope(value="protoType")
//	public IIdolSearchService idolService() { //서비스에 다오를 어떻게 호출할건지 setter? 생성자주입?
//		return new IdolSearchServiceImpl(mySqlDAO());	
//	}
	//lazy 하고 주석 - component생성
}
