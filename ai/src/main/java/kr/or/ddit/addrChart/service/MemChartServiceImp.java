package kr.or.ddit.addrChart.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.addrChart.dao.IMemChartDAO;
import kr.or.ddit.vo.AllergyVO;
import kr.or.ddit.vo.MemberVO;

@Service
public class MemChartServiceImp implements IMemChartService{

	@Inject
	IMemChartDAO dao;
	
	@Override
	public AllergyVO selectGender(MemberVO vo) {
		return dao.selectGender(vo);
	}

	@Override
	public AllergyVO selectAddr(MemberVO vo) {
		return dao.selectAddr(vo);
	}

	@Override
	public AllergyVO selectAge(MemberVO vo) {
		return dao.selectAge(vo);
	}

}
