package kr.or.ddit.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.notice.dao.INoticeDAO;
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
@Service
public class NoticeServiceImpl implements INoticeService{
	@Inject INoticeDAO dao;
	
	@Override
	public int retrieveNoticeCount() {
		return dao.selectNoticeCount();
	}

	@Override
	public List<NoticeVO> retrieveNoticeList(PagingInfoVO<NoticeVO> pagingVO) {
		return dao.selectNoticeList(pagingVO);
	}

	@Override
	public NoticeVO retrieveNotice(int noticeNo) {
		dao.updateNoticeHit(noticeNo);
		return dao.selectNotice(noticeNo);
	}

	@Override
	public ServiceResult createNotice(NoticeVO notice) {
		ServiceResult result = null;
		int cnt = dao.insertNotice(notice);
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult modifyNotice(NoticeVO notice) {
		ServiceResult result = null;
		int cnt = dao.updateNotice(notice);
		if(cnt<0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult removeNotice(int noticeNo) {
		ServiceResult result = null;
		int cnt = dao.deleteNotice(noticeNo);
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	


}
