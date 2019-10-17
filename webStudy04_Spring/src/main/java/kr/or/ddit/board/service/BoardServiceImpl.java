package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.IAttatch2DAO;
import kr.or.ddit.board.dao.IBoard2DAO;
import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.exception.CommonException;
import kr.or.ddit.security.EncryptDesc;
import kr.or.ddit.vo.Attatch2VO;
import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.PagingInfoVO;
@Service
public class BoardServiceImpl implements IBoardService {
	@Inject
	IBoard2DAO dao;
	@Inject
	IAttatch2DAO attatchDAO;
	
//	@Inject
//	SqlSessionTemplate sqlSession;
	
	File saveFolder = new File("d:/saveFiles");
	
	private int processAttatch(Board2VO board) { // 수정에서도 쓸수잇어
		// 첨부ㅏ일처리...
		List<Attatch2VO> attatchList = board.getAttatchList();
		int cnt = 0;
		if (attatchList != null) {
			attatchDAO.insertAttatches(board); // 세션안넣어서 잘못된세션을 넘길려함.... 주의 !!
			for (Attatch2VO attatch : attatchList) {
				// 미들에 바이너리, db에
				// if(1==1)throw new RuntimeException("강제 발생 예외");
				try {
					attatch.saveFile(saveFolder);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return cnt;
	}
	
	private int deleteAttatch(Board2VO board) {
		Integer[] delAtts = board.getDelAttatches();
		int cnt = 0;
		if (delAtts != null && delAtts.length > 0) {
			for (int delNo : delAtts) { // db에는 메타데이터 저장 미들티에어는 바이너리 있어
				Attatch2VO attatch = attatchDAO.selectAttatch(delNo); // attatch으 ㅣ기본 정보 가져옴
				FileUtils.deleteQuietly( // 2진데이터 지우고 잇음
						new File(saveFolder, attatch.getAtt_savename()));
			}
			cnt += attatchDAO.deleteAttatches(board); // db랑 미들티어 완전히 지워야해
		}
		return cnt;
	}
	
	@Override
	public ServiceResult createBoard(Board2VO board) {
		// 원자성관리 - 트랜젝션관ㄹ ㅣ-세션열고 -커밋까지
		// 다오에 세션 넘긱 ㅣ
		int cnt = dao.insertBoard(board);
		cnt += processAttatch(board);
		ServiceResult result = null;
		//암호화
		String bo_pass = null;
		try {
			bo_pass = EncryptDesc.encryptSha512(board.getBo_pass());
			board.setBo_pass(bo_pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (cnt > 0)
			result = ServiceResult.OK;
		else
			result = ServiceResult.FAILED;
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
		Board2VO savedBoard = retrieveBoard(board); // 1.존재여부 확인
		ServiceResult result = null;
		String bo_pass = null;
		try {
			bo_pass= EncryptDesc.encryptSha512(board.getBo_pass());
			if (savedBoard.getBo_pass().equals(bo_pass)) { // 2. 패스워드 비교
				int cnt = dao.updateBoard(board);
				cnt += processAttatch(board); // 수정할 때 첨부파일 등록 학 ㅣ
				cnt += deleteAttatch(board); // 첨부파일 삭제. session꼭 넘긱 ㅣ
				if (cnt > 0) {
					result = ServiceResult.OK; // 수정완료
				} else {
					result = ServiceResult.FAILED;
				}
				
			} else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	@Override //AOP 적용하면 트랜젝션 은 빠져 - 관점 지향 
	public ServiceResult removeBoard(Board2VO board) {
		//존재여부 확인.
		//2. 패스워드 비교
		//3. 첨부파일 삭제 
		Board2VO savedBoard = retrieveBoard(board);//1.존재여부 확인
		ServiceResult result = null;
		String bo_pass;
		try {
			bo_pass = EncryptDesc.encryptSha512(board.getBo_pass());
			if(savedBoard.getBo_pass().equals(bo_pass)){ //2. 패스워드 비교
				List<Attatch2VO> attatchList = savedBoard.getAttatchList();
				int cnt = 0;
				//1.메타데이터 지우고 --?첨부파일
				//2.게시글 지우고
				//3. 바이너리데이터(2진데이터) 지우기 (메타데이터 받아놔서 바이너리데이터 접근 가능함.)
				if(attatchList!=null && attatchList.size()>0) { //메타데이터 지움 
					
					cnt += attatchDAO.deleteAttatches(board); //xml에서 삭제할려햇는데 이미 삭제되있어서 아래에서 null뜸
				}
				// board delete
				cnt += dao.deleteBoard(board); //2진데이터 삭제 
				if(cnt>0) {
					if(attatchList!=null) { 
						// binary delete
						for(Attatch2VO attatch : attatchList) {
							FileUtils.deleteQuietly( //첨부파일 갯수만큼 for문 돌아
									new File(saveFolder, 
											attatch.getAtt_savename()));
						}
					}
					result = ServiceResult.OK;
				}else {
					result = ServiceResult.FAILED;
				}
			}else {
				result = ServiceResult.INVALIDPASSWORD;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Attatch2VO downloadAttatch(int att_no) {
		Attatch2VO attatch = attatchDAO.selectAttatch(att_no);
		if(attatch==null) throw new CommonException(att_no+"파일 없음.");
		attatchDAO.updateDowncount(att_no);
		return attatch;
	}

	@Override
	public ServiceResult incrememtLike(int bo_no) {
		int cnt = dao.updateBoLike(new Board2VO(bo_no));
		ServiceResult result = null;
		if(cnt>0) result = ServiceResult.OK;
		else result = ServiceResult.FAILED;
		return result;
			
		
	}
}