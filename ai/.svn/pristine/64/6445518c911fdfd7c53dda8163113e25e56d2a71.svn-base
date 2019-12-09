package kr.or.ddit.advertiseReply.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.advertiseReply.dao.IAdReplyDAO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.AdreplyVO;
import kr.or.ddit.vo.PagingInfoVO;

/**
 * @author 박주연
 * @since 2019. 11. 15.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 15.        박주연		 최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class AdReplyServiceImpl implements IAdReplyService{
	@Inject
	IAdReplyDAO dao;
	
	@Override
	public ServiceResult createReply(AdreplyVO reply) {
		int cnt = dao.insertReply(reply);
		ServiceResult result = null;
		if(cnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.FAILED;
		return result;
	}

	@Override
	public int retrieveReplyCount(PagingInfoVO<AdreplyVO> pagingVO) {
		return dao.selectReplyCount(pagingVO);
	}

	@Override
	public List<AdreplyVO> retrieveReplyList(PagingInfoVO<AdreplyVO> pagingVO) {
		return dao.selectReplyList(pagingVO);
	}

	@Override
	public ServiceResult modifyReply(AdreplyVO reply) {
		int cnt = dao.updateReply(reply);
		ServiceResult result = null;
		if(cnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.FAILED;
		return result;
	}

	@Override
	public ServiceResult removeReply(AdreplyVO reply) {
		int cnt = dao.deleteReply(reply);
		ServiceResult result = null;
		if(cnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.FAILED;
		return result;
	}

}
