package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author 박슬기
 * @since 2019. 11. 21.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * 
 * --------     --------    ----------------------
 * 2019. 11. 21.      박슬기       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Data
@NoArgsConstructor
public class BrowserVO implements Serializable{
	private String broType;
	private String broDate;
	private String osType;
	
}
