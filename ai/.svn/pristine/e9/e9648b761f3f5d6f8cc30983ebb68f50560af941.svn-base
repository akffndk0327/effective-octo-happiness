package kr.or.ddit.note.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.note.dao.INoteDAO;
import kr.or.ddit.vo.NoteVO;
import kr.or.ddit.vo.PagingInfoVO;

@Service
public class NoteServiceImpl implements INoteService {

	@Inject
	INoteDAO dao;
	@Override
	public int countUnreadNotes(String memId) {
		// TODO Auto-generated method stub
		return dao.countUnreadNotes(memId);
	}
	@Override
	public int SendNote(NoteVO note) {
		// TODO Auto-generated method stub
		return dao.SendNote(note);
	}
	@Override
	public int DeleteNote(NoteVO note) {
		// TODO Auto-generated method stub
		return dao.DeleteNote(note);
	}
	@Override
	public int ReadNote(NoteVO note) {
		// TODO Auto-generated method stub
		return dao.ReadNote(note);
	}
	@Override
	public List<NoteVO> SendNotesList(PagingInfoVO<NoteVO> pagingVO) {
		// TODO Auto-generated method stub
		return dao.SendNotesList(pagingVO);
	}
	@Override
	public List<NoteVO> RecieveNotesList(PagingInfoVO<NoteVO> pagingVO) {
		// TODO Auto-generated method stub
		return dao.RecieveNotesList(pagingVO);
	}
	@Override
	public int selectSendNoteCount(PagingInfoVO<NoteVO> pagingVO) {
		// TODO Auto-generated method stub
		return dao.selectSendNoteCount(pagingVO);
	}
	@Override
	public int selectRecieveNoteCount(PagingInfoVO<NoteVO> pagingVO) {
		// TODO Auto-generated method stub
		return dao.selectRecieveNoteCount(pagingVO);
	}
	@Override
	public NoteVO selectNote(NoteVO note) {
		// TODO Auto-generated method stub
		return dao.selectNote(note);
	}
	@Override
	public List<String> selectCompany() {
		// TODO Auto-generated method stub
		return dao.selectCompany();
	}
	@Override
	public List<String> selectMember() {
		// TODO Auto-generated method stub
		return dao.selectMember();
	}
	@Override
	public List<String> selectAll() {
		List<String> list=new ArrayList<String>();
		list.addAll(dao.selectCompany());
		list.addAll(dao.selectMember());
		return list;
	}


}
