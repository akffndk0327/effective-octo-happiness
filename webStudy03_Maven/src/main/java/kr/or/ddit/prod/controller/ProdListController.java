package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

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
	IProdService service = new ProdServiceImpl();

	@URIMapping("/prod/prodList.do")
	public String prodList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String pageParam = req.getParameter("page");
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) { //숫자 맞나 확인 
			currentPage = Integer.parseInt(pageParam);
		}
		
		PagingInfoVO<ProdVO> pagingVO = new PagingInfoVO<ProdVO>();
		int totalRecord = service.retreieveProdCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		// 처리
		List<ProdVO> prodList = service.retrieveProdList(pagingVO);
		pagingVO.setDataList(prodList); //한페이지에 대한 정보 갖고있어
			req.setAttribute("pagingVO", pagingVO);
			return "prod/prodList";
		}
	}

