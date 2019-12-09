package kr.or.ddit.prod.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ReviewVO;


/**
 * @author 최서희
 * @since 2019. 11. 8.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                    수정자            수정내용
 * -------------   --------    ----------------------
 * 2019. 11. 8.     최서희 	      최초작성
 * 2019. 11. 13.    최서희 		리뷰 삭제부분 수정
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Controller
public class ProdDelete {
	@Inject
	IProdService service;

	@RequestMapping(value="/review/reviewDelete.do", method=RequestMethod.POST, 
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public PagingInfoVO<ReviewVO> deleteReview(
			@RequestParam(required=true) int reviewNo,
			@RequestParam(required=true) String prodId,
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage,
			Model model
			) {
		String message = null;
		model.addAttribute("message", message);
		ServiceResult result = service.removeReview(reviewNo);
		switch (result) {
		case OK:
			message = "상품평 삭제 성공";
			PagingInfoVO<ReviewVO> pagingVO = new PagingInfoVO<>(5, 5);
			int totalRecord = service.retrieveReviewCount(prodId);
			pagingVO.setTotalRecord(totalRecord);
			pagingVO.setCurrentPage(currentPage);
			pagingVO.setPk(prodId);
			List<ReviewVO> list = service.retrieveReviewList(pagingVO);
			pagingVO.setDataList(list);
			model.addAttribute("pagingVO", pagingVO);
			return (PagingInfoVO<ReviewVO>) model.asMap().get("pagingVO");
		default:
			message = "서버 오류";
			break;
		}

		return null;
	}

}
