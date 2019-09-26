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
	
	//글 삭제
	public int deleteBoard(int seq)throws SQLException;
	//글수정
	public int updateBoard(BoardVO vo) throws SQLException;
	
	public int insertReply(ReplyVO vo) throws SQLException; 
	public List<ReplyVO> replyList(int bonum) throws SQLException;
	
	//댓글 수정
	public int updateReply(ReplyVO vo) throws SQLException; //내용이랑 번호 가져감 
		//삭제
	public int deleteReply(int renum) throws SQLException;
	
	//조회수 증가 와 select가 같이 성공해야됨
	public int updateHit(int seq) throws SQLException;
	
	//조회수 select 
	public int selectHit(int seq) throws SQLException;
	
	
}
