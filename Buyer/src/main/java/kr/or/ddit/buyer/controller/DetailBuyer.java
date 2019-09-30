package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.mvc.annotation.CommandHandler;
import kr.or.ddit.mvc.annotation.HttpMethod;
import kr.or.ddit.mvc.annotation.URIMapping;
import kr.or.ddit.utils.MarshallingUtils;

/**
 * Servlet implementation class DetailBuyer
 */
@CommandHandler
public class DetailBuyer{
   private static final long serialVersionUID = 1L;
   
   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   @URIMapping(value="/buyer/DetailBuyer", method=HttpMethod.POST)
   public String BuyerViwe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      HttpSession session = request.getSession();
      request.setCharacterEncoding("UTF-8");
      String accept = request.getHeader("Accept");
      IBuyerService service = BuyerServiceImpl.getInstance();
      String buyer_id = request.getParameter("buyer_id");
      
      BuyerVO bv = service.buyerDetail(buyer_id);
      
      String viewName = null;
      if (accept.toLowerCase().contains("json")) {
         response.setContentType("application/json;charset=UTF-8");
         String json = new MarshallingUtils().marshalling(bv);
          try(
                PrintWriter out = response.getWriter();    
                         
              ){
            out.print(json);
         }
      
      }else {
         viewName = "buyer";
      }
      
      return viewName;
   }
}