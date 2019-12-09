package kr.or.ddit.othersallergies.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.othersallergies.dao.IOthersAllergiesDAO;
import kr.or.ddit.vo.AllergyVO;

@Service
public class OthersAllergiesServiceImpl implements IOthersAllergiesService {

	@ Inject
	IOthersAllergiesDAO otherAllergiesDAO;
	
	@Override
	public AllergyVO retrieveOthersAllergies(String allId) {
		return otherAllergiesDAO.selectOthersAllergies(allId);
	}

	@Override
	public ServiceResult modifyOthersAllergies(AllergyVO allergy) {
		ServiceResult result = null;
		int cnt = otherAllergiesDAO.updateOthersAllergies(allergy);
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	
}
