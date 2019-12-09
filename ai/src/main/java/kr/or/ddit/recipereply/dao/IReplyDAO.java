package kr.or.ddit.recipereply.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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

@Repository
public interface IReplyDAO {
	public int selectReplyCount(PagingInfoVO<ReplyVO> pagingVO);
	public List<ReplyVO> selectReplyList(PagingInfoVO<ReplyVO> pagingVO);
	public int deleteReply(ReplyVO reply);
	public int updateReply(ReplyVO reply);
	public int insertReply(ReplyVO reply);
	public int selectReply(ReplyVO reply);
	public int eliminateReply(ReplyVO reply);
}
