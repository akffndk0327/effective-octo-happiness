 package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @author 박슬기
 * @since 2019. 11. 8.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * 
 * --------     --------    ----------------------
 * 2019. 11. 8.      박슬기       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Data
@NoArgsConstructor
public class OnedayVO implements Serializable{
	private String onedayId;
	private String menuId;
	private String menuName;
	private String monPass;
	private String monthlyTitle;
	private String monthlyId;
	private String afterMenu;
	
}
