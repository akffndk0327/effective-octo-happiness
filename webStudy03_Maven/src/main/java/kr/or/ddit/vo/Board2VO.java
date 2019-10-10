package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.wrapper.PartWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="bo_no")
public class Board2VO implements Serializable{

	// 글번호 
	public Board2VO(int bo_no){
		this.bo_no = bo_no;
	}
	
	//삭제할때 글번호, 비번 받기 
	public Board2VO(int bo_no, String bo_pass){
		this.bo_no = bo_no;
		this.bo_pass = bo_pass;
	}
	
	private Integer rnum;
	private Integer level; // 계층 구조상에서 글의 깊이 1 : 원글, >1: 답글
	private Integer bo_no;
	private String board_type;
	private String board_name;
	private String bo_title;
	private String bo_writer;
	private String bo_date;
	private String bo_content;
	private String bo_pass;
	private String bo_ip;
	private Integer bo_hit;
	private Integer bo_like;
	private Integer bo_parent; //답글 쓸때 원글 찾아가는 변수 
	
	//댓글 
	private List<Reply2VO> replyList;
	//첨부파일 
	private List<Attatch2VO> attatchList;
	
	private PartWrapper[] bo_file;
	
	public void setBo_file(PartWrapper[] bo_file) { // 왜????
		this.bo_file = bo_file;
		if(bo_file ==null|| bo_file.length==0) return;
		//여기 건너뛰면 첨부파일 잇는거 
		attatchList = new ArrayList<Attatch2VO>();
		for(PartWrapper tmp:bo_file) { //tmp: 업로드되는 파일들 VO가 만들어져야함.
			attatchList.add(new Attatch2VO(tmp)); //mapper? wrapper 구조가 됨.
			
		}
	}
	private Integer[] delAttatches; // ㅣㅈ우려는 파일의 번호르 ㄹ배열로 관리 가능 ㅇ이걸 attDao에서 받어 
	
	private Integer attNoStart;
	
	
}





















