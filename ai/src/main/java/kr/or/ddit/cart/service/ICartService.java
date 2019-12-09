package kr.or.ddit.cart.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.Order2VO;

/**
 * @author 최서희
 * @since 2019. 11. 7.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * 
 * --------     --------    ----------------------
 * 2019. 11. 7.      최서희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
public interface ICartService {
	public List<CartVO> retrieveCartList(String memId);
	public ServiceResult createCart(CartVO cart); 
	public ServiceResult modifyCart(CartVO cart); //장바구니 수량 조절
	public ServiceResult removeCart(Order2VO order);

}
