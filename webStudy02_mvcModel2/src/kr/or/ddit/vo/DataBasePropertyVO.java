package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * Java bean 규약 
 * vo(Value Object):데이터 담기위해 /dto(Data Transfer Object) :데이터 전송 /javaBean : 어떤 단계로 정의 되어있는지 알아야 vo 완성할수잇엉 /model  
 * 1. value를 담을수 있는 property 정의 
 * 2. 정해진 룰에 의해서만 사용할 수 있도록 property 캡슐화 함 .
 * 3. 캡슐화 된 프로퍼티에 접근 할 수 있는 인터페이스 정의 
 *  getter/setter 
 *  get[set] 프로퍼티명(initical capitalize : 변수명 첫 글자느 대문자 ! ) ex) getProperty_name
 * 4. 객체의 상태를 비교할 수 있는 방법 제공(equals 재정의)
 * 5. 객체의 상태를 확인할 수 있는 방법 제공(toString 재정의) 
 * 6. 객체의 상태를 직렬화 할 수 있도록
 * 직렬화 : 객체를 전송이나 저장을 목적으로 바이트 집합의 형태로 변환하는 작업 
 * 역직렬화 : 바이트 집합의 형태로 전송되었거나 저장되었던 객체르 다시 원래의 형태로 복원하는 작업 
 */
public class DataBasePropertyVO implements Serializable{
	private String property_name;
	private String property_value;
	private String description;
	
	public String getProperty_name() {
		return property_name;
	}
	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}
	public String getProperty_value() {
		return property_value;
	}
	public void setProperty_value(String property_value) {
		this.property_value = property_value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((property_name == null) ? 0 : property_name.hashCode());
		result = prime * result + ((property_value == null) ? 0 : property_value.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataBasePropertyVO other = (DataBasePropertyVO) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (property_name == null) {
			if (other.property_name != null)
				return false;
		} else if (!property_name.equals(other.property_name))
			return false;
		if (property_value == null) {
			if (other.property_value != null)
				return false;
		} else if (!property_value.equals(other.property_value))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DataBasePropertyVO [property_name=" + property_name + ", property_value=" + property_value + "]";
	}
	
	
}
