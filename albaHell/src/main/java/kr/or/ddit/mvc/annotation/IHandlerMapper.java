package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;
/**
 *  HanlerMapper 역할
 *  1 @CommandHandler / @URIMapping을 가진 클래스와 메소드에 대한 정보를 수집
 *  	-> handlerMap생성
 *  2. 요청에 대한 핸들러를 찾아서 반환 .( 정보 돌려주기) 
 * @param req
 */
public interface IHandlerMapper {
	public URIMappingInfo findCommandHandler(HttpServletRequest req); //요청 받은거
}
