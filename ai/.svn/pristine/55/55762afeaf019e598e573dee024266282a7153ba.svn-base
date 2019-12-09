package kr.or.ddit.mypage.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.mypage.service.IMemMypageService;
import kr.or.ddit.vo.MemAllergyVO;

@Controller
@RequestMapping("/memMypage")
public class MemberAllergyDeleteController {

   @Inject
   IMemMypageService service;
   
   @RequestMapping(value="memAllergyDelete.do", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
   @ResponseBody
   public int deleteAllergy(
		 @RequestParam (required=true) String memId,
		 @RequestParam (required=true) String allId,
		 RedirectAttributes redirectAttributes,
         Authentication authentication) {
	   
      MemAllergyVO member = new MemAllergyVO();
      member.setMemId(memId);
      member.setAllId(allId);
      int result = service.removeMemAllergy(member);
      
      return result;
   }
}