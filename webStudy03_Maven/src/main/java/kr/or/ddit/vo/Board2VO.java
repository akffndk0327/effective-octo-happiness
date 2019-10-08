package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="bo_no")
public class Board2VO implements Serializable{
	public Board2VO(int bo_no){
		this.bo_no = bo_no;
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
	private Integer bo_parent;
	
	//댓글 
	private List<Reply2VO> replyList;
	//첨부파일 
	private List<Attatch2VO> attatchList;
	private Integer[] delAttaches; // ㅣㅈ우려는 파일의 번호르 ㄹ배열로 관리 가능 ㅇ이걸 attDao에서 받어 
}
