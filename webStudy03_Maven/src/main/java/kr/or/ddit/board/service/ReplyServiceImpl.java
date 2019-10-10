package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IReply2DAO;
import kr.or.ddit.board.dao.Reply2DAOImpl;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.Reply2VO;

public class ReplyServiceImpl implements IReplyService {
IReply2DAO dao = new Reply2DAOImpl();
	
	@Override
	public ServiceResult createReply(Reply2VO reply) {
		int cnt = dao.insertReply(reply);
		ServiceResult result = null;
		if(cnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.FAILED;
		return result;
	}

	
	@Override
	public ServiceResult modifyReply(Reply2VO reply) {
		int cnt = dao.updateReply(reply);
		ServiceResult result = null;
		if(cnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.INVALIDPASSWORD;
		return result;
	}

	@Override
	public ServiceResult removeReply(Reply2VO reply) {
		int cnt = dao.deleteReply(reply);
		ServiceResult result = null;
		if(cnt > 0) result = ServiceResult.OK;
		else result = ServiceResult.INVALIDPASSWORD;
		return result;
	}

	@Override
	public int retriveReplyCount(PagingInfoVO<Reply2VO> pagingVO) {
		return dao.selectReplyCount(pagingVO);
	}

	@Override
	public List<Reply2VO> retriveReplyList(PagingInfoVO<Reply2VO> pagingVO) {
		return dao.selectReplyList(pagingVO);
	}
}
