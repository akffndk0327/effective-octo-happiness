package kr.or.ddit.alba.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/alba/albaUpdate.do")
public class AlbaUpdateController {
	@Inject
	ICodeDao codeDAO;
	@Inject
	IAlbaService service;

	private void setCodeInScope(Model model) {
		model.addAttribute("licenses", codeDAO.selectLicense());
		model.addAttribute("grades", codeDAO.selectGrades());
	}

	@RequestMapping(method = RequestMethod.GET)
	public String doGet(@RequestParam(name = "who", required = true) String who, HttpServletRequest req, Model model,
			HttpServletResponse resp) throws IOException {
		setCodeInScope(model);
		// String who = req.getParameter("who");
		// int sc = 0;
		// if (StringUtils.isBlank(who)) {
		// sc = HttpServletResponse.SC_BAD_REQUEST;
		// } else {
		AlbaVO alba = service.retrieveAlba(who);
		model.addAttribute("alba", alba);

		String view = null;
		// if (sc != 0) {
		// resp.sendError(sc);
		// } else {
		view = "alba/albaForm";
		// }
		return view;
	}

	@RequestMapping(method=RequestMethod.POST)
	public String doPost(
			@Valid @ModelAttribute("alba")AlbaVO alba, 
			Errors errors,	Model model)  {
		setCodeInScope(model);
		
//		AlbaVO alba = new AlbaVO();
//		req.setAttribute("alba", alba);
//		try {
//			BeanUtils.populate(alba, req.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			throw new RuntimeException(e);
//		}
		//검증 지워 errors
//		Map<String, String> errors = new HashMap<String, String>();
//		model.addAttribute("errors", errors);
		boolean valid = !errors.hasErrors();
		String[] lic_codes = alba.getLic_codes();
		// 자격증 코드와 자격증 사본에 대한 처리
//		if(req instanceof MultipartRequestWrapper && lic_codes!=null && lic_codes.length > 0){
//			MultipartRequestWrapper wrapper = (MultipartRequestWrapper)req;
//			PartWrapper[] images = wrapper.getPartWrappers("lic_images");
			if(images==null || lic_codes.length!=images.length){
				valid = false;
//				errors.put("lic_codes", "자격증 이미지 누락");
//			}else{
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

		if (valid) {
			ServiceResult result = service.modifyAlba(alba);
			if (ServiceResult.OK.equals(result)) {
				view = "redirect:/alba/albaView.do?who=" + alba.getAl_id();
			} else {
				model.addAttribute("message", "수정 실패, 다시 시도");
				view = "alba/albaForm";
			}
		} else {
			view = "alba/albaForm";
		}
		return view;
	}

}
