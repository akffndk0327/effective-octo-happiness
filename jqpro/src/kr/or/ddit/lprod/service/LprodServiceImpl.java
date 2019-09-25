package kr.or.ddit.lprod.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.dao.LprodDaoImpl;
import kr.or.ddit.lprod.vo.LprodVO;
import kr.or.ddit.member.vo.MemberVO;

public class LprodServiceImpl implements ILprodService {
	private ILprodDao dao;
	private static ILprodService service;
	
	private LprodServiceImpl(){
		dao = LprodDaoImpl.getInstance();
	}
	public static ILprodService getInstance(){
		if(service ==null)service =new LprodServiceImpl();
		return service;
	}
	@Override
	public List<LprodVO> selectAll() {
		List<LprodVO> list = null;
		try {
			list = dao.selectAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
