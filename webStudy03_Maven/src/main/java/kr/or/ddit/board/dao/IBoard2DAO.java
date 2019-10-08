package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.PagingInfoVO;

public interface IBoard2DAO {
	//crud
	
	public int insertBoard(Board2VO board); //매핑파일에 바운딩만 하고 실제로는 아래 메서드로 사용 
	public int insertBoard(Board2VO board, SqlSession sqlSession); //서비스에서 써먹, 여기는 세션 받기만 .매핑파일에 바운딩못해
	public int updateBoardHit(Board2VO board); //조회수증가
	public Board2VO selectBoard(Board2VO board); //보드 단건 조회 검색한거만 가져와
	public int selectBoardCount(PagingInfoVO<Board2VO> pagingVO);
	public List<Board2VO> selectBoardList(PagingInfoVO<Board2VO> pagingVO); //다건 조회 모두 가져와
	public int updateBoard(Board2VO board); //쿼리 3개 하나로 묵여야함
	public int updateBoard(Board2VO board, SqlSession sqlSession); //쿼리 3개 하나로 묵여야함
	
	public int deleteBoard(Board2VO board); //attactch 지우고 baord 지워야함  
	public int deleteBoard(Board2VO board,SqlSession sqlSession); //마이바티스는 이 구조 허용ㅎ안해서 위에 같은 거 씀 
	public int updateBoLike(Board2VO board); // 추천수 
	
}
