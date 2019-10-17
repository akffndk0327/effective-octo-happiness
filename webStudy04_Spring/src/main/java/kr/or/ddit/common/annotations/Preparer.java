package kr.or.ddit.common.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //1.클래스 메소드 필드 등 어디에 갖다 붙일지 결정 
@Retention(RetentionPolicy.RUNTIME) //동작 하느 ㄴ시점까지 살려야한다
@Documented
public @interface Preparer {

}
