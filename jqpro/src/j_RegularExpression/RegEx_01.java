package j_RegularExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx_01 {
	public static void main(String[] args) {
		/*
		1. 정규식이란 ? 형태맞는지 확인 구글링하면 나오는데 고객들이 이거 추가해줘 할때 내가 만들어야돼 
		  - 텍스트 데이터 중에서 원하는 조건과 일치하는 문자열을 찾아내기 위해
		  	사용하는 것 
		  
		  - 정규식의 구성
		  	: Pattern이라는 클래스를 이용해서 정규식을 정의한다 .
		  	: Matcher클래스를 ㄹ이용해서 Pattern과 데이터를 비교한다 .
		*/
		//1. 패턴 등록 **이게 중요함  [a-z]*
		Pattern p = Pattern.compile("[a-z]*") ; // * : 영어소문자로 이뤄졋거나 없어도 된다 의미
		
		//2 매칭 Mathching
		Matcher m =p.matcher("aSdf");
		
		System.out.println(m.matches());
		
		
		
	}
}
