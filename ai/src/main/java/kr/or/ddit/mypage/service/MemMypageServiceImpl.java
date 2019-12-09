package kr.or.ddit.mypage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.mypage.dao.IMemMypageDAO;
import kr.or.ddit.vo.MemAllergyVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MonthlyVO;
import kr.or.ddit.vo.Order2VO;
import kr.or.ddit.vo.PagingInfoVO;

@Service
public class MemMypageServiceImpl implements IMemMypageService {

	@Inject
	IMemMypageDAO mypageDAO;
	
	@Override
	public ServiceResult createMemAllergy(MemberVO member) {
		ServiceResult result = null;
		//멤버 알러지가 있으면
		if(member.getMemAllList().size()>0 && member.getMemAllList()!=null) {
			int cnt = mypageDAO.insertMemAllergy(member);
			if(cnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	
	@Override
	public int removeMemAllergy(MemAllergyVO member) {
		return mypageDAO.deleteMemAllergy(member);
	}


	@Override
	public ServiceResult removeMember(String memId) {
		ServiceResult result = null;
		int cnt = mypageDAO.deleteMember(memId);
		if(cnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}


	@Override
	public List<MonthlyVO> retrieveMemberDietList(PagingInfoVO<MonthlyVO> pagingVO) {
		return mypageDAO.selectMemberDietList(pagingVO);
	}


	@Override
	public int retrieveMemberDietCount(PagingInfoVO<MonthlyVO> pagingVO) {
		return mypageDAO.selectMemberDietCount(pagingVO);
	}


	@Override
	public List<Order2VO> orderByOrderList(String memId) {
		return mypageDAO.orderByOrderList(memId);
	}









	
	
}
