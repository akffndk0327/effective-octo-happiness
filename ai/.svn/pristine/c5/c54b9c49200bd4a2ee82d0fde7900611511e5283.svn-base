package kr.or.ddit.recipereply.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ReplyVO;

/**
 * @author 이유진
 * @since 2019. 11. 6.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 6.      이유진       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
public interface IReplyService {
	public List<ReplyVO> retrieveReply(PagingInfoVO<ReplyVO> pagingVO);
	public int selectReplyCount(PagingInfoVO<ReplyVO> pagingVO);
	public ServiceResult deleteReply(ReplyVO reply);
	public ServiceResult updateReply(ReplyVO reply);
	public ServiceResult insertReply(ReplyVO reply);
	public int selectReply(ReplyVO reply);
	public ServiceResult eliminateReply(ReplyVO reply);
}
