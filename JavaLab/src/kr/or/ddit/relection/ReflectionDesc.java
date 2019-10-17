package kr.or.ddit.relection;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

import kr.or.ddit.reflect.ReflectionTest;

public class ReflectionDesc {
	public static void main(String[] args) {
		// marshalling : java,c javascript ->xml,json형태로 바꾸는 거
		Test t = new Test();
		t.setNumber(42);
		t.setStr("test");
//		{"number" : 42, "str":"test "} =>하드코딩...
		String propPattern = "\"%s\":%s,";
		StringBuffer json = new StringBuffer();
		try { // 멀티캐치로 묶음.
			json.append("{");
			// 타입먼저 가져오기.
			Class<Test> tType = Test.class;
			Field[] fields = tType.getDeclaredFields();
			for (Field tmp : fields) {
				String name = tmp.getName();
				tmp.setAccessible(true); // 사용하는 순간 public 으로 바뀐. 외부에서 접근 간으 해짐 .
				Object retVal;
				retVal = tmp.get(t);
				Class<?> fldType = tmp.getType(); // 타입가져오기
				String formatValue = null;
				if (retVal != null && fldType.equals(String.class)) {
					// 문자열이면...?
					formatValue = "\"" + retVal + "\"";
				} else {
					// 다른타입...?
//				formatValue = retVal.toString();
					formatValue = Objects.toString(retVal);

				}
//			json.append(String.format(propPattern, name,retVal))
				json.append(String.format(propPattern, name, formatValue));
				//for문안에 한쌍의 데이터 있어
			}//for end 
			if(json.lastIndexOf(",") ==json.length()-1)
				json.deleteCharAt(json.lastIndexOf(","));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException();
		} // ()안에 주인객체 주기

		json.append("}");
		System.out.println(json);
	}
	public static void reflectionTest() {
		Test t = new Test();
		t.setNumber(42);
		t.setStr("test");
		System.out.println(t.merge());
		
		 Object instance = ReflectionTest.getObject();
		 
		 Class<?> instanceType = instance.getClass(); //틀이 돌아옴 = class 
		 System.out.println("instanceType : "+instanceType.getName());
		 //리플렉션 : 불확실성을 기반으로 함. 
		 //실행전까진 아는 건 아무것도 없어 => 제네릭타입도 ? 로 입력하고 상수풀에 입력됨.
//		Field[] fields =  instanceType.getFields(); //특성 찾아가야해. Filed : 전역변수 []이유는 아무것도 모르닌까. 변수에 전역변수에 모든것이 다 있을 것. 
		Field[] fields =  instanceType.getDeclaredFields(); //This includes public, protected, default (package) access, and private fields
		for(Field fld : fields) { //모든 전역변수에 하나씩접근 간으 
			String name  =fld.getName();
			Class<?> fldType = fld.getType();
			System.out.printf("%s %s;\n", fldType.getSimpleName(),name); //2차 reflection. Object 면 getter,setter못씀
			
			try {
				PropertyDescriptor pd = new PropertyDescriptor(name, instanceType);
				Object retVal = pd.getReadMethod().invoke(instance); //getter 
				System.err.printf("%s 호출발환 : %s\n", pd.getReadMethod().getName(),retVal);
			} catch (IntrospectionException e1) { //class name , method name, method name, wrong type signature
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}  //프로퍼티 하나에 대한 설명을 가직 ㅗ있음 
			catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String getter = "get"+name.substring(0,1).toUpperCase()+name.substring(1);
			
			//instanceType 행동정보 꺼내기
			//불확실해서 단계적으로 안전장치 해놈 ............................
			try {
				Method method = instanceType.getDeclaredMethod(getter);
				//호출해야 그 안에 값이 돌아옴 메소드 호출 : invoke
//				member.getMem_id(); 이거와 같음
				Object retValue = method.invoke(instance); //여기서 주인객체가 넘어감 
				System.out.printf("%s 호출반환 : %s\n", method.getName(),retValue); //문자가 아닌 메소드형태로 취급해야해 instance 가 갖고있는 
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //Class<?> : 가변파라미터 => 넘길거 굳이없어  
			catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) { //파라미터 잘못호출 
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) { // invoke(주인객체) 넘겨야하는데 엉뚱한ㄱ거 넘길때 
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		}
		 
	}
	public static class Test{
		private String str;
		private Integer number;
		public String merge(){
			return str+number;
		}
		
		public String getStr() {
			return str;
		}
		public void setStr(String str) {
			this.str = str;
		}
		public Integer getNumber() {
			return number;
		}
		public void setNumber(Integer number) {
			this.number = number;
		}
		
	}
}
