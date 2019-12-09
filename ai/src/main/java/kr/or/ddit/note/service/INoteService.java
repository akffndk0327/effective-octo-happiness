package kr.or.ddit.note.service;

import java.util.List;

import kr.or.ddit.vo.NoteVO;
import kr.or.ddit.vo.PagingInfoVO;

public interface INoteService {
	public int countUnreadNotes(String memId);
	public List<NoteVO> SendNotesList(PagingInfoVO<NoteVO> pagingVO);
	public List<NoteVO> RecieveNotesList(PagingInfoVO<NoteVO> pagingVO);
	public int SendNote(NoteVO note);
	public int DeleteNote(NoteVO note);
	public int ReadNote(NoteVO note);
	public int selectSendNoteCount(PagingInfoVO<NoteVO> pagingVO);
	public int selectRecieveNoteCount(PagingInfoVO<NoteVO> pagingVO);
	public NoteVO selectNote(NoteVO note);
	public List<String> selectCompany();
	public List<String> selectMember();
	public List<String> selectAll();
}
