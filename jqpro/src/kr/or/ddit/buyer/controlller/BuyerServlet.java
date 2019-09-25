package kr.or.ddit.buyer.controlller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;

/**
 * Servlet implementation class BuyerServlet
 */
@WebServlet("/BuyerServlet")
public class BuyerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BuyerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//전체 리스트가져오기
		
		//1.service 객체 얻기 
		IBuyerService service = BuyerServiceImpl.getInstance();
		
		//2.메서드호출 - 리턴받기 
		List<BuyerVO> list = service.selectNameList();
		
		//3.리턴결과값 저장하기 -request
		request.setAttribute("list",list);
		
		//4. jsp로 forward
		RequestDispatcher disp = request.getRequestDispatcher("/0911/nameList.jsp");
		disp.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//buyer_id를 파라미터로 전달받아서 해당 id에 상세보기를 수행한다.
		//파라미터 받기
		String id = request.getParameter("id"); //"id"는 ajax에서의 name과 같아야함 .
		//1.service객체 얻기
		IBuyerService service = BuyerServiceImpl.getInstance();
		//2. 메서드 호출 - 리턴받기 
		BuyerVO vo = service.buyerDetail(id);
		//3.리턴결과값 저장 하기 
		request.setAttribute("vo", vo); //"name", value, "vo"jsp가서 getAttr~
		//4. jsp로 forward
		RequestDispatcher disp = request.getRequestDispatcher("/0911/detail.jsp");
		disp.forward(request, response);
		
		
	}

}
