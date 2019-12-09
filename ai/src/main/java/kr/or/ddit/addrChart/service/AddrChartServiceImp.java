package kr.or.ddit.addrChart.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.addrChart.dao.IAddrChartDAO;

/**
 * @author 이진희
 * @since 2019. 11. 9.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 9.      이진희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class AddrChartServiceImp implements IAddrChartService{
	
	@Inject
	IAddrChartDAO dao;

	@Override
	public int selectSeoulAsthma() {
		return dao.selectSeoulAsthma();
	}

	@Override
	public int selectBusanAsthma() {
		return dao.selectBusanAsthma();
	}

	@Override
	public int selectDaeguAsthma() {
		return dao.selectDaeguAsthma();
	}

	@Override
	public int selectGwangjuAsthma() {
		return dao.selectGwangjuAsthma();
	}

	@Override
	public int selectDaejeonAsthma() {
		return dao.selectDaejeonAsthma();
	}

	@Override
	public int selectUlsanAsthma() {
		return dao.selectUlsanAsthma();
	}

	@Override
	public int selectChungbukAsthma() {
		return dao.selectChungbukAsthma();
	}

	@Override
	public int selectJeonnamAsthma() {
		return dao.selectJeonnamAsthma();
	}

	@Override
	public int selectJejuAsthma() {
		return dao.selectJejuAsthma();
	}

	@Override
	public int selectSeoulAtopy() {
		return dao.selectSeoulAtopy();
	}

	@Override
	public int selectBusanAtopy() {
		return dao.selectBusanAtopy();
	}

	@Override
	public int selectDaeguAtopy() {
		return dao.selectDaeguAtopy();
	}

	@Override
	public int selectGwangjuAtopy() {
		return dao.selectGwangjuAtopy();
	}

	@Override
	public int selectDaejeonAtopy() {
		return dao.selectDaejeonAtopy();
	}

	@Override
	public int selectUlsanAtopy() {
		return dao.selectUlsanAtopy();
	}

	@Override
	public int selectChungbukAtopy() {
		return dao.selectChungbukAtopy();
	}

	@Override
	public int selectJeonnamAtopy() {
		return dao.selectJeonnamAtopy();
	}

	@Override
	public int selectJejuAtopy() {
		return dao.selectJejuAtopy();
	}

	@Override
	public int selectSeoulRhinitis() {
		return dao.selectSeoulRhinitis();
	}

	@Override
	public int selectBusanRhinitis() {
		return dao.selectBusanRhinitis();
	}

	@Override
	public int selectDaeguRhinitis() {
		return dao.selectDaeguRhinitis();
	}

	@Override
	public int selectGwangjuRhinitis() {
		return dao.selectGwangjuRhinitis();
	}

	@Override
	public int selectDaejeonRhinitis() {
		return dao.selectDaejeonRhinitis();
	}

	@Override
	public int selectUlsanRhinitis() {
		return dao.selectUlsanRhinitis();
	}

	@Override
	public int selectChungbukRhinitis() {
		return dao.selectChungbukRhinitis();
	}

	@Override
	public int selectJeonnamRhinitis() {
		return dao.selectJeonnamRhinitis();
	}

	@Override
	public int selectJejuRhinitis() {
		return dao.selectJejuRhinitis();
	}

	@Override
	public int selectOneAtopy() {
		return dao.selectOneAtopy();
	}

	@Override
	public int selectOneAsthma() {
		return dao.selectOneAsthma();
	}

	@Override
	public int selectOneRhinitis() {
		return dao.selectOneRhinitis();
	}

	@Override
	public int selectTwoAtopy() {
		return dao.selectTwoAtopy();
	}

	@Override
	public int selectTwoAsthma() {
		return dao.selectTwoAsthma();
	}

	@Override
	public int selectTwoRhinitis() {
		return dao.selectTwoRhinitis();
	}

	@Override
	public int selectThrAtopy() {
		return dao.selectThrAtopy();
	}

	@Override
	public int selectThrAsthma() {
		return dao.selectThrAsthma();
	}

	@Override
	public int selectThrRhinitis() {
		return dao.selectThrRhinitis();
	}

	@Override
	public int selectFourAtopy() {
		return dao.selectFourAtopy();
	}

	@Override
	public int selectFourAsthma() {
		return dao.selectFourAsthma();
	}

	@Override
	public int selectFourRhinitis() {
		return dao.selectFourRhinitis();
	}

	@Override
	public int selectFiveAtopy() {
		return dao.selectFiveAtopy();
	}

	@Override
	public int selectFiveAsthma() {
		return dao.selectFiveAsthma();
	}

	@Override
	public int selectFiveRhinitis() {
		return dao.selectFiveRhinitis();
	}

	@Override
	public int selectManAtopy() {
		return dao.selectManAtopy();
	}

	@Override
	public int selectManAsthma() {
		return dao.selectManAsthma();
	}

	@Override
	public int selectManRhinitis() {
		return dao.selectManRhinitis();
	}

	@Override
	public int selectWoAtopy() {
		return dao.selectWoAtopy();
	}

	@Override
	public int selectWoAsthma() {
		return dao.selectWoAsthma();
	}

	@Override
	public int selectWoRhinitis() {
		return dao.selectWoRhinitis();
	}

	@Override
	public int selectWoShell() {
		return dao.selectWoShell();
	}

	@Override
	public int selectWoEgg() {
		return dao.selectWoEgg();
	}

	@Override
	public int selectWoMilk() {
		return dao.selectWoMilk();
	}

	@Override
	public int selectWoNut() {
		return dao.selectWoNut();
	}

	@Override
	public int selectManShell() {
		return dao.selectManShell();
	}

	@Override
	public int selectManEgg() {
		return dao.selectManEgg();
	}

	@Override
	public int selectManMilk() {
		return dao.selectManMilk();
	}

	@Override
	public int selectManNut() {
		return dao.selectManNut();
	}	
}
