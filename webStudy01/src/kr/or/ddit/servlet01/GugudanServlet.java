package kr.or.ddit.servlet01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//jsp는 서블릿이다... 
@WebServlet("/gugudan")
public class GugudanServlet extends HttpServlet{
	//request callback : template method, hook method(doXXX)
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		super.service(req, resp); //hook메서드 호출 
//		}
//	
	
	@Override //dopost->doGet ->Service (post,get포괄하고이음 )
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		try (InputStream is = req.getInputStream(); // 1바이트씩 찔끔 읽혀
//				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//		) {
//			String tmp = null;
//			while ((tmp = reader.readLine()) != null) {
//				System.out.printf("=====> %s\n", tmp); // 바디의 사이ㅡ 확인할수이성
//			}
//		}
//		//try 끝나면 is도 끝남.톰캣이 파싱못해 
		//요청은 html에서 날러애ㅑ헤ㅐ
		
		String minParam = req.getParameter("minDan");
		String maxParam = req.getParameter("maxDan");
		//검증 입력할때 - 제한된 타입만 쓰게끔 클라이언드사이드검증 - 입력하고 서버로 넘어놀때 어떻게 넘어올지 장담못해
		//서버사이드에서도 검증해야함.
		
		int minDan =2;
		int maxDan =9;
		if(minParam!=null && minParam.matches("[0-9]{1,2}") &&
				maxParam!=null && maxParam.matches("[0-9]{1,2}")) {//[0-9]+ 숫자 하나이상 , [0-9]* : 없거나 하나이상
			 minDan = Integer.parseInt(minParam);
			 maxDan = Integer.parseInt(maxParam);
		}
		
		
		//파싱하기
//		int minDan = Integer.parseInt(minParam);
//		int maxDan = Integer.parseInt(maxParam);
		
		resp.setContentType("text/html;charset = UTF-8");
		StringBuffer template = readTemplate("gugudan.tmpl");
		StringBuffer gugudan = new StringBuffer(); //String 대신 StringBuffer쓰는 이뉴는 메모리때문에 
		
		String ptrn = "<td>%d*%d=%d</td>"; //td 안에 들어갈 형식 
		for (int dan = minDan; dan <= maxDan; dan++) {
			gugudan.append("<tr>");
			for (int mul = 1; mul <=9; mul++) {
			
			gugudan.append(String.format(ptrn,dan,mul,dan*mul));
			
			}
			gugudan.append("</tr>");
		}
		int idx = template.indexOf("@gugudan");
		StringBuffer html = template.replace(idx,idx+"@gugudan".length(), gugudan.toString());
		idx = template.indexOf("@cnt");
		html = html.replace(idx,idx+"@cnt".length(), (9-1+1)+"");
		
		
		gugudan.append("</table>");
		gugudan.append("</body>");
		gugudan.append("</html>"); 
		
		 try(
			//closable 객체의 선언.
			PrintWriter out =resp.getWriter() ; //예외 발생 될때 자동적으로 생성됨 
		){
			out.println(html); 
		 }
	}
	
	private StringBuffer readTemplate(String name) throws IOException{
		StringBuffer template = new StringBuffer();
		try(
				//아래 두줄을 inputStream으로 끝냄. 
				//얇은 빨대
				InputStream is =
				GugudanServlet.class.getResourceAsStream(name); //상대경로 & 클래스패스리소스
		//		File file = new File();
		//		FileInputStream fis = new FileInputStream(file);
				
				//긁은 빨대 , InputStreamReader : 얇은 걸 굵게 만들어줘 
				BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8")); //2차 스트림 여기에 꽂아서 써야해 ..? 뭘 ?
				
		){
			String tmp = null;
			while((tmp =reader.readLine())!=null){ //맨 마지막 줄 만날때까지 읽어야해 
				template.append(tmp+"\n");
			}
		}
		return template;
	}//솔리드 때문에 분리하는중 ... 한코드에 여러개 안섞을려고 
	
	
	
}
