package kr.or.ddit.common.security.auth;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import kr.or.ddit.common.security.dao.ISecuredServiceDAO;

public class RequestMapFactoryBean implements FactoryBean<LinkedHashMap<RequestMatcher, List<ConfigAttribute>>>{
	private static Logger logger = LoggerFactory.getLogger(RequestMapFactoryBean.class);
	@Inject
	private ISecuredServiceDAO serviceDAO;
	private LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap;
	
	@PostConstruct
	public void init(){
		requestMap = new LinkedHashMap<>();
		List<Map<String, Object>> servicesAndRoles = serviceDAO.selectSecuredResourcesAndAuthorities();
		logger.info("서비스 정보 조회, {}", servicesAndRoles);
		servicesAndRoles.forEach((map)->{
			String url = (String) map.get("service_url");
			List<String> authorities = (List<String>) map.get("authorities");
			if(authorities==null || authorities.size()==0) return;
			AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher(url);
			List<ConfigAttribute> configList = new ArrayList<>();
			requestMap.put(requestMatcher, configList);
			authorities.forEach((element)->{
				configList.add(new SecurityConfig(element));
			});
		});
	}

	@Override
	public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getObject() throws Exception {
		if(requestMap==null) init();
		return requestMap;
	}

	@Override
	public Class<?> getObjectType() {
		return LinkedHashMap.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
