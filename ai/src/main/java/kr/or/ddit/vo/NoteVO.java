package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
@Data
public class NoteVO implements Serializable{
	private String noteNo; 
	private String sendId; 
	private String recieveId; 
	private String sendContent; 
	private String sendDate; 
	private String readNo; 
	private String deleteNo;
	
	public NoteVO(String noteNo) {
		super();
		this.noteNo = noteNo;
	}
	
	public NoteVO() {
	}
	
	
}
