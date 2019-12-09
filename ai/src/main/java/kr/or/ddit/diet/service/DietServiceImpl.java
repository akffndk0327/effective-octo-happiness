package kr.or.ddit.diet.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.diet.dao.IDietDAO;
import kr.or.ddit.vo.AllergyVO;
import kr.or.ddit.vo.MenuAllergyVO;
import kr.or.ddit.vo.MenuVO;
import kr.or.ddit.vo.MonthlyVO;
import kr.or.ddit.vo.OnedayVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.WeightVO;

/**
 * 
 * @author 박슬기
 * @since 2019. 11. 8.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *	2019.11.13		박슬기		하루식단 수정
 * --------     --------    ----------------------
 * 2019. 11. 7.      박슬기       최초작성
   2019.11.8	   박슬기      상세조회 추가
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class DietServiceImpl implements IDietService {

	@Inject
	IDietDAO dao;
	
	@Override
	public int retrieveDietCount(PagingInfoVO<MonthlyVO> pagingVO) {
		return dao.selectDietCount(pagingVO);
	}

	@Override
	public List<MonthlyVO> retrieveDietList(PagingInfoVO<MonthlyVO> pagingVO) {
		return dao.selectDietList(pagingVO);
	}

	@Override
	public List<OnedayVO> retrieveDiet(String monthlyId) {
		return dao.selectDiet(monthlyId);
	}

	@Override
	public List<MenuVO> retrievetMenuList() {
		return dao.selectMenuList();
	}

	@Override
	public List<MenuAllergyVO> retrievetAllergyMenuList(String memId) {
		return dao.selectAllergyMenuList(memId);
	}


	@Override
	public int modifyMonthly(MonthlyVO monthlyVO) {
		return dao.updateMonthly(monthlyVO);
	}

	@Override
	public int modifyOneday(OnedayVO onedayVO) {
		return dao.updateOneday(onedayVO);
	}

	@Override
	public int createMonthly(MonthlyVO monthlyVO) {
		return dao.insertMonthly(monthlyVO);
	}

//	ONEDAY_ID,MENU_ID
	@Override 
	public int createOneday(String[] MenuArray) {
		int cnt = 0;
		OnedayVO onedayVO = null;
		String OnedayId = dao.selectMaxOnedayId();
		for (int i = 0; i < MenuArray.length; i++) {
			if (i%5==0) {
				onedayVO = new OnedayVO();
				OnedayId = OnedayId.substring(1);
				int nid = Integer.parseInt(OnedayId)+1;
				OnedayId = nid+"";
				OnedayId = "D0"+OnedayId;
				onedayVO.setOnedayId(OnedayId);
			}
			onedayVO.setMenuId(MenuArray[i]);
			cnt += dao.insertOneday(onedayVO);
		}
		return cnt;
	}

	@Override
	public int removeDiet(String monthlyId) {
		return dao.deleteDiet(monthlyId);
	}

	@Override
	public List<MenuAllergyVO> retrieveMemAllergyMenuList(String memId) {
		return dao.selectMemAllergyMenuList(memId);
	}

	@Override
	public List<MenuVO> retrieveCountBan(String memId) {
		return dao.selectCountBan(memId);
	}

	@Override
	public List<MenuVO> retrieveCountGook(String memId) {
		return dao.selectCountGook(memId);
	}

	@Override
	public List<MenuVO> retrieveCountRice(String memId) {
		return dao.selectCountRice(memId);
	}

	@Override
	public List<MenuVO> retrieveCountKim(String memId) {
		return dao.selectCountKim(memId);
	}

	@Override
	public String retrieveMaxOnedayId() {
		return dao.selectMaxOnedayId();
	}

	@Override
	public List<AllergyVO> retrieveMemberAllergyName(String memId) {
		return dao.selectMemberAllergyName(memId);
	}

	@Override
	public List<WeightVO> retrieveWeight() {
		return dao.selectWeight();
	}

	@Override
	public List<MenuVO> retrieveRecommend() {
		return dao.selectRecommend();
	}

}
