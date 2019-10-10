package kr.or.ddit.board.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.Attatch2VO;
import kr.or.ddit.vo.Board2VO;

public interface IAttatch2DAO {
	public int insertAttatches(Board2VO board);
	public int insertAttatches(Board2VO board, SqlSession sqlSession);  //한꺼번에 board글이랑  첨부파일 받겟다 //단독트랜젝션 x 오버로딩 필요
	public Attatch2VO selectAttatch(int att_no) ; //인증 x , 첨부파일에 대한 모든거 잇어서VO로 받어
//	selectAttatchList 마이바티스에서 조인으로 
//	updateAttatch
	public int deleteAttatches(Board2VO board);
	
	public int deleteAttatches(Board2VO board,SqlSession sqlSession);
	
	int updateDowncount(int att_no);
}
