package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.api.databinding.MappingInfo;

public class HandlerInvoker implements IHandlerInvoker {

	@Override
	public String invokeHandler(URIMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		//리턴갑 호출자한테 넘겨주기 
		//그럼 호출정보늑? URIMappingInfo 여기 있지
		Object handler = mappingInfo.getCommandHandler();
		Method handlerMtd = mappingInfo.getHandlerMethod();
		String logicalViewName = (String) handlerMtd.invoke(handler, req,resp); //메소드 호출 (주인객체, 파라미터)
		return logicalViewName;
	}

}
