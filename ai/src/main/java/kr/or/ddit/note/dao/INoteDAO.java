package kr.or.ddit.note.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.NoteVO;
import kr.or.ddit.vo.PagingInfoVO;

@Repository
public interface INoteDAO {
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
}
