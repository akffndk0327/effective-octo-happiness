package kr.or.ddit.advice;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 허민지
 * @since 2019. 11. 3.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 3.      허민지       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Aspect
public class LoggingAdvice {
	private static Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	//중간 depth가 몇개가 됐든 service에 가겠다.
	@Around("execution(* kr.or.ddit..service.*.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		String clzName = joinPoint.getTarget().getClass().getSimpleName();
		String mtdName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		logger.info("{}.{} 호출, 전달 파라미터 : {}", clzName, mtdName, Arrays.toString(args));
		long start = System.currentTimeMillis();
		Object retValue =  joinPoint.proceed(args);
		long end = System.currentTimeMillis();
		logger.info("{}.{} , 소요시간 : ({}ms 반환값 : {})", clzName, mtdName, (end-start), retValue);
		//확인작업중
		return retValue;
	}
}
