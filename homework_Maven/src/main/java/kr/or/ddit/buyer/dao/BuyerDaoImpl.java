package kr.or.ddit.buyer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.db.ConnectionFactory;

public class BuyerDaoImpl implements IbuyerDao {
	private static BuyerDaoImpl dao;
	private BuyerDaoImpl() {};
	
	public static BuyerDaoImpl getInstance() {
		if(dao == null) {
			dao =new BuyerDaoImpl();
		}
		return dao;
	}
	
	

	@Override
	public List<BuyerVO> selectBuyerList() {
		List<BuyerVO> list = new ArrayList<BuyerVO>();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT                                            ");
		sql.append(" buyer_id buyer_name,    buyer_lgu,    buyer_bank,      	   ");
		sql.append(" buyer_bankno,    buyer_bankname,        ");
		sql.append(" buyer_add1,    buyer_mail             ");
		sql.append(" FROM  buyer    					     	        ");
		sql.append(" where buyer_id = ?    					     	        ");
	    
	    try (
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			ResultSet rs = pstmt.executeQuery(); // 쿼리문을 실행...
			while (rs.next()) {
				BuyerVO buyer = new BuyerVO();
				list.add(buyer);
				buyer.setBuyer_name(rs.getString("Buyer_name"));
				buyer.setBuyer_lgu(rs.getString("Buyer_lgu"));
				buyer.setBuyer_bank(rs.getString("Buyer_bank"));
				buyer.setBuyer_bankno(rs.getString("Buyer_bankno"));
				buyer.setBuyer_bankname(rs.getString("Buyer_bankname"));
				buyer.setBuyer_zip(rs.getString("Buyer_zip"));
				buyer.setBuyer_add1(rs.getString("Buyer_add1"));
				buyer.setBuyer_add2(rs.getString("Buyer_add2"));
				buyer.setBuyer_mail(rs.getString("Buyer_mail"));
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	    


}
