package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

public class MemberServiceImpl implements IMemberService {
	private static IMemberService service;
	private IMemberDao dao;
	
	private MemberServiceImpl(){
		dao = MemberDaoImpl.getInstance();
	}
	
	public static IMemberService getInstance(){
		if(service==null) service = new MemberServiceImpl();
		return service;
	}

	@Override
	public List<MemberVO> selectAll() {
		List<MemberVO> list = null;
		try {
			list = dao.selectAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String selectByid(String id) {
		String  getId  = null; 
		try {
			getId = dao.selectByid(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return getId;
	}

	@Override
	public List<ZipVO> selectByDong(String dong) {
		List<ZipVO> list = null;
		try {
			list = dao.selectByDong(dong);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String insertMember(MemberVO vo) {
		String resid=null;
		try {
			resid = dao.insertMember(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resid;
	}

	
	@Override
	public List<ZipVO> selectSido(){
		List<ZipVO> list = null;
		try {
			list = dao.selectSido();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ZipVO> selectGugun(String gugun){
		List<ZipVO> list = null;
		try {
			list= dao.selectGugun(gugun);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ZipVO> selectDong(ZipVO vo){
		List<ZipVO> list = null;
		try {
			list=dao.selectDong(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ZipVO> selectAll(ZipVO vo) {
		List<ZipVO> list = null;
		try {
			list=dao.selectAll(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


}
