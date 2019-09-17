package kr.or.ddit.servlet02;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.servlet02.service.ImageListService;
import kr.or.ddit.utils.CookieUtil;

@WebServlet("/imageForm.do")
public class ImageFormServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//모델 2 구조
//		1. 요청받기 
//		2. 요청 분석(request-line, header ,body)
		//쿠기0917
		String imageName= new CookieUtil(req).getCookieValue("imageCookie");
		req.setAttribute("imageName", imageName);
//		3. 서비스객체와의 의존관계 형성 -> 로직선택 
		ImageListService service = new ImageListService();
		String[] images = service.getImageList();
		req.setAttribute("images", images); // 4. , 키,value쌍으로 넣은거 보니 req에는 map이 잇을것....! object : 마술같은 공간dl scope
//		4. Scope 를 통해 information공유 (서블릿과 jsp가 어떻게 하나의 데이터를 공유할것인가???)
//		5. view선택
//		6. view 이동 
		
		String viewName = "/WEB-INF/views/ImageForm.jsp"; //서버사이드방식으로표기 
		RequestDispatcher rd = req.getRequestDispatcher(viewName);
		rd.forward(req, resp);

	}
}
