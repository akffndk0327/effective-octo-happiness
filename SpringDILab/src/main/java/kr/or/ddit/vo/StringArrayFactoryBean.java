package kr.or.ddit.vo;

import org.springframework.beans.factory.FactoryBean;

public class StringArrayFactoryBean implements FactoryBean<String[]> {

	@Override
	public String[] getObject() throws Exception {
		String[] array = new String[] {"array1","array2"};
		return array;
	}

	@Override
	public Class<?> getObjectType() {
		
		return String[].class; //.왜????
	}

	@Override
	public boolean isSingleton() { //빈의 스코프 등록되있어야해 
		return true; //false :스코프 : 프로토타입으로 되있음 -> 재활용하고싶으묜 ture로 ㅗ바꾸기
	}

}
