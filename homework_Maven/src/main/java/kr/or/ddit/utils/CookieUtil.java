package kr.or.ddit.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 쿠기 생성과 쿠키 확보를 지원하는 유틸리티
 *
 */
public class CookieUtil {
	private Map<String,Cookie> cookieMap;
	//외부에서 받아와서 써얗
	private HttpServletRequest req; //반드시필요한지 부가적으로 필요한지
	
	//DependencyInjecion(DI) :setter, constructor 로 구분 필수전략으로 
	public CookieUtil(HttpServletRequest req) { //리퀘스트를 받아야 쿠키유틸을 사용할수있음
		super();
		this.req = req;
		cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = req.getCookies();
		if(cookies !=null) {
			for(Cookie tmp:cookies) {
				cookieMap.put(tmp.getName(), tmp); //만들어진건 Map으로 들어감.
			}
		}
	}

	
	private static String charset = "UTF-8";
	
	public static void setCharset(String charset) {
		CookieUtil.charset = charset;
	}

	public Map<String, Cookie> getCookieMap() {
		return cookieMap;
	}
	
	public Cookie getCookie(String name) {
		return cookieMap.get(name);
	}
	public String getCookieValue(String name) throws UnsupportedEncodingException{
		Cookie cookie = getCookie(name); //언제나 쿠키 존재한다고 볼순없어서 예외처리 
		if(cookie == null) return null;
		String value = cookie.getValue(); //왜? =메소드가 value 뽑느 ㄴ거여서
		return URLDecoder.decode(value,charset);
	}
		
	/**
	 * 쿠키생성
	 * @param name
	 * @param value 쿠키값, encoding UTF-8
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static Cookie createCookie(String name, String value) throws UnsupportedEncodingException{
		value = URLEncoder.encode(value,charset); // checked exception
		Cookie cookie = new Cookie(name,value);
		return cookie;
	}
	public static Cookie createCookie(String name, String value, int maxAge) throws UnsupportedEncodingException{
		value = URLEncoder.encode(value,charset);
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		return cookie;
	}

	//식별성 만족이키는 건 enum
	public static enum TextType{
		PATH, DOMAIN
	//이 값자체가 식별성 자겨
	}
	
	public static Cookie createCookie(String name, String value, String text, TextType pathOrDomain) throws UnsupportedEncodingException{
		
		Cookie cookie = createCookie(name, value);
		if(TextType.PATH.equals(pathOrDomain)) {
			cookie.setPath(text);
		}else if(TextType.DOMAIN.equals(pathOrDomain)) {
			cookie.setDomain(text);
		}
	
		return cookie;
	}

	public static Cookie createCookie(String name, String value, String text, TextType pathOrDomain, int maxAge) throws UnsupportedEncodingException{
		Cookie cookie = createCookie(name, value, text, pathOrDomain);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	public static Cookie createCookie(String name, String value, String path, String domain) throws UnsupportedEncodingException{
		Cookie cookie = createCookie(name, value);
		cookie.setPath(path);
		cookie.setDomain(domain);
		return cookie;
	}
	
	public static Cookie createCookie(String name, String value, String path, String domain, int maxAge) throws UnsupportedEncodingException{
		Cookie cookie = createCookie(name, value, path, domain);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	//메소드의 이름은 다 같은데 파라미터가 달라 => 다형성 
}












