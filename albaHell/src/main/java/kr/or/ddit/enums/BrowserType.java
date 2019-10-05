package kr.or.ddit.enums;

public enum BrowserType {
		CHROME("크롬"), FIREFOX("파이어폭스"), MSIE("익스플로어"), OTHER("기타") ; //4
		
		//생성자 만들고 CHROME("크롬")으로 파라미터 같이써
		BrowserType(String name) { //3
			this.name =name;
		}
		private String name; //1
		
		public String getName() { //2
			return this.name;
		}
		public static BrowserType seacrchBR(String userAgent) { //5
			BrowserType result2 = OTHER;
			for(BrowserType tmp2 : values()) {
				if(userAgent.toUpperCase().contains(tmp2.name())){
					result2 = tmp2;
					break;
				}
			}
			return result2;
		}
}
