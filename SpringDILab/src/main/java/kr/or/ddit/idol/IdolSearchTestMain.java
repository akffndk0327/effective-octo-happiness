package kr.or.ddit.idol;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IdolSearchTestMain {
	   public static void main(String[] args) {
	      Map<String, String[]> idolMap = new HashMap<>();
	      idolMap.put("I001", new String[] {"SES", "유진", "슈", "바다"});
	      idolMap.put("I002", new String[] {"COOL", "김성수", "유리", "이재훈"}); //이거 다오로 땜.
	      String code = "I002";
	      String[] data = idolMap.get(code); //이거 서비스로 뻄.
	      System.out.println(Arrays.toString(data)); // ui는 view 로 뺌
	   }
	}


