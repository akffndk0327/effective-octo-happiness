package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.Reply2VO;

public interface IReplyService {
	public ServiceResult createReply(Reply2VO reply); //등록성공, 실패
	public int retriveReplyCount(PagingInfoVO<Reply2VO> pagingVO);
	public List<Reply2VO> retriveReplyList(PagingInfoVO<Reply2VO> pagingVO);
	public ServiceResult modifyReply(Reply2VO reply); //잇거나 없거나 인증성공실패, 수정성공실패
	public ServiceResult removeReply(Reply2VO reply);//경우의수 3 db쿼라에서 인증.  있거나없거나 인증실패, 삭제실패(인증실패)
	
}
