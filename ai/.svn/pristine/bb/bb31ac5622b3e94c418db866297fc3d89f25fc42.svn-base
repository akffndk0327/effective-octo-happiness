package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.hints.UpdateHint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 이진희
 * @since 2019. 11. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 11.      이진희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(of="correctNo")
public class CorrectBoardVO implements Serializable{
	
	public CorrectBoardVO(int correntNo) {
		this.correctNo = correntNo;
	}
	
	public CorrectBoardVO(int correctNo,int correctPw) {
		this.correctNo = correctNo;
		this.correctPw = correctPw;
		
	}
	
	public CorrectBoardVO(int correctNo,String memId) {
		this.correctNo = correctNo;
		this.memId = memId;
		
	}
	
	
	private int rnum;
	private Integer level;
	
	@NotNull(groups=UpdateHint.class)
	private Integer correctNo; //게시글번호 
	private String correctIndate; //게시글 등록날짜
	private String correctTitle; //게시글 제목
	private String correctContent; //게시글 내용
	private int correctPw; //게시글 비밀번호
	private Integer correctParent; //게시글원글번호
	private String resId; //게시판종류 ->식품,화학,그외등등
	private String memId; //작성자 아이디
	private String correctType; // 게시판 타입
	private String correctUse;  //비밀글 여부 
	
	private List<CorrectAttatchVO> attatchList;
	private MultipartFile[] bo_file;
	
	private String codeName; //대분류명
	private String resName;//소분류명
	
	public void setBo_file(MultipartFile[] bo_file) {
		this.bo_file = bo_file;
		if(bo_file==null || bo_file.length==0) return;
		attatchList = new ArrayList<>();
		for(MultipartFile tmp : bo_file) {
			//비어있는 파일 필터링
			if(tmp.getSize()<=0) continue;
			attatchList.add(new CorrectAttatchVO(tmp));
		 }
	}
	
	private Integer[] delAttaches;
	
	private Integer attNoStart;
}
