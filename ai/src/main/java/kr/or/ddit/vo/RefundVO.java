package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="orderId")
public class RefundVO implements Serializable{
	private String orderId;
	private String refundDate;
	private String refundReason;
	private String refundApproval;
}
