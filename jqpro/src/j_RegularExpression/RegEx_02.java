package j_RegularExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx_02 {
	public static void main(String[] args) {
		/*
		^ : 문자열의 시작 (자바스크립스에서 많이 쓰임 )
		$ : 문자열의 끝 
		. : 임의의 한 문자 단 \ 는 넣을수 없다 .		
		* : 문자가 없을수도 있고 무한정 있을수도 있다 .
		+ : 앞문자가 하나 이상 있어야해 
		? : 앞문자가 없거나 하나 0개또는 1개 
		[] : 문자의 집합이나 범위를 나타내며 두 문자 사이는 - 기호로범위 나타낸ㄷ. 
			[]내에서 ^가 있으면 not의 의미 가짐 
		{} : 횟수 또는 범위를 나타낸다 .
		() : 소괄호 안의 문자를 하나의 문자로 인식한다 .
		(가나){2}
		| : 패턴안에서 or연산을 수행한다.
		\s : 공백문자 의미 (소문자! )     
		\S : 공백문자를 제외한 모든 문자 
		\w : 알파벳이나 숫자 					==> 역슬래쉬 표현은 괄호 안에 넣으면 안돼 
		\W : 알파벳이나 숫자 제외한 모든 문자
		\d : 숫자[0-9]
		
		^[a-z] :무조건 영어 소문자로 시작해야한다.
		[A-Za-z] : 영문자 이어야한다.
		{2} : 무조건 ㅇ2회 반복
		{2,} : 2회이상 반복
		{2,4} : 2회이상 4회이하반복 2,3,4회 반복의 의미는 글자 수  
		*/
		String regEx01 = "[a-z]{2,3}";
		System.out.println(Pattern.matches(regEx01, "df")); //반복의 의미는 글자 수  dfss는 false 
		
		//1. 영문자가 3회 반복 되고 숫자가 1회 이상 반복되어야한다. \w
		String regEx02 = "[A-Za-z]{3}[0-9]{1,}";
		System.out.println(Pattern.matches(regEx02, "AAa012"));
		
		//2. 핸드폰번호 Lv2
		//숫자3개 -숫자4-숫자4개
		String regEx03 = "\\d{3}-\\d{4}-[0-9]{4}";
		System.out.println(Pattern.matches(regEx03, "010-6307-4955"));
		
		//3. 핸드폰번호 lv2
		//01 0,1,6,7,8,9 -첫자리0,1불가 2~9숫자3 -숫자 4개
		
		String regEx04 = "01[016789]-[2-9]{1}[0-9]{3}-\\d{4}";
		System.out.println(Pattern.matches(regEx04, "011-307-4955"));
		
		//4. 주민번호 lv3 
		/*
			숫자 2자리 0 1~9 0 1~9 - 1~4 숫자6자리 
					1 0~2 1 0~9	
						  2 0~9
						  3 0~1
		*/
		
		String regEx05 = "\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[0-1])-[1-4]{1}\\d{6}";
		System.out.println(Pattern.matches(regEx05, "930327-1555410"));
		
		//5. 이메일 정규식 FINAL
		/*
		시작은 영문자이어야 하고 영문자 + 숫자 + 특수문자(-_.\)가 있을 수도 있고
		@ 이후에 영문자또는 숫자가 1~7가 나오고 
		. 이후 영문자가 2~3자 와야한다.
		. kr없을수도 하나 있을 수도 있다 .
		*/
		System.out.println("===========이메일===========");
//		String regEx06 = "^[A-Za-z]+\\d+[-_.\\\\]*+@[A-Za-z|\\d]+{1,7}.[A-Za-z]{2,3}[.kr]?";
		String regEx07 = "^[A-Za-z][A-Za-z0-9-_.\\\\]*@\\w{1,7}\\.(or[.])[A-Za-z]{2,3}(.kr)?";
		System.out.println(Pattern.matches(regEx07, "sdf123_.-\\@nave4r.com"));
		
		System.out.println("=======================================================");
		//욕설방지 프로그램 
		String text="야 이 개나리 십장생아... 이런 조카신발 같은 경우를 봤나.";
		String result = fillterText(text);
		System.out.println(result);
	}
	
	public static String fillterText(String text){
		//1.패턴등록
		Pattern p= Pattern.compile("게나리|십장생|조카신발|병일신",Pattern.CASE_INSENSITIVE ); //문자의 그룹 묶기위해서 insensitive
		//2.매칭
		Matcher m =p.matcher(text); 
		//앞에서 한글자씩 찾아서 언제 끝날지 몰라서 
		//마지막 합쳐야해
		StringBuffer sb = new StringBuffer();
		while(m.find()){
			//찾은거 그룹짓기
			String group = m.group();
			//String에 담고 마스킹처리
			String result=maskWord(group); 
			///오류뜨면 f2누르기
			m.appendReplacement(sb, result); // 찾은걸 바꿔
		}
		//꼬리부분 붙이기
		m.appendTail(sb);
		return sb.toString();
	}

	private static String maskWord(String group) {
		//한글자씩 잘라서 개 는 살리고 나리는 마스킹
		char[] c = group.toCharArray(); //★
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < c.length; i++) {
			if(i==0){
				sb.append(c[i]);
			}else{
				sb.append("★");
			}
		}
		return sb.toString();
	}
	
}
