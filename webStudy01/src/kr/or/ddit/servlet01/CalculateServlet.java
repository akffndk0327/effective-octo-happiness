package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculateServlet
 */
@WebServlet("/calculator")
public class CalculateServlet extends HttpServlet {
	public static enum OperatorType{
		PLUS('+', new Operator() {
			@Override
			public int operate(int left, int right) {
				return left + right;
			}
		}), MINUS('-', (left, right) -> {
			return left - right;
		}), MULTIPLY('*', (left, right) -> {
			return left * right;
		}), DIVIDE('/', (left, right) -> {
			return left / right;
		});
		//기호까지 결정되야함 
		OperatorType(char sign, Operator operator){
			this.sign = sign;
			this.operator = operator;
		}
		private char sign;
		private Operator operator; //오퍼레이터 타입이 쓸수있는 전략???
		//미리 전략이 정해져잇어야해 
		//전략 패턴?
		public char getSign() {
			return sign;
		}
		//만약에.....이런 경우라면...?
		public int operate(int left, int right) {
			//연산
			int result = operator.operate(left,right); // 왜 -1???
			return result;
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String leftParam = request.getParameter("leftOp");
		String rightParam = request.getParameter("rightOp");
		String opParam = request.getParameter("operator");
		int status = 200;
		if (leftParam == null || !leftParam.matches("\\d+") || rightParam == null 
				|| rightParam.matches("\\d+")) {
			// status는 400번대로 바껴야해
			status = 400;
		}
		// 연산자 검증 ! ->아무것도 없는데 선택하면 문제된다.
//		OperatorType op = OperatorType.valueOf(""); // 인자오류값 날수있어
		String plain = null;
		try {
			OperatorType op = OperatorType.valueOf(opParam);
			int left = Integer.parseInt(leftParam);
			int right = Integer.parseInt(rightParam);
			int result = op.operate(left,right);
			plain = String.format("%d %s %d = %d", left, op.getSign(),right,result);
		}catch (Exception e) {
			status =400;
		}
		if(status != 200) { //문제가 있음 
			response.sendError(status);
		}else { 
			response.setContentType("text/plain");
			try(
			PrintWriter out = response.getWriter();
			){
			out.println(plain); 
			}
//			out.close(); //버퍼 방출 
		}//여기서 스타트는 get방식으로 실행함 => 405에러남 .서버는 post인데 클라이언트가 get으로 넘긴거
	}

}
