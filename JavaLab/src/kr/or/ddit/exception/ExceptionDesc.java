package kr.or.ddit.exception;

import java.io.IOError;
import java.io.IOException;

/**
 *	예외란? (Throwable)
 * : 어플리케이션 내에서 발생하는 비정상 상황의 포괄개념 
 * 1. Error : 처리하지 않는/ 할수 없는 비정상 상황 
 * 2. Exception : 처리할 수 있는 상황 
 *   1)Exception (checked exception)
 *   	 : 발생 가능한 상황에서는 반드시 처리르 해야하는 예외
 *   	IOException, SQLException
 *   2) RuntimeException (Unchecked exception)
 *   	: 처리하지 않더라고 VM에게 제어권이 전달됨.
 *   	NullPointerException, IllegalArgumentException(10!에서 사요함.....)
 * 전달 = throw
 * ** 예외 발생 방법 
 *  throw new 예외타입의생성자 ();
 *** 예외 처리 방법 
 * 1. 적극적 : try~catch~finally 
 *
 * 2. 소극적 : throws
 */
public class ExceptionDesc {
	public static void main(String[] args) throws IOException { // main은 vm이 제어하닌까
//		String message = test();
//		System.out.println(message.toString());
		try {
//			login("b001", "java");
			boolean result = login("a001", "sfsa");
			if(result) {
				System.out.println("로그인성공 ");
			}else {
				System.out.println("아이디나 비번틀리");
			}
		} catch (UserNotFoundException e) {
			System.err.println(e.getMessage()); // 에러 콘솔 발생 id 없음 
		}catch(NotAuthenticatedException e) {
			System.err.println(e.getMessage());
		}
	}

	public static String test() {
		String result = "결과";
		try {
			if (1 == 1) {
				throw new IOException("강제발생 예외,cheked형태");
			}
			return result;
		} catch (IOException e) {
//			throw new RuntimeException(e); //갑자기...?아무것도 안해도 vm으로 넘거가 예외종류가 바ㅜ낌
			return "짜가";
		}
	}

	public static final String  ID = "a001";
	public static final String  password = "java";
	
	public static boolean login(String id, String pass){
		if(!ID.equals(id)) {//존재x
			throw new UserNotFoundException(id +"에 해당하는 사람 x "); //호출자가에게 어떤 상황발생햇는지 알려줘
//			return false;
		}
		if(!password.equals(pass)) {
			throw new NotAuthenticatedException("비번오류"); ///호출자에게 구체적인 문제 알려줘 왜 발생햇나 			
//			return false;
		}
		return true;
	}

}












