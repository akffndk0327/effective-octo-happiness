package kr.or.ddit.prod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.ProdVO;

public class ProdDaoImpl_JDBC implements IProdDAO {
	private static ProdDaoImpl_JDBC dao ;
	private ProdDaoImpl_JDBC() {}
	
	public static ProdDaoImpl_JDBC getInstance() {
		if(dao==null) {
			dao = new ProdDaoImpl_JDBC();
		}
		return dao;
	}
	
	@Override
	public int insertProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//전체 리스트 출력
	@Override
	public List<ProdVO> selectProdList() {
		List<ProdVO> list = new ArrayList<ProdVO>();
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT                                             ");
	    sql.append(" a.prod_id, a.prod_name,  b.lprod_nm,  c.buyer_name,");
	    sql.append(" a.prod_cost, a.prod_price,  a.prod_mileage         ");
	    sql.append(" FROM  PROD a, LPROD b, BUYER c                   ");
	    sql.append(" WHERE a.PROD_LGU = b.LPROD_gu and                                     ");
	    sql.append(" a.PROD_BUYER  = c.BUYER_ID                        ");

		try (
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProdVO prod = new ProdVO();
				prod.setProd_id(rs.getString("PROD_ID"));
				prod.setProd_name(rs.getString("PROD_NAME"));
				prod.setLprod_nm(rs.getString("LPROD_NM"));
				prod.setBuyer_name(rs.getString("BUYER_NAME"));
				prod.setProd_cost(rs.getInt("PROD_COST"));
				prod.setProd_price(rs.getInt("PROD_PRICE"));
				prod.setProd_mileage(rs.getInt("PROD_MILEAGE"));
				list.add(prod);
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public ProdVO selectProd(String prod_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateProd(ProdVO prod) {
		// TODO Auto-generated method stub
		return 0;
	}

}
