package kr.or.ddit.refund.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.RefundVO;

/**
 * @author 최서희
 * @since 2019. 11. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                      수정자               수정내용
 * -------------     --------    ----------------------
 * 2019. 11. 19.      최서희       		최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface IRefundDAO {
	public int insertOrderRefund(RefundVO refund); //환불신청
	
	//환불 승인
	public int updateRefundPaystatus(String orderId);	
	public int updateRefundOrderstatus(String orderId);
	public int updateRefundApprovalYes(String orderId);
	
	//환불 거절
	public int updateRefundApprovalNo(String orderId);
	
}
