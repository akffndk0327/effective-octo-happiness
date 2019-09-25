package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

public interface IMemberService {
	public List<MemberVO> selectAll();
	
	public String selectByid(String id);
	
	public List<ZipVO> selectByDong(String dong);
	
	public String insertMember(MemberVO vo);
	
	public List<ZipVO> selectSido() ;
	
	public List<ZipVO> selectGugun(String gugun);
	
	public List<ZipVO> selectDong(ZipVO vo) ;
	
	public List<ZipVO> selectAll(ZipVO vo);
}