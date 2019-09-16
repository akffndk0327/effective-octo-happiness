package kr.or.ddit.servlet01;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.net.URLEncoder;


public class ImageServlet extends HttpServlet{
	@Override
		public void init(ServletConfig config) throws ServletException {
			super.init(config);
			folder= config.getInitParameter("imgFolderPath");
		}
	String folder;
	
 @Override
 public void doGet(HttpServletRequest req, HttpServletResponse resp)
 throws ServletException, IOException
 {
 
		// MIME text : main_type/sub_type ; charset=encoding
		// MIME : 전송 데이터의 형태나 특성을 표현하는 문자
		resp.setContentType("image/jpeg");
		String folder = "d:/contents"; //이 코드가 하드코딩 되있는게 맞는지?
		//변경될만한 코드와 변경안될거는 같이 있으면안되고 분리해야 함 @  ->web.xml에서 수정 
		String imageName = req.getParameter("image");
		
		
		//09.16 쿠키 예제
				String value = URLEncoder.encode("한글값","UTF-8");
				Cookie cookie = new Cookie(imageName,imageName); //쿠키 객체 생성 
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24*2); //데이터 정보 2일 살아잉ㅆ어 
				resp.addCookie(cookie); //응답보내기
		
		
		
		int status = 200;
		if (imageName == null || imageName.trim().length() == 0) {
			status = HttpServletResponse.SC_BAD_REQUEST; // 상수가 int형
		}
		File imgFile = new File(folder, imageName); // 읽을 준비는 함. 근데 다른 사진이 필요하면? "Desert.jpg"=> imageName
		if(!imgFile.exists()) { //서비스가 불가능함 ....	//중간에서 이미지 정보 맞는 지확인하기 
			status = HttpServletResponse.SC_NOT_FOUND;
		}
		if (status == 200) {
			byte[] buffer = new byte[1024];
			try (
					// 입력스트림 필요해
					FileInputStream fis = new FileInputStream(imgFile);
					OutputStream os = resp.getOutputStream();
			) {
				int cnt = -1;
				// stream copy
				while ((cnt = fis.read(buffer)) != -1) { // fis.read : fp :파일포지션
					os.write(buffer, 0, cnt); // 크기가 2.5kb일때 : 2번돌고 0.5만 읽어
				}//while end 
			}//try end
//			os.flush();
//			os.close();
//			fis.close();
			// 이미지 번역이 아닌 띄우라고 지시하기 -> 마인텍스트 : 일정한 문법구조 잇어
		}else {
			resp.sendError(status);// 이미지의 파라미터만 확인한거...
			//진짜 잇나 없나는몰ㄹ라..
		
		}
	}

}




