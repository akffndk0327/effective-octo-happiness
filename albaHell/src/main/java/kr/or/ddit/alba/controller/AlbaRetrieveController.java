package kr.or.ddit.alba.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.alba.vo.AlbaVO;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;

/**
 * 알바 상세 조회 컨트롤러
 *
 */
@CommandHandler
public class AlbaRetrieveController {
	IAlbaService service = new AlbaServiceImpl();

	@URIMapping("/alba/albaView.do")
	public String AlbaView(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String who = req.getParameter("who");
		int sc = 0;
		if(StringUtils.isBlank(who)) {
			sc = HttpServletResponse.SC_BAD_REQUEST;
		}else {
			AlbaVO alba = service.retrieveAlba(who);
			req.setAttribute("alba", alba);
			if(alba==null) {
				sc = HttpServletResponse.SC_NOT_FOUND;
			}
		}
		String view = null;
		if(sc!=0) {
			resp.sendError(sc);
		}else {
			view = "alba/albaView";
		}
		return view;
	}

}
