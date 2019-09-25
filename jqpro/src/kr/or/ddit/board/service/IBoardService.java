package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;

public interface IBoardService {
	//CURD
	
	//전체 list가져오기 : 파라미터 안쓰면돼
	public List<BoardVO> selectBoard();  
	
	public List<BoardVO> selectByPage(Map<String, Object> map);
	
	public int countList();
	
	//새글 작성 저장하기 
	public int insertBoard(BoardVO vo);
	
	//댓글 작성
	public int insertReply(ReplyVO vo) ; 
	
	//댓글 리스트 
	public List<ReplyVO> replyList(int bonum);
	
	//댓글 수정 
	public int updateReply(ReplyVO vo); //내용이랑 번호 가져감 
	
	public int deleteReply(int renum);
	
}
