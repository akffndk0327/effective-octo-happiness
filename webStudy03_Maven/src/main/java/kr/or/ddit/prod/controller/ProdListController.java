package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.MarshallingUtils;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

//POJO
@CommandHandler
public class ProdListController {
	private static Logger logger = LoggerFactory.getLogger(ProdListController.class);
	IProdService service = new ProdServiceImpl();

	@URIMapping("/prod/prodList.do")
	public String prodList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//방법1
//		String prod_lgu = req.getParameter("prod_lgu");
//		String prod_buyer = req.getParameter("prod_buyer");
//		String searchTxt = req.getParameter("searchTxt");
		
		ProdVO searchVO = new ProdVO();
		//방법2
		try {
			BeanUtils.populate(searchVO, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
				logger.error("검색중 예외 발생 ",e);//error(String msg, Throwable t)
		}
		//방법1
//		searchVO.setProd_lgu(prod_lgu);;
//		searchVO.setProd_buyer(prod_buyer);
//		searchVO.setProd_name(searchTxt);
		
		String pageParam = req.getParameter("page");
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) { //숫자 맞나 확인 
			currentPage = Integer.parseInt(pageParam);
		}
		//페이징
		PagingInfoVO<ProdVO> pagingVO = new PagingInfoVO<ProdVO>(5,3); //스크린,블럭사이즈
		pagingVO.setSearchVO(searchVO); //검색조건과 페이징이 포함 되어있음. 
		
		int totalRecord = service.retreieveProdCount(pagingVO); //COUNT부르면서 검색 조건 파라미터 넘겼음( PROD.XML) 
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		// 처리
		List<ProdVO> prodList = service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList); //한페이지에 대한 정보 갖고있어
		req.setAttribute("pagingVO", pagingVO); //이거만 view로 보냄
		return "prod/prodList";
		}
	}

