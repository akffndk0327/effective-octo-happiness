package kr.or.ddit.relection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.ClassUtils;

/**
 * Marshalling : 특정 언어로 표현된 데이터(java object)를범용 표현 방식(json/xml)으로 변환하는 작업 
 * 
 */
public class MarshallingUtils {
	public String marshalling(Object target) {
		//"test", 42, 'c' => 값만 가져도 안되고 , int(Integer), double(Double), char, String, StringBuffer
//		ClassUtils.isPrimitiveOrWrapper(target.getClass())
		//파라미터 검증하기 
		if(ClassUtils.isPrimitiveOrWrapper(target.getClass()) || //.getClass():타입 알수있어
//		if(target.getClass().isPrimitive() || //.getClass():타입 알수있어
//		target.getClass().equals(String.class)
		CharSequence.class.isAssignableFrom(target.getClass())) {//Assign : 할당  => 이게 부모인지 보겟다.
			throw new IllegalArgumentException("마샬링이 불가능한 데이낱");
		}
		return marshallingObjectToJson(target);
	}

	private String marshallingObjectToJson(Object target) {
//		"c", 42, true, "str", {}, [],{}
		if(target ==null) return null;
		Class<?> targetType =target.getClass();
		String value = null; //왜 갑자기 value?
		
		if(CharSequence.class.isAssignableFrom(targetType) ||
				ClassUtils.isAssignable(targetType, Character.class, true)) {
			//어떤 녀석 할당?
			value =String.format("\"%s\"",target);
		}else if(ClassUtils.isPrimitiveOrWrapper(targetType)) { //문자가 아닌 나머지 
			value= target.toString(); 
			
		}else if(targetType.isArray()) { //객체? 배열? 맵?
			//true면 배열임.
//			if(targetType.getComponentType().isPrimitive()) {
//			}else {
//				Object[] array = (Object[]) target;
				value = marsharllingArrayToJson(target);  // ???시그니처...?
				

		}else if(target instanceof Map) {
			Map map = (Map) target;
			value = marshallingMapToJson(map);
		}else {
			StringBuffer json = new StringBuffer("{");
			//프로퍼티 찾아내기 
			Field[] fields = targetType.getDeclaredFields();
			for(Field tmp :fields) {
				String name = tmp.getName();
				try {
					PropertyDescriptor pd = new PropertyDescriptor(name, targetType);
					Object propValue = pd.getReadMethod().invoke(target);
//					String.format(PROPPATTERN, name,marshallingObjectToJson(propValue));
					json.append(String.format(PROPPATTERN, name,marshallingObjectToJson(propValue)));
					
				} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					System.err.println(e.getMessage());
					continue;
				}
			}
			int lastIndex = json.lastIndexOf(",");
			if(lastIndex == json.length()-1) json.deleteCharAt(lastIndex);
			json.append("}");
			value = json.toString();
		}
			
		return value;
	}
	
	private final String PROPPATTERN="\"%s\":%s";
	
	private String marshallingMapToJson(Map map) {
		if(map == null)return null;
		Iterator<?> keys =  map.keySet().iterator();
		StringBuffer json = new StringBuffer("{");
				while (keys.hasNext()) {
			Object key = (Object) keys.next(); //object : 프로퍼ㅌ티명  : key value
			//포맷팅
			Object value= map.get(key);
			String.format(PROPPATTERN, key,marshallingObjectToJson(value));
		}
				int lastIndex = json.lastIndexOf(",");
				if(lastIndex == json.length()-1) json.deleteCharAt(lastIndex);
				json.append("}");
		//		{"key" : "value"}
		return json.toString();
	}

	public String marsharllingArrayToJson(Object array) { //배열 하나에 대해서 외부에서도 사용가ㅡㅇ함. 
		StringBuffer json = new StringBuffer("[");
		if(array !=null) { // array 검증 
			int length = Array.getLength(array);
		//array 요소 만들기
			for(int i=0;i<length; i++) { //배열의 요소 다 접근하기 위해 만듬
				json.append(marshallingObjectToJson(Array.get(array, i))+",");
			}
			int lastindex = json.lastIndexOf(",");
			if(lastindex ==json.length()-1)
				json.deleteCharAt(lastindex);
		}
		json.append("]");
		return json.toString();
	}

}
