package kr.or.ddit.utils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.text.MaskFormatter;

import org.apache.commons.lang3.ClassUtils;

/**
 *   Marshalling : 특정 언어로 표현된 데이터(java object)를 범용 표현 방식(json/xml 문자열기반)으로 변환하는 작업.    
 * 
 *
 */
public class MarshallingUtils {
   public String marshalling(Object target) {
      //45,"test"는 마샬링 불가능하다 기본형이고 값만 가지고는 아무것도 못한다 
      //int(Integer),double(Double),char,String,StringBuffer
      if(ClassUtils.isPrimitiveOrWrapper(target.getClass()) || //마샬링불가능 
         CharSequence.class.isAssignableFrom(target.getClass())) { //누구로부터 할당받을수있는지 두개의 조건중 하나라도 걸리면 마샬링 불가능
         throw new IllegalArgumentException("마샬링이 불가능한 데이터");
      }
      return marshallingObjectToJson(target);
      
   }

   private String marshallingObjectToJson(Object target) {
//      "c",42,true,"ste",{},[],{}
      if(target==null) return null;
      
      Class<?> targetType = target.getClass();
      String value = null;
      //CharSequence.class.isAssignableFrom(targetType) ->"ste"타입을 잡음
      //ClassUtils.isAssignable(targetType, Character.class, true) ->기본형인 문자타입도 잡힘
      if(CharSequence.class.isAssignableFrom(targetType) || 
               ClassUtils.isAssignable(targetType, Character.class, true)) {
         value = String.format("\"%s\"",target);
      }else if(ClassUtils.isPrimitiveOrWrapper(targetType)){ //위에서 문자를 잡았으므로 문자를 제외한 type들이 걸린다
         value = target.toString();
      }else if(targetType.isArray()) {

//         Object[] array = (Object[])target; //기본형의배열인지 참조형배열인지를 구분 
         value = marshallingArrayToJson(target);

      }else if(target instanceof Map) {
         Map map = (Map)target;
         value = marshallingMapToJson(map);
      }else {
         StringBuffer json = new StringBuffer("{");
         Field[] fields = targetType.getDeclaredFields();
         
         for(Field tmp:fields) {
            String name = tmp.getName();
            try {
               PropertyDescriptor pd = new PropertyDescriptor(name, targetType);
               Object propValue = pd.getReadMethod().invoke(target);
               json.append(String.format(PROPPATTERN, name, marshallingObjectToJson(propValue)));
            } catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
               //현재 타켓이 자바빈 규약에 의해서 만들어진 객체가 아니다.
               System.err.println(e.getMessage());
               continue;
            }
         }
         int lastIndex = json.lastIndexOf(",");
         if(lastIndex==json.length()-1) {
            json.deleteCharAt(lastIndex);
         }
         json.append("}");
         value = json.toString();
      }
      
      return value;
      
   }
   
   private final String PROPPATTERN = "\"%s\":%s,";
   
   private String marshallingMapToJson(Map map) {
      if(map==null) return null;
      Iterator<?> keys= map.keySet().iterator();
      StringBuffer json = new StringBuffer("{");
      while (keys.hasNext()) {
         Object key = (Object) keys.next();
         Object value = map.get(key);;
         json.append(String.format(PROPPATTERN,key,marshallingObjectToJson(value)));
      }
      int lastIndex = json.lastIndexOf(",");
      if(lastIndex==json.length()-1) {
         json.deleteCharAt(lastIndex);
      }
      json.append("}");
      return json.toString();
   }

   public String marshallingArrayToJson(Object array) {
      StringBuffer json = new StringBuffer("[");
      if(array!=null) {
         int length = Array.getLength(array);
         for(int i=0; i<length; i++) {
            json.append(marshallingObjectToJson(Array.get(array, i))+",");
         }
         int lastIndex = json.lastIndexOf(",");
         if(lastIndex==json.length()-1) {
            json.deleteCharAt(lastIndex);
         }
      }
      
      json.append("]");
      return json.toString();
   }

   public static class TestVO{
      private Integer number= new Integer(34);
      private int num = 23;
      private int[] numbers = new int[] {1, 2, 3};
      private String[] array = new String[] {"a","b"};
      private Map<String, Object> map = new HashMap<String, Object>();
      {
         map.put("key1",number);
         map.put("key2",num);
         map.put("key3",array);
//         map.put("key4",new TestVO());
         
      }
      
      public int[] getNumbers() {
         return numbers;
      }
      public void setNumbers(int[] numbers) {
         this.numbers = numbers;
      }
      public Integer getNumber() {
         return number;
      }
      public void setNumber(Integer number) {
         this.number = number;
      }
      public int getNum() {
         return num;
      }
      public void setNum(int num) {
         this.num = num;
      }
      public String[] getArray() {
         return array;
      }
      public void setArray(String[] array) {
         this.array = array;
      }
      public Map<String, Object> getMap() {
         return map;
      }
      public void setMap(Map<String, Object> map) {
         this.map = map;
      }
      
      
      
   }
   
   
   public static void main(String[] args) {
      TestVO test = new TestVO();
      String json = new MarshallingUtils().marshalling(test);
      System.out.println(json);
//      Object target = "Text";
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      
//      target = 'c';
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      
//      target = new Character('a');
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      
//      target = new StringBuffer("text");
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      
//      target = 32;
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      
//      target = true;
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      
//      target = null;
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      
//      target = new char[] {'a','b'};
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      
//      target = new Integer[] {23,65};
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      
//      target = new String[] {};
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      
//      target = new String[] {"test","value"};
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(target));
//      
//      Map<String, Object> map = new HashMap<String, Object>();
//      map.put("key1", 34);
//      map.put("key2", "test");
//      map.put("key3", new Character('c'));
//      System.out.println(new MarshallingUtils().marshallingObjectToJson(map));
      
      
      
      
   }
}