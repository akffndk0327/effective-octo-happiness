package kr.or.ddit.prod.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.prod.vo.ProdVO;

public class ProdDaoImpl implements IprodDao {
	private static IprodDao dao ; //순서1
	private SqlMapClient smc ; //순서2 db연결위해서
	
	public static IprodDao getInstance() { //dao 객체있나 없나 확인 
		if(dao == null){
			dao = new ProdDaoImpl() ;
		}
		return dao;
	}
	
	private ProdDaoImpl(){
		smc=SqlMapClientFactory.getInstance();
	}
	
	@Override
	public List<ProdVO> getBuyLguList(String lprod_gu) throws SQLException {
		
		return smc.queryForList("prod.getBuyLguList",lprod_gu);
	}

	@Override
	public ProdVO getDetail(String prod_id) throws SQLException {
		
		return (ProdVO) smc.queryForObject("prod.getDetail",prod_id);
	}


}
