package kr.or.ddit.note.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.note.dao.INoteDAO;
import kr.or.ddit.note.service.INoteService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.NoteVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.StopSellingFoodVO;

@Controller
public class NoteRetrieveController {
	@Inject
	INoteService service;

	@RequestMapping("note/noteRecieve.do")
	public String noteRecieve(Authentication auth, Model model,
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage) {
		MemberVO member = (MemberVO) auth.getPrincipal();
		String name = member.getMemName();

		PagingInfoVO<NoteVO> pagingVO = new PagingInfoVO<>(5, 5);
		pagingVO.setRecieveId(auth.getName());
		int totalRecord = service.selectRecieveNoteCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<NoteVO> recieveList = service.RecieveNotesList(pagingVO);
		pagingVO.setDataList(recieveList);

		model.addAttribute("recieveList", pagingVO);
		model.addAttribute("re", "re");
		model.addAttribute("name", name);
		return "note/noteMain";
	}

	@RequestMapping(value = "note/noteSend.do")
	public String noteSend(Authentication auth, Model model,
			@RequestParam(name = "page", required = false, defaultValue = "1") int currentPage) {
		MemberVO member = (MemberVO) auth.getPrincipal();
		String name = member.getMemName();

		PagingInfoVO<NoteVO> pagingVO = new PagingInfoVO<>(5, 5);
		pagingVO.setSendId(auth.getName());
		int totalRecord = service.selectSendNoteCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		List<NoteVO> sendList = service.SendNotesList(pagingVO);
		pagingVO.setDataList(sendList);

		model.addAttribute("sendList", pagingVO);
		model.addAttribute("se", "se");
		model.addAttribute("name", name);
		return "note/noteMain";
	}

	@RequestMapping("note/noteView.do")
	public String noteView(Authentication auth, Model model, @RequestParam String noteId) {
		MemberVO member = (MemberVO) auth.getPrincipal();
		String name = member.getMemName();

		NoteVO note = service.selectNote(new NoteVO(noteId));

		if (note.getReadNo().equals("N")) {
			service.ReadNote(note);
		}

		model.addAttribute("note", note);
		model.addAttribute("name", name);
		return "note/noteView";
	}

	@RequestMapping(value = "/note", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public int noteView(Authentication auth) {
		int cnt = 0;
		if (auth != null) {
			cnt = service.countUnreadNotes(auth.getName());
			System.out.println(cnt);
		}
		return cnt;
	}
}
