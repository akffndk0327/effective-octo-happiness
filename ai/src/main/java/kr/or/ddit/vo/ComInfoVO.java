package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * @author 허민지
 * @since 2019. 11. 12.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 12.      허민지       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */

@Data
public class ComInfoVO implements Serializable{
	
	private String comNum;
	private String comName;
	private String comAddr;
	

}
