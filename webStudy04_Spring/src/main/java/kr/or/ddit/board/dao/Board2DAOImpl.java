package kr.or.ddit.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.PagingInfoVO;

@Repository
public class Board2DAOImpl implements IBoard2DAO {
	//1017 
	// @Inject
	// SqlSessionFactory sqlSessionFactory ;

	@Inject
	SqlSessionTemplate sqlSession;

	@Override
	public int insertBoard(Board2VO board) {
	// try(SqlSession sqlSession = sqlSessionFactory.openSession();){
		IBoard2DAO mapper = sqlSession.getMapper(IBoard2DAO.class);
		int cnt = mapper.insertBoard(board);
//		sqlSession.commit();
		return cnt;
		// }
	}

//	@Override
//	public int insertBoard(Board2VO board, SqlSession sqlSession) {
//		IBoard2DAO mapper = sqlSession.getMapper(IBoard2DAO.class);
//		return mapper.insertBoard(board);
//	}

	@Override
	public int updateBoardHit(Board2VO board) {
		IBoard2DAO mapper = sqlSession.getMapper(IBoard2DAO.class);
		int cnt = mapper.updateBoardHit(board);
//		sqlSession.commit();
		return cnt;
	}

	@Override
	public Board2VO selectBoard(Board2VO board) {
		IBoard2DAO mapper = sqlSession.getMapper(IBoard2DAO.class);
		return mapper.selectBoard(board);
	}

	@Override
	public int selectBoardCount(PagingInfoVO<Board2VO> pagingVO) {
		IBoard2DAO mapper = sqlSession.getMapper(IBoard2DAO.class);
		return mapper.selectBoardCount(pagingVO);
	}

	@Override
	public List<Board2VO> selectBoardList(PagingInfoVO<Board2VO> pagingVO) {
		IBoard2DAO mapper = sqlSession.getMapper(IBoard2DAO.class);
		return mapper.selectBoardList(pagingVO);
	}


	@Override
	public int updateBoard(Board2VO board) {
		IBoard2DAO mapper = sqlSession.getMapper(IBoard2DAO.class);
		return mapper.updateBoard(board);
	}


	@Override
	public int deleteBoard(Board2VO board) { // 세션 받을 거만 필요해
		IBoard2DAO mapper = sqlSession.getMapper(IBoard2DAO.class);
		return mapper.deleteBoard(board);
	}

	@Override
	public int updateBoLike(Board2VO board) {
		IBoard2DAO mapper = sqlSession.getMapper(IBoard2DAO.class);
		int cnt = mapper.updateBoLike(board);
//		sqlSession.commit(); // 트랜젝션 관리할 필요없어서 다오에서 세션오픈
		return cnt;
	}

}
