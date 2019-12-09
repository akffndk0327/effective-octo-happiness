package kr.or.ddit.note.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.note.service.INoteService;
import kr.or.ddit.vo.NoteVO;

@Controller
public class NoteDeleteController {
	@Inject
	INoteService service;
	
	@ResponseBody
	@RequestMapping("/note/noteDelete.do")
	public String deleteNote(@RequestParam String noteNo) {
		int cnt=service.DeleteNote(new NoteVO(noteNo));
		String status="";
		if(cnt>0) {
			status="OK";
		}
		return status;
	}
}
