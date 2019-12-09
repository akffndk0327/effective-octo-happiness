package kr.or.ddit.mypage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.mypage.dao.IAdminMypageDAO;
import kr.or.ddit.vo.CompanyVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

@Service
public class AdminMypageServiceImpl implements IAdminMypageService {

	@Inject
	IAdminMypageDAO adminDAO;
	
	@Override
	public List<MemberVO> retrieveMemberList(PagingInfoVO<MemberVO> pagingVO) {
		return adminDAO.selectMemberList(pagingVO);
	}

	@Override
	public MemberVO retrieveMember(MemberVO member) {
		return adminDAO.selectMember(member);
	}

	@Override
	public List<CompanyVO> retrieveCompanyList(PagingInfoVO<CompanyVO> pagingVO) {
		return adminDAO.selectCompanyList(pagingVO);
	}

	@Override
	public int retrieveMemberCount(PagingInfoVO<MemberVO> pagingVO) {
		return adminDAO.selectMemberCount(pagingVO);
	}

	@Override
	public int selectChorme() {
		return adminDAO.selectChorme();
	}

	@Override
	public int selectFireFox() {
		return adminDAO.selectFireFox();
	}

	@Override
	public int selectEx() {
		return adminDAO.selectEx();
	}

	@Override
	public int selectOthers() {
		return adminDAO.selectOthers();
	}

	@Override
	public int selectWindows() {
		return adminDAO.selectWindows();
	}

	@Override
	public int selectLinux() {
		return adminDAO.selectLinux();
	}

	@Override
	public int selectEtc() {
		return adminDAO.selectEtc();
	}

	@Override
	public int selectSafari() {
		return adminDAO.selectSafari();
	}

	@Override
	public int selectAndroid() {
		return adminDAO.selectAndroid();
	}

	@Override
	public int selectIOS() {
		return adminDAO.selectIOS();
	}

	@Override
	public List<ProdVO> selectSaleProdRankList() {
		return adminDAO.selectSaleProd();
	}

}
