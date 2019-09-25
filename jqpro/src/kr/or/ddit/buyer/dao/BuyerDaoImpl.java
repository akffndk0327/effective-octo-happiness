package kr.or.ddit.buyer.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.ibatis.config.SqlMapClientFactory;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BuyerDaoImpl implements IBuyerDao {
	private SqlMapClient smc;
	private static IBuyerDao dao;
	
	private BuyerDaoImpl(){
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IBuyerDao getInstance(){
		if(dao==null){
			dao = new BuyerDaoImpl();
		}
		return dao;
	}

	@Override
	public List<BuyerVO> selectNameList() throws SQLException {
		//vo가 모여서 list가 됨.
		return smc.queryForList("buyer.selectNameList");
	}

	@Override
	public BuyerVO buyerDetail(String buyer_id) throws SQLException { 
		//가로로 한줄 		
		return (BuyerVO) smc.queryForObject("buyer.buyerDetail",buyer_id);
	}

}
