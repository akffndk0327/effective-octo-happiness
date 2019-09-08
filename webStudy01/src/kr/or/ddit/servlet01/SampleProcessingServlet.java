package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * "/sampleProcess"로 매핑된 
 * kr.or.ddit.se~01.Sample~vlet작성
 * 전송되는 모든 파라미털ㄹ
 * name : value 형식으로 시스템 콘솔에출력 
 *
 */
@WebServlet("/sampleProcess") //절대경로이지만 많이 생략되잇어서 찾을수 있어 여기서 현재 위치는? server 
public class SampleProcessingServlet extends HttpServlet {
	//뭐부터해야할까?
	//클라이언트가 입력한 데이터를 서버에 보내야되. POST
	//Form Data가 나와야해 -> 이게 있나 확인 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//바디에 얼마나 잇나 확인?
		//파라미터 데이터 방법1 => 일일이 파라미터 가져와야해서 불편해 방법2로 하자 ! 
		req.setCharacterEncoding("UTF-8"); //UI먼저 확인 ->jsp가지 . 서버가 지원하는지 확인 ! 
		
		String numParam = req.getParameter("numParam"); //파라미터 가져오기. numParam : jsp의 name과 같아야함  
		if(numParam != null && numParam.matches("\\d+")) {
			System.out.println(Integer.parseInt(numParam) *300);}
		String textParam = req.getParameter("textParam");
		String radioParam = req.getParameter("radi"+ ""+ "Param");
		
		String[] checkParam = req.getParameterValues("checkParam"); //이렇게 하면 선택 1개만 옴 3개 선택시 모두 못와 getParameter -> getParameterValues로 ! 
		String selectParame1 = req.getParameter("selectParam1");
		
		String[] selectParame2 = req.getParameterValues("selectParam2");
	
		//파싱모아둔거 방법2
		Map<String, String[]> pMap = req.getParameterMap(); //이게없으면 위에거 모두 존재 x 
		Set<String> nameSet = pMap.keySet(); //다이렉트 접근 불가+ 진짜 컬렉션. keyset으로 key값 뽑아오기 + 2)entryset()
		Iterator<String> it = nameSet.iterator(); //인터페이스만 갖고잇는 객체 = 컬렉션 뷰 
		//Iterator는 자바의 컬렉션 프레임웍에서 컬렉션에 저장되어 있는 요소들을 읽어오는 방법을 표준화 하였는데 그 중 하나가 Iterator이다.
		//자동으로 Index 를 관리해주기 때문에,사용에 편리함이 있을수 있으나
		//Iterator 를 열어보면 객체를 만들어 사용하기 때문에 느릴수 밖에 없다.

		while (it.hasNext()) { //hasNext() : 읽어 올 요소가 남아있는지 확인
			String pName = (String) it.next();//파라미터명
			String[] values = pMap.get(pName); // pMap.get(pName); => getgetParameterValues
			System.out.printf("%s : %s\n", pName, Arrays.toString(values)); //배열로 값 볼 수 있음 .
		}
		InputStream is = req.getInputStream();
		System.out.println(is.available());
	}
	

}
