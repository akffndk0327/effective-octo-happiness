package kr.or.ddit.buyer.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;

/**
 * Servlet implementation class DetailBuyer
 */
@Controller
public class DetailBuyer{
   private static final long serialVersionUID = 1L;
   @Inject
   IBuyerService service ;
   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   @RequestMapping(value="/buyer/DetailBuyer",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
   public String BuyerViwe(HttpSession session, @RequestParam(name="buyer_id")String buyer_id
		   	) throws ServletException, IOException {
	  
//      HttpSession session = request.getSession();
//      request.setCharacterEncoding("UTF-8");
//      String accept = request.getHeader("Accept");
//      String buyer_id = request.getParameter("buyer_id");
      
      BuyerVO bv = service.buyerDetail(buyer_id);
      
      String viewName = null;
//      if (
//    		accept.toLowerCase().contains("json")) {
//         response.setContentType("application/json;charset=UTF-8");
//         String json = new MarshallingUtils().marshalling(bv);
//          try(
//                PrintWriter out = response.getWriter();    
//                         
//              ){
//            out.print(json);
//         }
//      
//      }else {
         viewName = "buyer";
//      }
      
      return viewName;
   }
}