package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.utils.ReflectionUtils;

public class HandlerMapper implements IHandlerMapper {
	private static Logger logger = LoggerFactory.getLogger(HandlerMapper.class);
	//핸들러 맵 선언
	public Map<URIMappingCondition, URIMappingInfo> handlerMap;
	
	//commandhandler 갖고잇고 memlist찾아서 map을 구성해야해
	//요청에 대한 정보 수집하기 1. url
	public HandlerMapper(String[] basePackages) {
		handlerMap = new LinkedHashMap<>();
		List<Class<?>> classes = new ArrayList<>();
		for(String basePkg : basePackages) {
			classes.addAll(ReflectionUtils.getClassesAtBasePackageWithAnnotation(basePkg.trim(), 
					CommandHandler.class));	//CommandHandler.타입//
		}
		//메소드 골라ㅎ내기
		for(Class<?> tmp :classes) {
//			System.out.println(tmp.getName());
			List<Method> methods = ReflectionUtils.getMethodsAtClassWithAnnotation(tmp, URIMapping.class, 
					String.class, HttpServletRequest.class,HttpServletResponse.class);
			Object handler;
			try {
				handler = tmp.newInstance();
				for(Method mtd :methods) {
					//하나의 메소드로 접근 & 한쌍의 핸들러 만들어져
					//URIMappingCondition : URIMappingInfo 로 POJO 하기
					URIMapping mapping = mtd.getAnnotation(URIMapping.class); //urimapping 타입의 객체로 넘어옴. null 체크 안해오되.
					URIMappingCondition key = new URIMappingCondition(mapping.value(), mapping.method()); 
					URIMappingInfo info = new URIMappingInfo(key, handler, mtd); //commandHandler(handler) :mtd:이거 가지고 있는개 체 필요 
					handlerMap.put(key,info);
//					System.out.printf("%s 핸들로 : %s \n", key,info);
					logger.info("{} 핸들러 : {}", key , info ); //message argument
				}
			} catch (InstantiationException | IllegalAccessException e) {
				System.err.println(e.getMessage());
				continue;
			}
		}
		
	}
	
	@Override
	public URIMappingInfo findCommandHandler(HttpServletRequest req) {
		//새로 매핑컨디션 객체 만ㄷ르어서 연결해얗 ㅐ
		String uri = req.getRequestURI(); //컨텍스ㅡ 패스 포함되잇어서 잘라내기ㅏ
		uri = uri.substring(req.getContextPath().length());
		uri = uri.split(";")[0]; //서버사이드 경로를 뽑아와
		HttpMethod method = HttpMethod.valueOf(req.getMethod().toUpperCase());//enum으로 찾아야해.+ 정상적으로 상수돌아오는거 보장 못해(메소드없을때 예ㅎ외로 넘어감)
		URIMappingCondition key = new URIMappingCondition(uri, method);
		return handlerMap.get(key);
	}

}
