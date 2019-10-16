package kr.or.ddit.prod.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

//POJO
@Controller
@RequestMapping(value = "/prod")
public class ProdListController {
	private static Logger logger = LoggerFactory.getLogger(ProdListController.class);
	@Inject
	IProdService service;

	@RequestMapping("prodList.do")
	public String prodList(
				@RequestParam(required=false)String pageParam,
				@ModelAttribute("searchVO")ProdVO searchVO,
				@RequestParam(name="page", required=false, defaultValue="1") int currentPage,	
				Model model) {
//		ProdVO searchVO = new ProdVO();
//		//방법2
//		try {
//			BeanUtils.populate(searchVO, req.getParameterMap());
//		} catch (IllegalAccessException | InvocationTargetException e) {
//				logger.error("검색중 예외 발생 ",e);//error(String msg, Throwable t)
//		}
		
//		String pageParam = req.getParameter("page");
//		int currentPage = 1;
//		if(StringUtils.isNumeric(pageParam)) { //숫자 맞나 확인 
//			currentPage = Integer.parseInt(pageParam);
//		}
		//페이징
		PagingInfoVO<ProdVO> pagingVO = new PagingInfoVO<ProdVO>(5,3); //스크린,블럭사이즈
		pagingVO.setSearchVO(searchVO); //검색조건과 페이징이 포함 되어있음. 
		
		int totalRecord = service.retreieveProdCount(pagingVO); //COUNT부르면서 검색 조건 파라미터 넘겼음( PROD.XML) 
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		// 처리
		List<ProdVO> prodList = service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList); //한페이지에 대한 정보 갖고있어
		model.addAttribute("pagingVO", pagingVO); //이거만 view로 보냄
		return "prod/prodList";
		
}



//	@RequestMapping("prodView.do")
//	public String prodList(@RequestParam(name="what", required=true)String prod_id, Model model	) {
	@RequestMapping("{what}")
	public ModelAndView prodView(@PathVariable(name="what", required=true) String prod_id){
		/*	IProdService service =new ProdServiceImpl();
			String prod_id =req.getParameter("what");
			if(StringUtils.isBlank(prod_id)) {
				resp.sendError(400); 
				return null;
		}   */ // 이 검증 처리위 해서 requeired= true 해준거 !! 
		ProdVO prod = service.retrieveProd(prod_id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("prod", prod);
		mav.setViewName("prod/prodView");
		return mav;
		}
	
	
	}
