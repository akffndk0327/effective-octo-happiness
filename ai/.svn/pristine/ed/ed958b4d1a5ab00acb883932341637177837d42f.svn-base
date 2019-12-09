package kr.or.ddit.advertiseReply.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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

@Repository
public interface IAdReplyDAO {
	public int insertReply(AdreplyVO reply);
	public int selectReplyCount(PagingInfoVO<AdreplyVO> pagingVO);
	public List<AdreplyVO> selectReplyList(PagingInfoVO<AdreplyVO> pagingVO);
	public int updateReply(AdreplyVO reply);
	public int deleteReply(AdreplyVO reply);
}
