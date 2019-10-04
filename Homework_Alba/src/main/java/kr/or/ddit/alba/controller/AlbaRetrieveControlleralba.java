package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.core.tools.picocli.CommandLine.Command;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.alba.vo.AlbaVO;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.MarshallingUtils;

@Command
public class AlbaRetrieveControlleralba {
	IAlbaService service = AlbaServiceImpl.getInstance();

	@URIMapping("/alba/albaList.do")
	public String AlbaList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String accept = req.getHeader("Accept");
		List<AlbaVO> list = service.selectAlbaList();
		req.setAttribute("list", list);
		// 마샬링
		if (
				accept.toLowerCase().contains("json")) { // 비동기
			resp.setContentType("application/json;charset=UTF-8");

			// json을 만들기위한 marshalling
			String json = new MarshallingUtils().marshalling(list);

			try (PrintWriter out = resp.getWriter();) {
				out.println(json);
			}
		}
		return "alba/albaList"; 

	}
}
