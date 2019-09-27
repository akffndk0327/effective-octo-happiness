package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enums.BrowserType;
import kr.or.ddit.enums.OsType;
/**
 * 클라이언튼의 시스템을 파악하고,
 * 최종적으로 "당신의 OS는 xxx(윈도우)이고, 당신의 브라우저는 xxx(크롬)입니다. 
 * 라는 메서지를 alert창으로 띄울것 
 * linux/ window
 * chorm/firefox/MSIE
 * 
 * enum문법 활용... 
 */

@WebServlet("/userAgent")
public class UserAgentServlet extends HttpServlet {
	
//			Map<String , String> osMap = new LinkedHashMap<String, String>();
//			osMap.put("linux", "리눅스");
//			osMap.put("windows", "윈도우");
//			osMap.put("other", "기타OS");
//			Map<String , String> brMap = new LinkedHashMap<String, String>();
//			brMap.put("chrom", "크롬");
//			brMap.put("firefox", "파이어폭스");
//			brMap.put("MSIE", "익스플로어");
//			//상수집합체 => enum
	
	@Override //가져오기 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userAgent = req.getHeader("User-Agent"); //toLowerCase(); ->upper ->삭제 
		System.out.println(userAgent);
		
		OsType os = OsType.searchOS(userAgent); //책임 분리 중 
		String osName =os.getName();
		
		BrowserType br = BrowserType.seacrchBR(userAgent);
		String browserName = br.getName();
		
		// OsType.values()
//			
//			for(Entry<String, String> entry : brMap.entrySet()) {
//				if(userAgent.contains(entry.getKey())) {
//					browser = entry.getValue();
//					break;
//				}
//			}
//			
			
		}
	


	}

