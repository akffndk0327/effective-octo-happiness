package kr.or.ddit.enums;

public enum OsType{
	LINUX("리눅스"), WINDOWS("윈도우"),ANDROID("안드로이드"),IPHONE("IOS"),OTHER("기타OS");
	
	OsType(String name){
		this.name = name;
	}
	
	private String name;
	public String getName(){
		return this.name;
	}
	
	public static OsType searchOS(String userAgent) {
		OsType result = OTHER;
		for(OsType tmp : values()) {
			if(userAgent.toUpperCase().contains(tmp.name())) {
				result = tmp;
				if (result.equals(ANDROID)) {
					break;
				}
			}
		}
		return result;
	}
}