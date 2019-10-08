package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.Attatch2VO;
import kr.or.ddit.vo.Board2VO;
import kr.or.ddit.vo.PagingInfoVO;

public interface IBoardService {
	//crud
	//c,u,d 는 첨부파일 같이 관리해야해 
	//한 ㄱ쿼리에의해 비즈니스 완성되는ㄱ ㅓ아니고
	//2개으 ㅣ쿼리 잇어야 하나으 ㅣ비즈니스 완성됨.
	//트랜젝션 관리되야함
	
	public ServiceResult createBoard(Board2VO board); // insertBoard, insetAttaches
	public Board2VO retrieveBoard(Board2VO board); //common exception r 
	public int retrieveBoardCount(PagingInfoVO<Board2VO> pagingVO);
	public List<Board2VO>retrieveBoardList(PagingInfoVO<Board2VO> pagingVO);
	
	public ServiceResult modifyBoard(Board2VO board); // 글 잇나없나, 잇으면 다음단계(인증하기(비번실패)) -> 수정 하기/수정실패  4가지 경우 있으 
	
	public ServiceResult removeBoard(Board2VO board); //글잇나없나 인증, 삭제 성공,실패
	//다운로드
	public Attatch2VO downloadAttatch(int att_no); //해당파일 잇거나 없거나(없을땐?)
	//추천
	public ServiceResult incrememtLike(int bo_no);
	
}
