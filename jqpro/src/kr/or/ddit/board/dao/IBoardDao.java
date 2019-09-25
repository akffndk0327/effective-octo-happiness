package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;

public interface IBoardDao {
	
	public List<BoardVO> selectBoard() throws SQLException;
	
	public List<BoardVO> selectByPage(Map<String, Object> map) throws SQLException;
	
	public int countList() throws SQLException;
	
	public int insertBoard(BoardVO vo)throws SQLException;
	
	public int insertReply(ReplyVO vo) throws SQLException; 
	public List<ReplyVO> replyList(int bonum) throws SQLException;
	
	//댓글 수정
	public int updateReply(ReplyVO vo) throws SQLException; //내용이랑 번호 가져감 
	
	//삭제
	public int deleteReply(int renum) throws SQLException;
}
