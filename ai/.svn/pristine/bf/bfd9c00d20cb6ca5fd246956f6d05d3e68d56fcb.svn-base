package kr.or.ddit.faq.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.FaqVO;
import kr.or.ddit.vo.PagingInfoVO;

/**
 * @author 최서희
 * @since 2019. 11. 21.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * -------------   --------    ----------------------
 * 2019. 11. 21.    최서희     	 	 최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IFaqDAO {
	public int selectFaqCount();
	public List<FaqVO> selectFaqList(PagingInfoVO<FaqVO> pagingVO);
	public FaqVO selectFaq(int faqNo);
	public int insertFaq(FaqVO faq); 
	public int updateFaqHit(int faqNo); //조회수 증가
	public int updateFaq(FaqVO faq);
	public int deleteFaq(int faqNo);

}
