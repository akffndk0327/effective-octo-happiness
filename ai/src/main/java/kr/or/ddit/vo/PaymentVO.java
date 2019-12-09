package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="orderId")
public class PaymentVO implements Serializable{
	private String orderId;
	private int payPrice;
	private String payDate;
	private String payStatus;
	private String payStatusName;
	private int adId;
}
