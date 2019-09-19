package kr.or.ddit.servlet03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enums.CommandType;
import kr.or.ddit.utils.MarshallingUtils;

@WebServlet("/serverFileManager")
public class FileManagerServlet extends HttpServlet {
	private ServletContext application;
	// ServletContext : 하나의 context내에서 다른 servlet간 공유 가능한 data 혹은 자원
	// Context 는 application의 최상위 개념/
	// ServletConfig : 하나의 servlet에만 쓸수있음

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String leftSrc = req.getParameter("leftSrc"); // 이 파라미터 없ㅇ르 수도 잇어
		String rightTarget = req.getParameter("rightTarget"); // 이 파라미터 없ㅇ르 수도 잇어
		if (leftSrc == null)
			leftSrc = "";
		if (rightTarget == null)
			rightTarget = "";
		// 웹리소스의 상대경로 잡아와야해
		String realPathLeft = application.getRealPath(leftSrc);
		String realPathRight = application.getRealPath(rightTarget);
		File folderLeft = new File(realPathLeft);
		File folderRight = new File(realPathRight);

		int status = 200;
		String message = null;
		Map<String, FileWrapper[]> jsonMap = new HashMap<String, FileWrapper[]>();
		try {
//			File[] files = traversing(folder); // 여기로 예외넘어와, File[] 파일대신 wrapper가 와야해 기존개체 wrapper하는거 에서 ... 또다른 ..
			List<FileWrapper> leftfiles = traversing(folderLeft);
			List<FileWrapper> rightfiles = traversing(folderRight);

			// 0919 마샬링 준비////
			FileWrapper[] leftArray = new FileWrapper[leftfiles.size()];
			FileWrapper[] rightArray = new FileWrapper[rightfiles.size()];
			jsonMap.put("leftfiles", leftfiles.toArray(leftArray)); // 왜 리스트를 배열로?
			jsonMap.put("rightfiles", rightfiles.toArray(rightArray)); // 왜 리스트를 배열로?
			// ---0919

			req.setAttribute("leftfiles", leftfiles); // 비동기 해도 이거 2개 가져가야해 => 마샬링 해서
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
			String accept = req.getHeader("Accept");
			if (accept.contains("json")) {
				// 요청받고 jsp로 포워딩하기 .....=마샬링하기
				// json.jsp... 내가갖고잇는걸어떻게 json으로 바꿀것인지
				// 0919 마임 세팅
				resp.setContentType("application/json"); // 응답데이터로 ,json 내보낼준비함.
				String json = new MarshallingUtils().marshalling(jsonMap);
				try ( // 출력 스트림
						PrintWriter out = resp.getWriter();) {
					out.print(json); //직렬화
				}

			} else {
				String viewName = "/WEB-INF/views/serverFileManager.jsp";
				req.getRequestDispatcher(viewName).include(req, resp);

			}
//		RequestDispatcher :요청을 보내주는 클래스
		} else {
			resp.sendError(status, message);
		}
	}

	private List<FileWrapper> traversing(File folder) throws FileNotFoundException, MalformedURLException {// File[]
																											// =>list
		// 폴더 검증
		if (!folder.exists()) {
			throw new FileNotFoundException(folder.getName() + "존재하지 않는 폴더 입니다."); // 체크리셉셭 . 404를 내보낼수있는 거한테 보내기
		}
		if (folder.isFile()) { // 호출자한테 전달하기
			throw new IllegalArgumentException(folder.getName() + "는 디렉토리가 아님."); // runtime 예외
		}
		File[] files = folder.listFiles();
		List<FileWrapper> result = new ArrayList<FileWrapper>();

//		application.getRealPath("");
		URL root = application.getResource("");
		URL current = folder.toURL(); // 왜 씀?
		if (!root.equals(current)) {
			File parent = folder.getParentFile(); // 상위파일
			result.add(new FileWrapper(parent, application, true));
		}

		for (File file : files) {
			result.add(new FileWrapper(file, application)); // file, getServletContext() or file, application
		}
//		return files;
		return result;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String srcFileParam = req.getParameter("srcFile");
		String command = req.getParameter("command"); // 버튼
		String leftSrc = req.getParameter("leftSrc"); // 현재 폴더 위치값 가지고 있어야 해서
		String rightTarget = req.getParameter("rightTarget"); // 어디에 복사할 건지

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
						File targetFolder = new File(application.getRealPath(rightTarget));
						commandType.commandProcess(srcFile, targetFolder);
					}
				} catch (IllegalArgumentException e) {
					status = 400;
				}
			}


		}
		if(status==200) {
			String viewPtrn = "/serverFileManager?leftSrc=%s&rightTarget=%s&srcFile=%s";
			resp.sendRedirect(req.getContextPath() + 
					String.format(viewPtrn, leftSrc, rightTarget, srcFileParam));
		}else {
			resp.sendError(status);
		}

	}
}
