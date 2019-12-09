package kr.or.ddit.mypage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CompanyVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;

/**
 * @author 허민지
 * @since 2019. 11. 23.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 23.      허민지       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IAdminMypageDAO {

	public List<MemberVO> selectMemberList(PagingInfoVO<MemberVO> pagingVO);
	
	public MemberVO selectMember(MemberVO member);
	
	public List<CompanyVO> selectCompanyList(PagingInfoVO<CompanyVO> pagingVO);
	
	public int selectMemberCount(PagingInfoVO<MemberVO> pagingVO);
	
	public int selectChorme();
	
	public int selectFireFox();
	
	public int selectEx();
	
	public int selectSafari();
	
	public int selectOthers();
	
	public int selectWindows();
	
	public int selectLinux();
	
	public int selectAndroid();
	
	public int selectIOS();
	
	public int selectEtc();
	
	
	/**
	 * 제품 판매 순위 10 리스트
	 * @author 최서희
	 */
	public List<ProdVO> selectSaleProd();
	
	

}
