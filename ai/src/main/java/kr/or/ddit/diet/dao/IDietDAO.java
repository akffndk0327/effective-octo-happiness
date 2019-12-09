package kr.or.ddit.diet.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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
 * 수정일                          수정자               수정내용
 * 2019.11.14		박슬기	식단 추가
 * 2019.11.13		박슬기	하루식단 수정
 * 2019. 11. 11    박슬기       리스트조회
 * --------     --------    ----------------------
 * 2019. 11. 7.      박슬기       최초작성
 * 2019.11.8	   박슬기      상세조회 추가
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IDietDAO {
	//식단 공유게시판 리스트 조회
	public List<MonthlyVO> selectDietList(PagingInfoVO<MonthlyVO> pagingVO);
	
	//페이징 처리를 위한 게시글 갯수 조회
	public int selectDietCount(PagingInfoVO<MonthlyVO> pagingVO);
	
	//식단상세조회
	public List<OnedayVO> selectDiet(String monthlyId);
	
	//메뉴 리스트 조회
	public List<MenuVO> selectMenuList();
	
	//회원별 알레르기 성분 포함 메뉴 리스트 조회
	public List<MenuAllergyVO> selectAllergyMenuList(String memId);
	
	//식단 공유 게시글 수정
	public int updateMonthly(MonthlyVO monthlyVO);
	
	//하루식단 수정
	public int updateOneday(OnedayVO onedayVO);
	
	//식단 작성
	public int insertMonthly(MonthlyVO monthlyVO);
	
	//하루식단 작성
	public int insertOneday(OnedayVO OnedayVO);
	
	//식단 삭제
	public int deleteDiet(String monthlyId);
	
	//멤버별 알레르기 증상 포함 리스트 조회
	public List<MenuAllergyVO>selectMemAllergyMenuList(String memId);
	
	//멤버별 반찬 사용 빈도수
	public List<MenuVO> selectCountBan(String memId);
	
	//멤버별 국 사용 빈도수
	public List<MenuVO> selectCountGook(String memId);
	
	//멤버별 밥 사용 빈도수
	public List<MenuVO> selectCountRice(String memId);
	
	//멤버별 김치 사용 빈도수
	public List<MenuVO> selectCountKim(String memId);
	
	//Insert를 하기 위한 Oneday_id maxValue
	public String selectMaxOnedayId();
	
	public List<AllergyVO> selectMemberAllergyName(String memId);
	
	public List<WeightVO> selectWeight();
	
	public List<MenuVO> selectRecommend();
}
