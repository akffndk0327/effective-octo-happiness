package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="orderId")
@ToString(exclude= {"prodIds","prodQty","delCartId"})
public class Order2VO implements Serializable{
	private Integer rnum;
	private String orderId; //주문아이디
	private String orderStatus;
	private String orderStatusName;
	private int orderPrice; //주문금액 : 물건 가격
	private int orderTotal;//총 주문금액: 물건 총가격
	private int payPrice; //결제금액 : 물건 총가격+배송비
	private int totalPrice; //총 결제금액 : sum(물건 총가격+배송비)
	private String orderDate; //주문날짜
	private String memId; //주문 회원Id
	private String prodId;
	
	private String plus; //주문리스트 제품명 => 제품명 외 ..개
	
	private String[] prodIds;
	private int[] prodQty;
	private String[] delCartId;
	
	private List<OrderDetailVO> orderDtList; //1:n
//	private List<ProdVO> prodList;
	
	private PaymentVO payVO; //1:1
	private ProdVO prod;
	private RefundVO refund;
	
}
