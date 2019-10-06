package kr.or.ddit.alba.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.alba.service.AlbaServiceImpl;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.alba.vo.AlbaVO;
import kr.or.ddit.alba.vo.Lic_albaVO;
import kr.or.ddit.alba.vo.LicenseVO;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.wrapper.MultipartRequestWrapper;
import kr.or.ddit.wrapper.PartWrapper;

@CommandHandler
public class AlbaInsertController {
	IAlbaService service = new AlbaServiceImpl();

	@URIMapping(value = "/alba/albaInsert.do", method = HttpMethod.GET)
	public String InsertForm(HttpServletRequest req, HttpServletResponse resp) {
		try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        List<LicenseVO> license = service.selectLic();
        req.setAttribute("license", license);
        String viewName = "albaInsertForm";
        return viewName;

	}

	@URIMapping(value = "/alba/albaInsert.do", method = HttpMethod.POST)
	public String AlbaInsert(HttpServletRequest req, HttpServletResponse resp) {
		AlbaVO alba = new AlbaVO();
		service.insertAlba(alba);
		req.setAttribute("alba", alba);

		String [] licCodeArr = req.getParameterValues("lic_code");
        for(String licCode : licCodeArr) {
            Lic_albaVO licAlba = new Lic_albaVO();
            licAlba.setAl_id(alba.getAl_id());
            licAlba.setLic_code(licCode);
            
            service.insertLicAlba(licAlba);
        }

//		  //알바 자격증명 사진 정보 등록
        try {
            if(req instanceof MultipartRequestWrapper) {
                PartWrapper[] array = ((MultipartRequestWrapper) req).getPartWrappers("lic_image");

                if(array.length > 0) {
                    for(int i=0; i<array.length; i++) {
                        Lic_albaVO licAlba = new Lic_albaVO();
                        licAlba.setAl_id(alba.getAl_id());
                        licAlba.setLic_code(licCodeArr[i]);
                        licAlba.setLic_image(array[i].getBytes());

                        service.updateLicAlba(licAlba);
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return "redirect:/alba/albaList.do";
    }
		
	private AlbaVO setAlbaVO(HttpServletRequest request) {
        String paramAge = request.getParameter("al_age");
        
        AlbaVO vo = new AlbaVO();
        vo.setAl_id(request.getParameter("al_id"));
        vo.setAl_name(request.getParameter("al_name"));
        vo.setAl_age(paramAge.isEmpty() ? 0 : Integer.parseInt(paramAge));
        vo.setAl_age(Integer.parseInt(paramAge));
        vo.setAl_address(request.getParameter("al_address"));
        vo.setAl_hp(request.getParameter("al_hp"));
        vo.setAl_spec(request.getParameter("al_spec"));
        vo.setAl_desc(request.getParameter("al_desc"));
        vo.setGr_code(request.getParameter("gr_code"));
        vo.setAl_career(request.getParameter("al_career"));
        vo.setAl_gen(request.getParameter("al_gen"));
        vo.setAl_btype(request.getParameter("al_btype"));
        vo.setAl_mail(request.getParameter("al_mail"));
        
        return vo;
    }
}
