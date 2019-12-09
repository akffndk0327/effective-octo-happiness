package kr.or.ddit.common.security.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.ExpressionBasedFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class CompositeFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{

	private SecurityExpressionHandler<FilterInvocation> expressionHandler =
											new DefaultWebSecurityExpressionHandler();
	private DefaultFilterInvocationSecurityMetadataSource metadataDef;
	private ExpressionBasedFilterInvocationSecurityMetadataSource metadataExp;
	private LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMapForDef;
	private LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMapForExp;
	
	public CompositeFilterInvocationSecurityMetadataSource(
			LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap) {
		super();
		divideRequestMap(requestMap);
		metadataDef = new DefaultFilterInvocationSecurityMetadataSource(requestMapForDef);
		metadataExp = new ExpressionBasedFilterInvocationSecurityMetadataSource(requestMapForExp, expressionHandler);
	}

	private void divideRequestMap(LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap) {
		requestMapForDef = new LinkedHashMap<>();
		requestMapForExp = new LinkedHashMap<>();
		requestMap.forEach((matcher, list)->{
			List<ConfigAttribute> listDef = new ArrayList<>();
			List<ConfigAttribute> listExp = new ArrayList<>();
			list.forEach(securityConfig->{
				if(securityConfig.getAttribute().startsWith("ROLE_")) {
					listDef.add(securityConfig);
				}else {
					listExp.add(securityConfig);
				}
			});
			if(listDef.size()>0) requestMapForDef.put(matcher, listDef);
			if(listExp.size()>0) requestMapForExp.put(matcher, listExp);
		});
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> allAttrs = new HashSet<>();
		if(metadataDef.getAllConfigAttributes()!=null)
			allAttrs.addAll(metadataDef.getAllConfigAttributes());
		if(metadataExp.getAllConfigAttributes()!=null)
			allAttrs.addAll(metadataExp.getAllConfigAttributes());
		return allAttrs;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object request) throws IllegalArgumentException {
		Collection<ConfigAttribute> attributesDef = metadataDef.getAttributes(request);
		Collection<ConfigAttribute> attributesExp = metadataExp.getAttributes(request);
		if(attributesDef==null && attributesExp==null) return null;
		else if(attributesDef!=null && attributesExp==null) return attributesDef;
		else if(attributesDef==null && attributesExp!=null) return attributesExp;
		else {
			attributesDef.addAll(attributesExp);
			return attributesDef;
		}
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return FilterInvocation.class.isAssignableFrom(arg0);
	}

}
