package kr.or.ddit.lprod.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.lprod.vo.LprodVO;

public class LprodDaoImpl implements ILprodDao{
	private SqlMapClient smc; 
	private static ILprodDao dao;
	
	private LprodDaoImpl(){
		smc = SqlMapClientFactory.getInstance();
	}
	public static ILprodDao getInstance(){
		if(dao==null){
			dao = new LprodDaoImpl();
		}
		return dao;
	}
	
	@Override
	public List<LprodVO> selectAll() throws SQLException {
		
		return smc.queryForList("lprodTest.getlprodAll"); //mapper xml의 namespace, 쿼리id 입력  
	}
	

}
