package kr.or.ddit.chemicals.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.chemicals.dao.IChemicalsDAO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.vo.ChemicalsVO;
import kr.or.ddit.vo.ProdChemicalVO;
import kr.or.ddit.vo.ProdNutritionVO;
import kr.or.ddit.vo.RawMaterialVO;

/**
 * @author 최서희
 * @since 2019. 11. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 19.      최서희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Service
public class ChemicalsServiceImpl implements IChemicalsService {
	@Inject IChemicalsDAO dao;
	@Inject	IProdDAO prodDao;
	
	@Override
	public List<ChemicalsVO> retrieveChemicals(String name) {
		return dao.selectChemicals(name);
	}

	@Override
	public List<RawMaterialVO> retrieveRawMaterial(String name) {
		return dao.selectRawMaterial(name);
	}

	@Override
	public ServiceResult createProdChemicals(ProdChemicalVO prodChemical) {
		ServiceResult result = null;
		int cnt = dao.insertProdChemicals(prodChemical);
		cnt+= prodDao.updateProdApproval(prodChemical.getProdId());
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult createProdRaws(ProdNutritionVO prodRaw) {
		ServiceResult result = null;
		int cnt = dao.insertProdRaws(prodRaw);
		cnt+= prodDao.updateProdApproval(prodRaw.getProdId());
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<ProdChemicalVO> retrieveProdChemicals(String prodId) {
		return dao.selectProdChemicals(prodId);
	}

	@Override
	public List<ProdNutritionVO> retrieveProdRawMaterial(String prodId) {
		return dao.selectProdRawMaterial(prodId);
	}

}
