package kr.or.ddit.chemicals.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
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
public interface IChemicalsService {
	
	List<ChemicalsVO> retrieveChemicals(String name);
	List<RawMaterialVO> retrieveRawMaterial(String name);
	
	List<ProdChemicalVO> retrieveProdChemicals(String prodId);
	List<ProdNutritionVO> retrieveProdRawMaterial(String prodId);
	
	ServiceResult createProdChemicals(ProdChemicalVO prodChemical);
	ServiceResult createProdRaws(ProdNutritionVO prodRaw);
}
