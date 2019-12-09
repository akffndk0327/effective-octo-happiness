package kr.or.ddit.refund.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.refund.dao.IRefundDAO;
import kr.or.ddit.vo.RefundVO;

@Service
public class RefundServiceImpl implements IRefundService{
	@Inject IRefundDAO dao;
	
	@Override
	public ServiceResult createOrderRefund(RefundVO refund) {
		ServiceResult result = null;
		int cnt = dao.insertOrderRefund(refund);
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult updateOrderRefundYes(String orderId) {
		ServiceResult result = null;
		int cnt = dao.updateRefundApprovalYes(orderId);
		cnt+= dao.updateRefundOrderstatus(orderId);
		cnt+= dao.updateRefundPaystatus(orderId);
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult updateOrderRefundNo(String orderId) {
		ServiceResult result = null;
		int cnt = dao.updateRefundApprovalNo(orderId);
		if(cnt>0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

}
