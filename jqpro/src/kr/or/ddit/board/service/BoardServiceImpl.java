package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;

public class BoardServiceImpl implements IBoardService {
	//다오 객체 얻기.
	private IBoardDao dao ;
	//서비스 객체 생성하기 
	private static IBoardService service;
	
		
	private  BoardServiceImpl(){
		dao = BoardDaoImpl.getInstance(); //다오 메서드 호출 
	}
	
	public static IBoardService getInstance(){
		if(service ==null){
			service = new BoardServiceImpl();
		}
		return service;
	}
	
	@Override
	public List<BoardVO> selectBoard() {
		List<BoardVO> list = null;
		try {
			list = dao.selectBoard();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BoardVO> selectByPage(Map<String, Object> map) {
		List<BoardVO> list =null;
		try {
			list = dao.selectByPage(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int countList() {
		 int count = 0;
		 try {
			count = dao.countList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	//0924
	@Override
	public int insertBoard(BoardVO vo) {
		int seq = 0;
		try {
			seq=dao.insertBoard(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seq;
	}

	@Override
	public int insertReply(ReplyVO vo) {
		int rpl = 0;
		try {
			rpl = dao.insertReply(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rpl;
	}

	@Override
	public List<ReplyVO> replyList(int bonum) {
		List<ReplyVO> rpl = null;
		try {
			rpl = dao.replyList(bonum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rpl;
	}

	@Override
	public int updateReply(ReplyVO vo) {
		int rpl = 0;
		try {
			rpl = dao.updateReply(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rpl;
	}

	@Override
	public int deleteReply(int renum) {
		int rpl = 0;
		try {
			rpl = dao.deleteReply(renum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rpl;
	}

	@Override
	public int deleteBoard(int seq) {
		int board = 0;
		try {
			board=dao.deleteBoard(seq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;
	}

}
