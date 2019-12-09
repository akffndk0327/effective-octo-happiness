package kr.or.ddit.faq.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
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
 * 2019. 11. 21.    최서희      		 최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
public interface IFaqService {
	public int retrieveFaqCount();
	public List<FaqVO> retrieveFaqList(PagingInfoVO<FaqVO> pagingVO);
	public FaqVO retrieveFaq(int faqNo);
	public ServiceResult createFaq(FaqVO faq); 
	public ServiceResult modifyFaq(FaqVO faq);
	public ServiceResult modifyFaqHit(int faqNo);
	public ServiceResult removeFaq(int faqNo);

}
