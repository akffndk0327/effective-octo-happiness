package kr.or.ddit.alba.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.alba.service.IAlbaService;
import kr.or.ddit.alba.vo.AlbaVO;
import kr.or.ddit.alba.vo.PagingInfoVO;

@Controller
public class AlbaRetrieveControlleralba {
	@Inject
	IAlbaService service ;
//	@URIMapping("/alba/licenseImage.do")
//	public String licenseImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String al_id = req.getParameter("al_id");
//		String lic_code = req.getParameter("lic_code");
//		if(StringUtils.isBlank(al_id)||StringUtils.isBlank(lic_code)){
//			resp.sendError(400);
//			return null;
//		}
//		Lic_albaVO licAlba = new Lic_albaVO();
//		licAlba.setAl_id(al_id);
//		licAlba.setLic_code(lic_code);
//		Lic_albaVO license = service.retrieveLicense(licAlba);
//		resp.setContentType("application/octet-stream");
//		byte[] imageData = license.getLic_image();
//		if(imageData==null){
//			try(
//				FileInputStream fis = new FileInputStream(req.getServletContext().getRealPath("/images/noImage.png"));
//			){		
//				int size = fis.available();
//				imageData = new byte[size];
//				IOUtils.read(fis, imageData);
//			}
//		}
//		try(
//			OutputStream os = resp.getOutputStream();
//			ByteArrayInputStream is = new ByteArrayInputStream(imageData);
//		){
//			IOUtils.copy(is, os);
//		}
//		return null;
//	}
	
	//알바 리스트 출력 
	@RequestMapping("/alba/albaList.do")
	public String AlbaList(
			@RequestParam(required=false) String searchType,
			@RequestParam(required=false) String searchWord,
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			,Model model){
//		String pageParam = req.getParameter("page");
//		String searchType = req.getParameter("searchType");
//		String searchWord = req.getParameter("searchWord");
//		int currentPage = 1;
//		if (StringUtils.isNotBlank(pageParam) && StringUtils.isNumeric(pageParam)) {
//			currentPage = Integer.parseInt(pageParam);
//		}

		PagingInfoVO<AlbaVO> pagingVO = new PagingInfoVO<AlbaVO>(7, 3);
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("searchType", searchType);
		searchMap.put("searchWord", searchWord);
		pagingVO.setSearchMap(searchMap);
		pagingVO.setCurrentPage(currentPage);
		
		int totalRecord = service.retrieveAlbaCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);

		List<AlbaVO> albaList = service.retrieveAlbaList(pagingVO);
		pagingVO.setDataList(albaList);

		model.addAttribute("pagingVO", pagingVO);

		String view = "alba/albaList";
		return view;
	}
	
}
