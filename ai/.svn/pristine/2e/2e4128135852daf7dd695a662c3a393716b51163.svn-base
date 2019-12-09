package kr.or.ddit.correctboard.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.CorrectAttatchVO;
import kr.or.ddit.vo.CorrectBoardVO;

/**
 * @author 이진희
 * @since 2019. 11. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 11.      이진희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface ICorrectAttatchDAO {
	
	//첨부파일 생성
	public int createCorrectAttatch(CorrectBoardVO vo);
	
	//하나의 첨부파일
	public CorrectAttatchVO selectAttatch(int correntNo);
	
	//첨부파일 삭제 
	public int deleteAttatch(CorrectBoardVO vo);
	

}
