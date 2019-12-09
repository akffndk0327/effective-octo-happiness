//package kr.or.ddit.note.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.inject.Inject;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import kr.or.ddit.note.service.INoteService;
//import kr.or.ddit.vo.NoteVO;
//
//@Controller
//public class NoteSendController2 {
//
//	@Inject
//	INoteService service;
//
//	@RequestMapping(value = "/note/sendingNote.do", method = RequestMethod.POST)
//	@ResponseBody
//	public List sendingNote(@RequestParam(required = false) String id, @RequestParam String text,
//			@RequestParam String selected, @RequestParam String login) {
//		List<String> all = service.selectAll();
//		List<String> member = service.selectMember();
//		List<String> company = service.selectCompany();
//		List<String> who =  new ArrayList<>();
//		List<NoteVO> notevo = new ArrayList<>();
//		int cnt = 0;
//		if (selected.equals("self")) {// 직접입력
//			String[] recieve = id.split(",");
//			System.out.println(recieve);
//			for (int i = 0; i < recieve.length; i++) {
//				NoteVO note = new NoteVO();
//				note.setSendContent(text);
//				note.setRecieveId(recieve[i]);
//				note.setSendId(login);
//				notevo.add(note);
//				cnt+=service.SendNote(note);
//				who.add(recieve[i]);
//			}
//		} else if (selected.equals("giup")) {// 기업회원 전체
//			for (int i = 0; i < company.size(); i++) {
//				NoteVO note = new NoteVO();
//				note.setSendContent(text);
//				note.setRecieveId(company.get(i));
//				note.setSendId(login);
//				notevo.add(note);
//				cnt+=service.SendNote(note);
//				who.add(company.get(i));
//			}
//		} else if (selected.equals("member")) {// 일반회원전체
//			for (int i = 0; i < member.size(); i++) {
//				NoteVO note = new NoteVO();
//				note.setSendContent(text);
//				note.setRecieveId(member.get(i));
//				note.setSendId(login);
//				notevo.add(note);
//				cnt+=service.SendNote(note);
//				who.add(member.get(i));
//			}
//		} else if (selected.equals("all")) {// 전체
//			for (int i = 0; i < all.size(); i++) {
//				NoteVO note = new NoteVO();
//				note.setSendContent(text);
//				note.setRecieveId(all.get(i));
//				note.setSendId(login);
//				notevo.add(note);
//				cnt+=service.SendNote(note);
//				who.add(all.get(i));
//			}
//		}
//
//		// int cnt=service.SendNote(notevo);
//		if (cnt > 0) {
//			who.add(0,"ok");
//			who.add(1,cnt+"");
//		}
//
//		return who;
//	}
//}
