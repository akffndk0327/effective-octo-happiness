package kr.or.ddit.mvc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented //API문서로 만들어줌 ^_^
@Retention(RetentionPolicy.RUNTIME) //SOURCE: 사람한테만 . CLASS : 컴파일 한 후에 바이트 형태로 남아잇음
								   //RUNTIME : 실행후에도 남아있음 =>실시간으로 수집 간으 !  
@Target(ElementType.TYPE) //singleValue annotation. value = ElementType.TYPE =>value 생략간으 
public @interface CommandHandler { //@interface : 어노테이션. 컴파일할때 포함되어야해. 클래스에만 쓸수 있음 .
	//어디에 사용햇냐에 따라 제공함. 
	//사람과 jvm에 사용함. 
	//속성 아무것도 없어 => 마커 어노테이션 
}
