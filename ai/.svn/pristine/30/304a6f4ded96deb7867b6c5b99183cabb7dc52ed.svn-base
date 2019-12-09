package kr.or.ddit.faq.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.faq.dao.IFaqDAO;
import kr.or.ddit.vo.FaqVO;
import kr.or.ddit.vo.PagingInfoVO;

/**
 * @author 최서희
 * @since 2019. 11. 21.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * -------------   --------    ----------------------
 * 2019. 11. 21.    최서희                최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class FaqServiceImpl implements IFaqService{
	@Inject IFaqDAO dao;

	@Override
	public int retrieveFaqCount() {
		return dao.selectFaqCount();
	}

	@Override
	public List<FaqVO> retrieveFaqList(PagingInfoVO<FaqVO> pagingVO) {
		return dao.selectFaqList(pagingVO);
	}

	@Override
	public FaqVO retrieveFaq(int faqNo) {
		return dao.selectFaq(faqNo);
	}

	@Override
	public ServiceResult createFaq(FaqVO faq) {
		ServiceResult result = null;
		int cnt = dao.insertFaq(faq);
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult modifyFaq(FaqVO faq) {
		ServiceResult result = null;
		int cnt = dao.updateFaq(faq);
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult removeFaq(int faqNo) {
		ServiceResult result = null;
		int cnt = dao.deleteFaq(faqNo);
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult modifyFaqHit(int faqNo) {
		ServiceResult result = null;
		int cnt = dao.updateFaqHit(faqNo);
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

}
