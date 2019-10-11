package kr.or.ddit.idol.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IdolSearchDAO_Mysql implements IIdolSearchDAO {
	
	public IdolSearchDAO_Mysql() {
		super();
		System.out.printf("%s 객체생성 \n", getClass().getSimpleName());
	}
	
	Map<String, String[]> idolMap = new HashMap<>();
	{
	    idolMap.put("I001", new String[] {"SES", "유진", "슈", "바다"});
	    idolMap.put("I002", new String[] {"COOL", "김성수", "유리", "이재훈"});
	    System.out.println("코드블럭이다");
	}
	
	public void init() { //콜백메서드처럼 사용됨
		System.out.println("lifecycle callback");
	}
	
	
	@Override
	public String[] selectIdol(String code) {
		return idolMap.get(code);
	}

	@Override
	public List<String[]> selectIdolList() {
		//전체 값 가져오기
		List<String[]> list = new ArrayList<String[]>(idolMap.values()); //collection으로 리스트 만들때 !!
		
		return list;
	}

}
