package kr.or.ddit.order.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.Order2VO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.RefundVO;

/**
 * @author 최서희
 * @since 2019. 11. 7.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 7.      최서희       최초작성
 * 2019. 11. 10.     최서희       주문결제 기능 추가
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
public interface IOrderService {
	public String retrieveOrderId(String memId);
	public List<String> retrieveOrderIdList(Order2VO order); 
	
	public int retrieveOrderCount(String pk);
	public List<Order2VO> retrieveOrderList(PagingInfoVO<Order2VO> pagingVO);
	public int retrieveOrderEmpCount(PagingInfoVO<Order2VO> pagingVO);
	public List<Order2VO> retrieveOrderEmpList(PagingInfoVO<Order2VO> pagingVO);
	public Order2VO retrieveOrder(String orderId); //주문 상세조회
	public ServiceResult createOrder(Order2VO order); //주문
	public ServiceResult modifyOrder(String orderId); //배송완료
	public ServiceResult removeOrder(String orderId);//주문취소
}
