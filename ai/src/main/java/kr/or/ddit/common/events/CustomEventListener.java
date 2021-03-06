package kr.or.ddit.common.events;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author 허민지
 * @since 2019. 11. 3.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2019. 11. 3.      허민지       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Component
public class CustomEventListener {
   @Inject
   WebApplicationContext container;
//   @Resource(name="userList")
//   Set<SendVO> userList;
//   @Resource(name="webSocketSessionList")
//   List<WebSocketSession> wsList;
   

   @EventListener(classes=ContextRefreshedEvent.class)
   public void handleEvent(ContextRefreshedEvent event) {
      System.err.println("이벤트 처리");
      ServletContext application = container.getServletContext();
      application.setAttribute("cPath", application.getContextPath());
   }
   
//   @EventListener(classes=AuthenticateSuccessEvent.class)
//   public void handerSuccessEvent(AuthenticateSuccessEvent event) throws IOException{
//      MemberVO authMember = event.getAuthMember();
//      SendVO send = new SendVO(authMember.getMem_id(), authMember.getMem_name());
//      userList.add(send);
//      broadcast();
//   }
   
//   private void broadcast() throws IOException {
//      ObjectMapper mapper = new ObjectMapper();
//      String json = mapper.writeValueAsString(userList);
//      for(WebSocketSession session : wsList) {
//         session.sendMessage(new TextMessage(json));
//      }
//      System.out.println("broadcast");
//   }

//   @EventListener(classes=LogoutSuccessEvent.class)
//   public void logoutEvent(LogoutSuccessEvent event) throws IOException {
//      MemberVO authMember = event.getAuthMember();
//      if(authMember==null) return;
//      SendVO send = 
//            new SendVO(authMember.getMem_id(), authMember.getMem_name());
//      userList.remove(send);
//      broadcast();      
//   }
}