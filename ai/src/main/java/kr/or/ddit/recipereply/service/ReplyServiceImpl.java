package kr.or.ddit.recipereply.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.recipereply.dao.IReplyDAO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements IReplyService {
	@Inject
	IReplyDAO dao;

	@Override
	public List<ReplyVO> retrieveReply(PagingInfoVO<ReplyVO> pagingVO) {
		// TODO Auto-generated method stub
		return dao.selectReplyList(pagingVO);
	}

	@Override
	public int selectReplyCount(PagingInfoVO<ReplyVO> pagingVO) {
		// TODO Auto-generated method stub
		return dao.selectReplyCount(pagingVO);
	}

	@Override
	public ServiceResult deleteReply(ReplyVO reply) {
		ServiceResult result = null;
		int cnt = dao.deleteReply(reply);
		if(cnt<=0) {
			result=ServiceResult.FAILED;
		}else {
			result=ServiceResult.OK;
		}
		return result;
	}

	@Override
	public ServiceResult updateReply(ReplyVO reply) {
		ServiceResult result = null;
		int cnt = dao.updateReply(reply);
		if(cnt<=0) {
			result=ServiceResult.FAILED;
		}else {
			result=ServiceResult.OK;
		}
		return result;
	}

	@Override
	public ServiceResult insertReply(ReplyVO reply) {
		ServiceResult result = null;
		int cnt = dao.insertReply(reply);
		if(cnt<=0) {
			result=ServiceResult.FAILED;
		}else {
			result=ServiceResult.OK;
		}
		return result;
	}

	

	@Override
	public int selectReply(ReplyVO reply) {
		// TODO Auto-generated method stub
		return dao.selectReply(reply);
	}

	@Override
	public ServiceResult eliminateReply(ReplyVO reply) {
		ServiceResult result = null;
		int cnt = dao.eliminateReply(reply);
		if(cnt<=0) {
			result=ServiceResult.FAILED;
		}else {
			result=ServiceResult.OK;
		}
		return result;
	}

}
