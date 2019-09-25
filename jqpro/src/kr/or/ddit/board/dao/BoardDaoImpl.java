package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;
import kr.or.ddit.ibatis.config.SqlMapClientFactory;

public class BoardDaoImpl implements IBoardDao {
	//다오 객체 생성
	private static IBoardDao dao;
	//sqlMapClient 객체 얻기
	private SqlMapClient smc ; 
	
	private BoardDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IBoardDao getInstance(){
		if(dao ==null){
			dao = new BoardDaoImpl();
		}
		return dao;
	}
	
	@Override
	public List<BoardVO> selectBoard() throws SQLException {
		
		return smc.queryForList("board.selectBoard");
	}

	@Override
	public List<BoardVO> selectByPage(Map<String, Object> map)throws SQLException {
	
		return smc.queryForList("board.selectByPage",map);
	}

	@Override
	public int countList() throws SQLException {
		
		return (Integer)smc.queryForObject("board.countList");
	}
	//0924
	@Override
	public int insertBoard(BoardVO vo) throws SQLException {
		return (Integer)smc.insert("board.insertBoard",vo);
				
	}

	@Override
	public int insertReply(ReplyVO vo) throws SQLException {
		return (Integer) smc.insert("board.insertReply",vo);
	}

	@Override
	public List<ReplyVO> replyList(int bonum) throws SQLException {
		return smc.queryForList("board.replyList",bonum);
	}

	@Override
	public int updateReply(ReplyVO vo) throws SQLException {
		return smc.update("board.updateReply",vo);
	}

	@Override
	public int deleteReply(int renum) throws SQLException {
		return smc.delete("board.deleteReply",renum);
	}

	@Override
	public int deleteBoard(int seq)throws SQLException {
		
		return smc.delete("board.deleteBoard",seq);
	}
	
}
