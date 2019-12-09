package kr.or.ddit.member.exception;

/**
 * @author 허민지
 * @since 2019. 11. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 5.      허민지       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
public class NotAuthenticatedException extends RuntimeException{

	public NotAuthenticatedException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotAuthenticatedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NotAuthenticatedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotAuthenticatedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotAuthenticatedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
