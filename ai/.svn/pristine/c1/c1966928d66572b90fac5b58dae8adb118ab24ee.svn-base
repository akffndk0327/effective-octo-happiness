package kr.or.ddit.correctboard.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CodeVO;
import kr.or.ddit.vo.CorrectBoardVO;
import kr.or.ddit.vo.PagingInfoVO;

/**
 * @author 이진희
 * @since 2019. 11. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자               수정내용
 * 2019. 11. 13.      이진희       답글삭제하는 메서드 추가
 * --------     --------    ----------------------
 * 2019. 11. 11.      이진희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface ICorrectBoardDAO {
	
	//게시글등록
	public int createCorrectBoard(CorrectBoardVO vo);
	
	//게시글 상세보기 
	public CorrectBoardVO selectCorrect(CorrectBoardVO vo);
	
	//게시글전체리스트수 
	public int selectCorrectCount(PagingInfoVO<CorrectBoardVO> pagingVO);
	
	//게시글 전체리스트 출력
	public List<CorrectBoardVO> selectCorrectList(PagingInfoVO<CorrectBoardVO> pagingVO);
	
	//게시글원글 삭제
	public int deleteCorrect(CorrectBoardVO vo);
	
	//게시글답글삭제
	public int deleteCorrectRe(CorrectBoardVO vo);
	
	//게시판종류리스트
	public List<CodeVO> selectCodeList();

	//게시판소분류리스트 
	public List<CodeVO> selectBoardList();
	
}
