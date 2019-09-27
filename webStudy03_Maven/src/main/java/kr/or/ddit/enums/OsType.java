package kr.or.ddit.enums;

public enum OsType {
		LINUX("리눅스"), WINDOWS("윈도우"), OTHER("기타"); //이름과동일한게 할당되어있어
		//생성자 만들어
		OsType(String name){
			this.name = name;
		}
		private String name;
		
		public String getName() {
			return this.name;
		}
		//만약...
		public static OsType searchOS(String userAgent) {
			OsType result = OTHER;
			for(OsType tmp : values()) { // tmp  = 윈도우..
				if(userAgent.toUpperCase().contains(tmp.name())) {// 상수랑 같은 값 
					result = tmp ;
					break;
				}
			}
			return result;
		}
		
	}

