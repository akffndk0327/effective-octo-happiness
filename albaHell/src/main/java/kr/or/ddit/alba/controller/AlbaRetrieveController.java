package kr.or.ddit.alba.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.alba.vo.AlbaVO;

/**
 * 알바 상세 조회 컨트롤러
 *
 */
@Controller
public class AlbaRetrieveController {
	@Inject
	IAlbaService service = new AlbaServiceImpl();

	@RequestMapping("/alba/albaView.do")
	public String AlbaView(
			@ModelAttribute(name="who")String who,	Model model) throws IOException {
//			@RequestParam(name="who", required=true)String who,	Model model

		//		String who = req.getParameter("who");
//		if(StringUtils.isBlank(who)) {
//			sc = HttpServletResponse.SC_BAD_REQUEST;
//		}else {
			AlbaVO alba = service.retrieveAlba(who);
			model.addAttribute("alba", alba);
//			if(alba==null) {
//				sc = HttpServletResponse.SC_NOT_FOUND;
//			}
		
		String view = "alba/albaView";
		return view;
	}

}
