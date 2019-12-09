package kr.or.ddit.refund.service;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.RefundVO;

/**
 * @author 최서희
 * @since 2019. 11. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                        수정자              수정내용
 * -------------     --------    ----------------------
 * 2019. 11. 19.       최서희      		 최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
public interface IRefundService {
	public ServiceResult createOrderRefund(RefundVO refund); //환불신청
	public ServiceResult updateOrderRefundYes(String orderId); //환불승인
	public ServiceResult updateOrderRefundNo(String orderId); //환불거절
	
}
