package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

public interface IMemberDao {
	public List<MemberVO> selectAll() throws SQLException;
	
	public String selectByid(String id) throws SQLException;
	
	public List<ZipVO> selectByDong(String dong) throws SQLException;
	
	public String insertMember(MemberVO vo) throws SQLException;
	
	public List<ZipVO> selectSido() throws SQLException;
	
	public List<ZipVO> selectGugun(String gugun) throws SQLException;
	
	public List<ZipVO> selectDong(ZipVO vo) throws SQLException;
	
	public List<ZipVO> selectAll(ZipVO vo) throws SQLException;
	
	
}
