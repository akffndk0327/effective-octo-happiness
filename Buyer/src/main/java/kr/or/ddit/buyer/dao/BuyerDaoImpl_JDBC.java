package kr.or.ddit.buyer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.db.ConnectionFactory;

public class BuyerDaoImpl_JDBC implements IBuyerDao {
	private static BuyerDaoImpl_JDBC dao;

	private BuyerDaoImpl_JDBC() {
	}

	public static BuyerDaoImpl_JDBC getInstance() {
		if (dao == null) {
			dao = new BuyerDaoImpl_JDBC();
		}
		return dao;
	}

	@Override
	public List<BuyerVO> selectNameList() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT BUYER_ID, BUYER_NAME      ");
		sql.append("FROM BUYER    ");

		List<BuyerVO> list = new ArrayList<BuyerVO>();
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BuyerVO buyer = new BuyerVO();
				buyer.setBuyer_id(rs.getString("BUYER_ID"));
				buyer.setBuyer_name(rs.getString("BUYER_NAME"));
				list.add(buyer);
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public BuyerVO buyerDetail(String buyer_id) {
		StringBuffer sql = new StringBuffer();
		List<BuyerVO> b_list = new ArrayList<BuyerVO>();
		sql.append("select buyer_id, ");
		sql.append("buyer_name, ");
		sql.append("buyer_lgu, ");
		sql.append("buyer_bank, ");
		sql.append("buyer_bankno, ");
		sql.append("buyer_bankname, ");
		sql.append("buyer_zip, ");
		sql.append("buyer_add1, ");
		sql.append("buyer_add2, ");
		sql.append("buyer_comtel, ");
		sql.append("buyer_mail ");
		sql.append(" from buyer");
		sql.append(" where buyer_id = ?");

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, buyer_id);

			ResultSet rs = pstmt.executeQuery();
			BuyerVO bv = null;
			if (rs.next()) {
				bv = new BuyerVO();
				bv.setBuyer_id(rs.getString("buyer_id"));
				bv.setBuyer_name(rs.getString("buyer_name"));
				bv.setBuyer_lgu(rs.getString("buyer_lgu"));
				bv.setBuyer_bank(rs.getString("buyer_bank"));
				bv.setBuyer_bankno(rs.getString("buyer_bankno"));
				bv.setBuyer_bankname(rs.getString("buyer_bankname"));
				bv.setBuyer_zip(rs.getString("buyer_zip"));
				bv.setBuyer_add1(rs.getString("buyer_add1"));
				bv.setBuyer_add2(rs.getString("buyer_add2"));
				bv.setBuyer_comtel(rs.getString("buyer_comtel"));
				bv.setBuyer_mail(rs.getString("buyer_mail"));
			}
			return bv;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int buyerInsert(BuyerVO vo) {
		System.out.println(vo.getBuyer_id());
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO BUYER                     ");
		sql.append("(                                     ");
		sql.append("	  BUYER_ID,                          ");
		sql.append("	  BUYER_NAME,                        ");
		sql.append("	  BUYER_LGU,                         ");
		sql.append("	  BUYER_BANK,                        ");
		sql.append("	  BUYER_BANKNO,                      ");
		sql.append("	  BUYER_BANKNAME,                    ");
		sql.append("	  BUYER_ZIP,                         ");
		sql.append("	  BUYER_ADD1,                        ");
		sql.append("	  BUYER_ADD2,                        ");
		sql.append("	  BUYER_COMTEL,                      ");
		sql.append("	  BUYER_MAIL                         ");
		sql.append(")                                     ");
		sql.append("VALUES                                ");
		sql.append("	(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)    ");
		
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			int a = 1;
			pstmt.setString(a++, vo.getBuyer_id());
			pstmt.setString(a++, vo.getBuyer_name());
			pstmt.setString(a++, vo.getBuyer_lgu());
			pstmt.setString(a++, vo.getBuyer_bank());
			pstmt.setString(a++, vo.getBuyer_bankno());
			pstmt.setString(a++, vo.getBuyer_bankname());
			pstmt.setString(a++, vo.getBuyer_zip());
			pstmt.setString(a++, vo.getBuyer_add1());
			pstmt.setString(a++, vo.getBuyer_add2());
			pstmt.setString(a++, vo.getBuyer_comtel());
			pstmt.setString(a++, vo.getBuyer_mail());
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public int buyerDelete(String buyer_id) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM BUYER  ");
		sql.append("WHERE BUYER_ID = ?   ");
		
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());){
				
				pstmt.setString (1, buyer_id);
				
				return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int buyerUpdate(BuyerVO vo) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE buyer  ");
		sql.append("SET  BUYER_NAME = ?, BUYER_LGU = ?, BUYER_BANKNO = ?, BUYER_BANKNAME = ?,");
		sql.append(" BUYER_MAIL = ?, BUYER_ADD1 = ?");
		sql.append("WHERE buyer_id = ?");
		
		int result=0;
		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			int idx=1;
			pstmt.setString(idx++, vo.getBuyer_name());
			pstmt.setString(idx++, vo.getBuyer_lgu());
			pstmt.setString(idx++, vo.getBuyer_bankno());
			pstmt.setString(idx++, vo.getBuyer_bankname());
			pstmt.setString(idx++, vo.getBuyer_mail());
			pstmt.setString(idx++, vo.getBuyer_add1());
			pstmt.setString(idx++, vo.getBuyer_id());
			
			result = pstmt.executeUpdate();
		

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
