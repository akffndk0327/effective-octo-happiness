package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.alba.vo.Lic_albaVO;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.MarshallingUtils;

@CommandHandler
public class AlbaDeleteController {
	
	    IAlbaService service = new AlbaServiceImpl();
	    
	    @URIMapping("/alba/albaDelete.do")
	    public String delete(HttpServletRequest req, HttpServletResponse resp) throws IOException{
	        String al_id = req.getParameter("al_id");
	        
	        if (StringUtils.isBlank(al_id)) {
	            System.out.println("id 없음");
	            resp.sendError(HttpServletResponse.SC_BAD_GATEWAY, "조회 대상 없음");
	            return null;
	        }
	        
	        String alba=service.deleteAlba(al_id);
	        service.deleteLicAlba(al_id);
	        
	        Map<String, Object> result = new HashMap<>();
	        if(alba.equals("OK")) {
	            result.put("valid", new Boolean(false));
	        }else {
	            result.put("valid", new Boolean(true));
	        }
	    
	        resp.setContentType("application/json;charset=UTF-8");

	        String json = new MarshallingUtils().marshalling(result);

			try (PrintWriter out = resp.getWriter();) {
				out.println(json);
			}

	        return null;
	    }
	    
	    @URIMapping(value="/alba/albaLicDelete.do",method=HttpMethod.POST)
	    public String deleteLic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	    	String al_id=req.getParameter("al_id");
	    	String lic_code=req.getParameter("lic_code");
	    	
	    	
	    	if (StringUtils.isNotBlank(al_id) && StringUtils.isNotBlank(lic_code)) {
	    		Lic_albaVO vo = new Lic_albaVO();
	    		vo.setAl_id(al_id);
	    		vo.setLic_code(lic_code);
	    		
	    		String alba = service.deleteLic(vo);
	    		System.out.println("서비스");
	    		
	    		Map<String, Object> result = new HashMap<>();
	    		
	            if(alba.equals("OK")) {
	                result.put("valid", new Boolean(false));
	            }else {
	                result.put("valid", new Boolean(true));
	            }
	        
	            resp.setContentType("application/json;charset=UTF-8");

	            String json = new MarshallingUtils().marshalling(result);

	    		try (PrintWriter out = resp.getWriter();) {
	    			out.println(json);
	    		}
	    	}
	    	
			return null;
	    	
	    }
	}