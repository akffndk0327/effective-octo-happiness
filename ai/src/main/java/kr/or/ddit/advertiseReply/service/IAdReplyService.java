package kr.or.ddit.advertiseReply.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.AdreplyVO;
import kr.or.ddit.vo.PagingInfoVO;
/**
 * 
 * @author 박주연
 * @since 2019. 11. 15.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                        		  수정자               	수정내용
 * -------------      --------    ----------------------
 * 2019. 11. 15.        박주연		 최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
public interface IAdReplyService {
	public ServiceResult createReply(AdreplyVO reply);
	public int retrieveReplyCount(PagingInfoVO<AdreplyVO> pagingVO);
	public List<AdreplyVO> retrieveReplyList(PagingInfoVO<AdreplyVO> pagingVO);
	public ServiceResult modifyReply(AdreplyVO reply);
	public ServiceResult removeReply(AdreplyVO reply);
}
