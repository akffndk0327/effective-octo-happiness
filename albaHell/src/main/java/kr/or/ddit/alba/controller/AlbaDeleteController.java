package kr.or.ddit.alba.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.enums.ServiceResult;

@Controller
public class AlbaDeleteController {
		@Inject
	    IAlbaService service ;
	    
	    @RequestMapping("/alba/albaDelete.do")
		public String doGet(
				@RequestParam(name="who",required=true)String who, Model model, 
				HttpServletResponse resp, HttpSession session) throws IOException {
//			String who = req.getParameter("who");
			if(StringUtils.isBlank(who)){
				resp.sendError(400);
				return null;
			}
			String view = "/alba/albaView.do";
			ServiceResult result = service.removeAlba(who);
			
			if(ServiceResult.OK.equals(result)){
				view = "redirect:/alba/albaList.do";
			}else{
				session.setAttribute("message", "삭제 실패, 다시 하라.");
				view = "redirect:/alba/albaView.do?who="+who;
			}
			
			return view;
		}
	}