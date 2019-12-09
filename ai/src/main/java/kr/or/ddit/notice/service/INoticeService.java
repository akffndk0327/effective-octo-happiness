package kr.or.ddit.notice.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PagingInfoVO;

/**
 * @author 최서희
 * @since 2019. 11. 20.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * -------------   --------    ----------------------
 * 2019. 11. 20.     최서희             최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
public interface INoticeService {
	public int retrieveNoticeCount();
	public List<NoticeVO> retrieveNoticeList(PagingInfoVO<NoticeVO> pagingVO);
	public NoticeVO retrieveNotice(int noticeNo);
	public ServiceResult createNotice(NoticeVO notice); 
	public ServiceResult modifyNotice(NoticeVO notice);
	public ServiceResult removeNotice(int noticeNo);

}
