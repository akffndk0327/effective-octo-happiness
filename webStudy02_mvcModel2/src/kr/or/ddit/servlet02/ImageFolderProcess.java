package kr.or.ddit.servlet02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import kr.or.ddit.servlet02.service.ImageFolderService;
@WebServlet("/imagefolder.do")
public class ImageFolderProcess extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1. 요청받기 
//		2. 요청 분석(request-line, header ,body)
//		3. 서비스객체와의 의존관계 형성 -> 로직선택
		ImageFolderService service = new ImageFolderService();
		String[] images = service.getImageList();
		req.setAttribute("images", images);
		String viewName = "/WEB-INF/views/ImagesFolderView.jsp"; //서버사이드방식으로표기 
		RequestDispatcher rd = req.getRequestDispatcher(viewName);
		rd.forward(req, resp); //이미지 리스트 받기
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "D:/A_TeachingMaterial/7.JspSpring/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/webStudy01";
		//선택한 이미지 클릭하고 복사 누르면 07 폴더에 들어가
		String imgparam = req.getParameter("filename");
		String btnparam = req.getParameter("command");
//		String path = req.getContextPath(); //안잡힘
		
		String img = url + "/images/"+imgparam;//원본이미지경로
		
		String img2 = url + "/07/"+imgparam;		//복사이미지
		
		switch (btnparam) {
		case "copy": //복사 07폴더로 
			try (
		 			FileInputStream is = new FileInputStream(img);
					FileOutputStream os = new FileOutputStream(img2);
				) {
		 		IOUtils.copy(is, os);
		 	}
			break;
		case "move": // 이동
			try (
				FileInputStream is = new FileInputStream(img); 
				FileOutputStream os = new FileOutputStream(img2);
			) {
				IOUtils.copy(is, os);
			}
			File file2 = new File(img);
			file2.delete();
			break;
			
		case "delete": //이동
			File file3 = new File(img);
			file3.delete();
			
			break;
		
		default : 
			
			break;
		}
		String location = req.getContextPath()+"/imagefolder.do"; //화면다시 돌아옴 
		resp.sendRedirect(location);
		
	}
	
}
