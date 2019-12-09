package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="cartId")
public class CartVO implements Serializable{
	private String cartId;
	private String prodId;
	private String memId;
	private int cartQty;
	private String cartIndate;
	private String prodDelivery; 
	private int orderPrice; //prodId*cartQty
	private int orderTotal; //SUM(prodId*cartQty)
	private int payPrice; //(prodId*cartQty)+prodDelivery 
	private int totalPrice; //SUM((PROD_PRICE*CART_QTY)+PROD_DELIVERY)
	
	private ProdVO prod;
	private CompanyVO company;
}
