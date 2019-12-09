package kr.or.ddit.notice.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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
@Repository
public interface INoticeDAO {
	public int selectNoticeCount();
	public List<NoticeVO> selectNoticeList(PagingInfoVO<NoticeVO> pagingVO);
	public NoticeVO selectNotice(int noticeNo);
	public int insertNotice(NoticeVO notice); 
	public int updateNoticeHit(int noticeNo); //조회수 증가
	public int updateNotice(NoticeVO notice);
	public int deleteNotice(int noticeNo);
}
