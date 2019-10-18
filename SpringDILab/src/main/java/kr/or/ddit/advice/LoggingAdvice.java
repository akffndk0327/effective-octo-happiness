package kr.or.ddit.advice;

import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Spring에서는 "메소드 호출 join point만 지원" 함. Weabing(타겟+어드바이스) 시점에 따른 종류 1. before
 * advice 2. after advice 1) after-returning advice 2) after-throwing advice 3.
 * around advice 전후 감싸서
 * 
 *
 */

// 5. 온테이션으로 바꿔보자 aop-context 다 주석으로 묶기
@Aspect //어드바이스 pointcut들어잇음.  근데 빈 등록안되있어 - > inclde  -> autiDi에서 aspect의 네임 넣어줘
public class LoggingAdvice {
	static Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
	
	@Pointcut("execution(* kr.or.ddit.idol.service.*.*(..))") //포인트컷 등록됨... ! 
	public void dummy() {}
	
	@Before("dummy()") //위에 ex가 갖고있는 포인트컷을 쓰겟다 라는 의미
	public void before(JoinPoint joinPoint) { // 어플리케이션 밖에잇는 weaver가 호출함. 리턴 상황이 존재할수없엇 void
		Object target = joinPoint.getTarget(); // Object 누가 타겟인지 몰라서
		String clzName = target.getClass().getSimpleName(); // 클래스정보꺼태
		Signature signature = joinPoint.getSignature(); // 타겟정보 갖고있음
		String methodName = signature.getName(); // 타겟이름
		Object[] args = joinPoint.getArgs(); // 파라메터잇을수 없을수도 얼마나 잇는지 몰라서

		logger.info("{}.{} 호출,파라미터 {}, 호출시점{}", clzName, methodName, Arrays.toString(args), new Date()); // 클래스정보, 메소드,
																											// 정보
	}
	
	
	@After("dummy()")
	public void after(JoinPoint joinPoint) {
		Object target = joinPoint.getTarget();
		String clzName = target.getClass().getSimpleName();
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		Object[] args = joinPoint.getArgs();

		logger.info("{}.{} 호출종료", clzName, methodName);

	}
	
	//반환값 확인 ! 
	@AfterReturning(pointcut="dummy()",returning="retValue")
	public void after_returning(JoinPoint joinPoint,Object retValue) { 
		Object target = joinPoint.getTarget();
		String clzName = target.getClass().getSimpleName();
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		Object[] args = joinPoint.getArgs();

		logger.info("{}.{} 호출종료, 결과값:{}", clzName, methodName,retValue);

	}
	//타겟내에서 예외발생? 정상종료 가 차이점 => 어떤예외 발생햇나 받아야함
	@AfterThrowing(pointcut="dummy()", throwing="e")
	public void after_throwing(JoinPoint joinPoint,Throwable e) { 
		Object target = joinPoint.getTarget();
		String clzName = target.getClass().getSimpleName();
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		Object[] args = joinPoint.getArgs();
		
		logger.error("{}.{} 호출종료, 발생예외:{}", clzName, methodName, e);
		
	}
	@Around("dummy()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable { //Object 클라이언트한테 돌려줘야해
		//befor
		Object target = joinPoint.getTarget();
		String clzName = target.getClass().getSimpleName();
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		
		Object[] args = joinPoint.getArgs();
		//before
		logger.info("{}.{} 호출,파라미터 {}, 호출시점{}", clzName, methodName, Arrays.toString(args), new Date());
		
		Object retValue = null;
		try {
			long startTime = System.currentTimeMillis();
			retValue = joinPoint.proceed(args);// 타겟호출함. 뭘 갖고잇는지 모르닌까 object & retValue =
			long endTime = System.currentTimeMillis();
			//after_returning
			logger.info("{}.{} 호출종료({}ms), 결과값:{}", clzName, methodName,(endTime - startTime),retValue);
		} catch (Throwable e) {
			e.printStackTrace();
			//after_throwing
			logger.error("{}.{} 호출종료, 발생예외:{}", clzName, methodName, e);
			throw e; //예외 알리고 싶으면 이렇ㄱ ㅔ 하고 throws로 던져 
			
		} //예외 발생하면 여기서 끝나... 클라이언트는 정보 모르게 하고싶으면 이구조로~~~~ 
		
		//after
		logger.info("{}.{} 호출종료", clzName, methodName);
		
		return retValue;
		
		
	}
	
	
}














































