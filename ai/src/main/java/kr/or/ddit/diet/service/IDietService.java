package kr.or.ddit.diet.service;

import java.util.List;

import org.apache.commons.math3.optim.nonlinear.vector.Weight;

import kr.or.ddit.vo.AllergyVO;
import kr.or.ddit.vo.MenuAllergyVO;
import kr.or.ddit.vo.MenuVO;
import kr.or.ddit.vo.MonthlyVO;
import kr.or.ddit.vo.OnedayVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.WeightVO;

/**
 * 
 * @author 박슬기
 * @since 2019. 11. 7.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일               수정자            수정내용
 * 2019.11.13		박슬기		하루식단 수정
 * --------     --------    ----------------------
 * 2019. 11. 7.      박슬기       최초작성
 * 2019.11.8	   박슬기      상세조회 추가
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
public interface IDietService {
	
	//페이징 처리를 위한 게시글 갯수 조회
	public int retrieveDietCount(PagingInfoVO<MonthlyVO> pagingVO);
	
	//식단 공유게시판 리스트 조회
	public List<MonthlyVO> retrieveDietList(PagingInfoVO<MonthlyVO> pagingVO);
	
	//상세조회
	public List<OnedayVO> retrieveDiet(String monthlyId);
	
	//메뉴 리스트 조회
	public List<MenuVO> retrievetMenuList();
	
	//회원별 알레르기 성분 포함 메뉴 리스트 조회
	public List<MenuAllergyVO> retrievetAllergyMenuList(String memId);
	
	//식단 공유 게시글 수정
	public int modifyMonthly(MonthlyVO monthlyVO);
	
	//하루식단 수정 
	public int modifyOneday(OnedayVO onedayVO);
	
	//식단 작성
	public int createMonthly(MonthlyVO monthlyVO);	
	
	//하루식단 작성
	public int createOneday(String[] MenuArray);	
	
	//식단 삭제
	public int removeDiet(String monthlyId);
	
	//멤버별 알레르기 증상 포함 리스트 조회
	public List<MenuAllergyVO>retrieveMemAllergyMenuList(String memId);
	
	//멤버별 반찬 사용빈도수
	public List<MenuVO> retrieveCountBan(String memId);
	
	//멤버별 국 사용빈도수
	public List<MenuVO> retrieveCountGook(String memId);
	
	//멤버별 밥 사용빈도수
	public List<MenuVO> retrieveCountRice(String memId);
	
	//멤버별 김치 사용빈도수
	public List<MenuVO> retrieveCountKim(String memId);
	
	public String retrieveMaxOnedayId();
	
	public List<AllergyVO> retrieveMemberAllergyName(String memId);
	
	public List<WeightVO> retrieveWeight();
	
	public List<MenuVO> retrieveRecommend();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
