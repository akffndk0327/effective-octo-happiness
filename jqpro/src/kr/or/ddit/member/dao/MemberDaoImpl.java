package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

import com.ibatis.sqlmap.client.SqlMapClient;

public class MemberDaoImpl implements IMemberDao {
	private static IMemberDao dao;
	private SqlMapClient smc;

	public static IMemberDao getInstance(){
		if(dao == null) {
			dao = new MemberDaoImpl();
		}
		return dao;
	}
	
	private MemberDaoImpl(){
		smc = SqlMapClientFactory.getInstance();
	}
	
	
	@Override
	public List<MemberVO> selectAll() throws SQLException {
		
		return smc.queryForList("memberTest.getMemberAll");
	}
	@Override
	public String selectByid(String id) throws SQLException {
		return (String) smc.queryForObject("memberTest.selectByid",id);
	}
	@Override
	public List<ZipVO> selectByDong(String dong) throws SQLException {
		return smc.queryForList("memberTest.selectByDong", dong);
	}
	@Override
	public String insertMember(MemberVO vo) throws SQLException {
		return (String)smc.insert("memberTest.insertMember", vo);
	}


	@Override
	public List<ZipVO> selectSido() throws SQLException {
		return smc.queryForList("memberTest.selectSido");
	}

	@Override
	public List<ZipVO> selectGugun(String gugun) throws SQLException {
		return smc.queryForList("memberTest.selectGugun",gugun);
	}

	@Override
	public List<ZipVO> selectDong(ZipVO vo) throws SQLException {
		return smc.queryForList("memberTest.selectDong",vo);
	}

	@Override
	public List<ZipVO> selectAll(ZipVO vo) throws SQLException {
		
		return smc.queryForList("memberTest.selectAll",vo);
	}
	
	
	
}
