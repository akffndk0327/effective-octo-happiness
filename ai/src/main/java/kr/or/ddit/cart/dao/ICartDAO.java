package kr.or.ddit.cart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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
 * --------     --------    ----------------------
 * 2019. 11. 7.      최서희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface ICartDAO {
	public List<CartVO> selectCartList(String memId);
	public int insertCart(CartVO cart);
	public int updateCart(CartVO cart); //장바구니 수량 조절
	public int deleteCart(Order2VO order);
}
