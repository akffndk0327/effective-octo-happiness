package kr.or.ddit.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String name = req.getParameter("name");
		String leftParam = req.getParameter("leftOp"); // 왼쪽 수 가져오기
		String op = req.getParameter("operator"); // 연산자 가져옥기
		String rightParam = req.getParameter("rightOp"); // 오른 쪽 수

		// 검증하기 => 제한된 타입만 쓰게끔 클라이언트 사이em 검증 작업
		long leftOp = 0L;
		long rightOp = 0L;
		if (leftParam != null && leftParam.matches("\\d+") && rightParam != null
				&& rightParam.matches("\\d+")) {

			// 파싱하기
			leftOp = Integer.parseInt(leftParam);
			rightOp = Integer.parseInt(rightParam);
		}
		
		long result = 0; // 결과값
		if (op.equals("PLUS")) {
			result = leftOp + rightOp;
			op = "+";
		} else if (op.equals("MINUS")) {
			result = leftOp - rightOp;
			op = "-";
		} else if (op.equals("MULTIPLY")) {
			result = leftOp * rightOp;
			op = "*";
		} else if (op.equals("DIVIDE")) {
			result = leftOp / rightOp;
			op = "/";
		}

		resp.setContentType("text/plain;charset = UTF-8");
		StringBuffer template = readTemplate("calc.tmpl");
		int idx = template.indexOf("@result");
	
		String clac = "클라이언트의 이름은 : "+ name +"\n"+ leftOp+ op+rightOp + "=" + result;
		
		StringBuffer html = template.replace(idx,idx+"@result".length(), clac.toString());

		try (
				// closable 객체의 선언.
				PrintWriter out = resp.getWriter(); // 예외 발생 될때 자동적으로 생성됨
		) {
			out.println(html);
		}
	}

	
	
	private StringBuffer readTemplate(String name) throws IOException {
		StringBuffer template = new StringBuffer();
		try (InputStream is = CalculatorServlet.class.getResourceAsStream(name);
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8")); // 2차 스트림 여기에 꽂아서 써야해
																								// ..? 뭘 ?
		) {
			String tmp = null;
			while ((tmp = reader.readLine()) != null) { // 맨 마지막 줄 만날때까지 읽어야해
				template.append(tmp + "\n");
			}
		}
		return template;
	}

}
