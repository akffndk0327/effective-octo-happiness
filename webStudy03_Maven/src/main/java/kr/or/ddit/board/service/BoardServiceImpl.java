package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.Board2DAOImpl;
import kr.or.ddit.board.dao.IBoard2DAO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.vo.Attatch2VO;
import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.PagingInfoVO;

public class BoardServiceImpl implements IBoardService {
	public IBoard2DAO dao = new Board2DAOImpl();
	
	private static BoardServiceImpl instance ;
	
	public static BoardServiceImpl getInstance() {
		if(instance == null) {
			instance = new BoardServiceImpl();
		}
		return instance;
	}
	@Override
	public ServiceResult createBoard(Board2VO board) {
		int cnt = dao.insertBoard(board);
		ServiceResult result = null;
		if(dao.selectBoard(board)==null) {
			if(cnt>0) result = ServiceResult.OK;
			else result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public Board2VO retrieveBoard(Board2VO board) {
		Board2VO saved = dao. selectBoard(board);
		if(saved == null)throw new CommonException(board.getBo_no()+"가 없음");
		dao.updateBoardHit(board);
		return saved;
	}

	@Override
	public int retrieveBoardCount(PagingInfoVO<Board2VO> pagingVO) {
		return dao.selectBoardCount(pagingVO);
	}

	@Override
	public List<Board2VO> retrieveBoardList(PagingInfoVO<Board2VO> pagingVO) {
				return dao.selectBoardList(pagingVO);
	}

	@Override
	public ServiceResult modifyBoard(Board2VO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeBoard(Board2VO board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Attatch2VO downloadAttatch(int att_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult incrememtLike(int bo_no) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
