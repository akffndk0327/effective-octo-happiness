package kr.or.ddit.servlet03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.CommandType;


@WebServlet("/serverFileManager")
public class FileManagerServlet extends HttpServlet {
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String leftSrc = req.getParameter("leftSrc"); // 이 파라미터 없ㅇ르 수도 잇어
		String rightTarget = req.getParameter("rightTarget"); // 이 파라미터 없ㅇ르 수도 잇어
		if (leftSrc == null)leftSrc = "";
		if (rightTarget == null)rightTarget = "";
		// 웹리소스의 상대경로 잡아와야해
		String realPathLeft = application.getRealPath(leftSrc);
		String realPathRight = application.getRealPath(rightTarget);
		File folderLeft = new File(realPathLeft);
		File folderRight = new File(realPathRight);
		
		int status = 200;
		String message = null;
		try {
//			File[] files = traversing(folder); // 여기로 예외넘어와, File[] 파일대신 wrapper가 와야해 기존개체 wrapper하는거 에서 ... 또다른 ..
			List<FileWrapper> leftfiles = traversing(folderLeft); // 여기로 예외넘어와, File[] 파일대신 wrapper가 와야해 기존개체 wrapper하는거 에서 ... 또다른 ..
			List<FileWrapper> rightfiles = traversing(folderRight); // 여기로 예외넘어와, File[] 파일대신 wrapper가 와야해 기존개체 wrapper하는거 에서 ... 또다른 ..
			req.setAttribute("leftfiles", leftfiles);
			req.setAttribute("rightfiles", rightfiles);
			
		} catch (FileNotFoundException | IllegalArgumentException e) {
			// 분류에 맞는 예외찾기
			if (e instanceof FileNotFoundException) {
				status = HttpServletResponse.SC_NOT_FOUND;

			} else {
				status = HttpServletResponse.SC_BAD_REQUEST;

			}
			message = e.getMessage();
		}
		if (status == 200) {
			String viewName = "/WEB-INF/views/serverFileManager.jsp";
			req.getRequestDispatcher(viewName).forward(req, resp);
		} else {
			resp.sendError(status, message);
		}
	}

	private List<FileWrapper> traversing(File folder) throws FileNotFoundException, MalformedURLException {//  File[]  =>list
		//폴더 검증
		if(!folder.exists()) {
			throw new FileNotFoundException(folder.getName()+ "존재하지 않는 폴더 입니다."); //체크리셉셭 . 404를 내보낼수있는 거한테 보내기
		}
		if(folder.isFile()){ //호출자한테 전달하기 
			throw new IllegalArgumentException(folder.getName()+"는 디렉토리가 아님."); //runtime 예외 
		}
		File[] files = folder.listFiles();
		List<FileWrapper> result = new ArrayList<FileWrapper>();
		
//		application.getRealPath("");
		URL root = application.getResource("");
		URL current = folder.toURL();
		if(!root.equals(current)) {
			File parent = folder.getParentFile(); //상위파일
			result.add(new FileWrapper(parent, application,true));
		}
		
		for(File file : files) {
			result.add(new FileWrapper(file, application)); //file, getServletContext() or file, application
		}
//		return files;
		return result;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String srcFileParam = req.getParameter("srcFile"); 
		String command = req.getParameter("command"); //버튼
		String leftsrc=req.getParameter("leftSrc");		//현재 폴더 위치값 가지고 있어야 해서 
		String rightTargetParam =req.getParameter("rightTarget"); //어디에 복사할 건지 
		
		int status = 200;
	      if (StringUtils.isBlank(srcFileParam) || StringUtils.isBlank(command)) {
	         status = HttpServletResponse.SC_BAD_REQUEST;
	      } else {
	    	 File srcFile = new File(application.getRealPath(srcFileParam));
	         if (!srcFile.exists()) {
	            status = HttpServletResponse.SC_NOT_FOUND;
			} else {
				try {
					CommandType commandType = CommandType.valueOf(command);
					if (status == 200) {
						File targetFolder = new File(application.getRealPath(rightTargetParam));
						commandType.commandProcess(srcFile, targetFolder);
					}
				} catch (IllegalArgumentException e) {
					status = 400;
				}
			}

	         if (status == 200) {
	        	 String viewPtrn = "/serverFileManager?leftSrc=%s&rightTarget=%s&srcFile=%s" ; 
	            resp.sendRedirect(req.getContextPath()+"/serverFileManager");
	         } else {
	            resp.sendError(status);
	         }

	      }
		
	}
}




















