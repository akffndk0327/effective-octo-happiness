package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.alba.dao.ICodeDao;
import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.alba.vo.AlbaVO;
import kr.or.ddit.alba.vo.Lic_albaVO;
import kr.or.ddit.enums.ServiceResult;

@Controller
@RequestMapping("/alba/albaInsert.do")
public class AlbaInsertController {
	@Inject
	ICodeDao codeDAO ;
	@Inject
	IAlbaService service;
	
	private void setCodeInScope(Model model){
		model.addAttribute("licenses", codeDAO.selectLicense());
		model.addAttribute("grades", codeDAO.selectGrades());
	}
	
	//첫화면 
	@RequestMapping(method=RequestMethod.GET)
	public String doGet(Model model) {
		setCodeInScope(model);
		String view = "alba/albaForm";
		return view;
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String doPost(
			Map<String,String[]> parameterMap,
			@Valid @ModelAttribute(name = "alba") AlbaVO alba,
			Errors errors, Model model)
			throws ServletException, IOException {
		setCodeInScope(model);
		
//		Map<String, String[]> parameterMap = req.getParameterMap();
//		AlbaVO alba = new AlbaVO();
		model.addAttribute("alba", alba);
		
//		try {
//			BeanUtils.populate(alba, parameterMap);
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			throw new RuntimeException(e);
//		}
		
		// 검증
//		Map<String, String> errors = new HashMap<String, String>();
//		req.setAttribute("errors", errors);
		boolean valid = !errors.hasErrors();
		String[] lic_codes = alba.getLic_codes();
		// 자격증 코드와 자격증 사본에 대한 처리
//		if(req instanceof MultipartRequestWrapper && lic_codes!=null && lic_codes.length > 0){
//			MultipartRequestWrapper wrapper = (MultipartRequestWrapper)req;
//			PartWrapper[] images = wrapper.getPartWrappers("lic_images");
			if(images==null || lic_codes.length!=images.length){
				valid = false;
				errors.put("lic_codes", "자격증 이미지 누락");
			}else{
				List<Lic_albaVO> licenseList = new ArrayList<>();
				alba.setLicenseList(licenseList);
				for(int idx=0; idx<lic_codes.length; idx++){
					Lic_albaVO licAlba = new Lic_albaVO();
					licAlba.setLic_code(lic_codes[idx]);
					licAlba.setLic_image(images[idx].getBytes());
					licenseList.add(licAlba);
				}
			}
	
		
		String view = null;
		
		if(valid) {
			ServiceResult result = service.createAlba(alba);
			if (ServiceResult.OK.equals(result)) {
				view = "redirect:/alba/albaList.do";
			} else {
				model.addAttribute("message", "등록 실패, 다시 시도");
				view = "alba/albaForm";
			}
		}else {
			view = "alba/albaForm";
		}
		return view;
	}
	
}
