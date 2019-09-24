package kr.or.ddit.mvc.annotation;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class HandlerMapper implements IHandlerMapper {
	//핸들러 맵 선언
	public Map<URIMappingCondition, URIMappingInfo> handlerMap;
	
	public HandlerMapper(String[] basePackages) {
		//commandhandler 갖고잇고 memlist찾아서 map을 구성해야해
	}
	
	
	
	@Override
	public URIMappingInfo findCommandHandler(HttpServletRequest req) {
		//새로 매핑컨디션 객체 만ㄷ르어서 연결해얗 ㅐ
		return null;
	}

}
