package kr.or.ddit.prod.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.common.hints.InsertHint;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.vo.ProdVO;

@Controller
@RequestMapping("/prod/prodInsert.do")
public class ProdInsertController {
	@Inject
	IProdService service;

	@RequestMapping(method = RequestMethod.GET)
	public String insertForm() {
		return "prod/prodForm";
	}
	/*
	 * 1016
	JSR-303 방식의 객체 검증 단계
	1.javax.validation 의존성 추가
	2. hibernate-validator 의존성 추가
	3.message 번들에 검증 결과 메세지 커스터마이징(message_local.properties)
	4.@Valid, @Validated(Validation hint)를 사용해 검증 대상이 되는 커맨드 오브젝트(VO)를 검증하도록 설정
	  1)각 업무를대상으로 한 hit interface 구현
	  2) 공통 검증 룰을 적용할 hint Default 상속 구조
	5.검증 대상이 되는 Domain layer에 검증 어노테이션으로 룰 설정
	7. 검증 대상 객체 다음에 Errors/BindingResult 사용하여 검증결고 ㅏ받음
	8.view layer에서 검증결과 메시지 출력
		spring-form 태그 라이브러리 사용 (form:form, form:errors)
	*/
	
	@RequestMapping(method = RequestMethod.POST)
	public String insert(Model model,
				@Validated(InsertHint.class) //@Validated(value) : 힌트받을수있어
				@ModelAttribute(name="prod")ProdVO prod, 
				BindingResult errors) {//검증결과데이터 갖고이엄  방법2
				
//		ProdVO prod = new ProdVO();
//		model.addAttribute("prod", prod);

//		try {
//			BeanUtils.populate(prod, req.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			throw new RuntimeException(e);
//		}
		
		//1004
//		if(req instanceof MultipartRequestWrapper) {
//			PartWrapper partWrapper = ((MultipartRequestWrapper) req).getPartWrappper("prod_image");
//			if(partWrapper!=null) {
				// 1. 저장위치
//				String saveFolderURL = "/prodImages"; // 여기에 이미지 하나 저장됨 .!
//				String saveFolderPath = req.getServletContext().getRealPath(saveFolderURL);
//				File saveFolder = new File(saveFolderPath);
//				// 존재여부 확인 !
//				if (!saveFolder.exists())
//					saveFolder.mkdirs();
//				// 2.저장명 원본파일명 쓰지마
//				String savename = UUID.randomUUID().toString();
//				try (InputStream is = partWrapper.getInputStream();) {
//					FileUtils.copyInputStreamToFile(is, new File(saveFolder, savename)); // 여기서 복사 떠짐
//				} // 저장명이라는 메타데이터 생김
//				prod.setProd_img(savename); // 입력받지 않은 이미지 경로가 생김 그ㅓ나 이미지는 웹서버상에 images에 저장됨.
//			}
//		}
		

//		Map<String, String> errors = new HashMap<String, String>();
//		model.addAttribute("errors", errors);

		boolean valid = !errors.hasErrors();
		String viewName = null;
		String message = null;
		if (valid) {
			ServiceResult result = service.createProd(prod);
			switch (result) {
			case OK:
//				- OK   : redirect -> welcome page
				viewName = "redirect:/prod/prodView.do?what=" + prod.getProd_id(); //what이 파라미터 
//				viewName = "redirect:/prod/" + prod.getProd_id(); //what이 파라미터 
				break;
			default:
				message = "서버 오류";
				viewName = "prod/prodForm";
				break;
			}

		} else {
			viewName = "prod/prodForm";
		}

		model.addAttribute("message", message);

		return viewName;
	}

}
