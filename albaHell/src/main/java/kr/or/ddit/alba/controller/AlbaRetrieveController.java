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
		String id = req.getParameter("id");
		if(
			StringUtils.isBlank(id)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"알바 없어용");
			return null;
		}
		//정상적으로 넘어왔ㅇ르때 
		AlbaVO alba = new AlbaVO(); //아이디만 세팅 
		alba.setAl_id(id);
		
		AlbaVO saved = service.selctAlba(alba);
		req.setAttribute("alba", saved);
		
		return "alba/albaView";
		
	}
}
