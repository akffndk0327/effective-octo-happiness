package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class ReplyVO implements Serializable{
	private String repId;
	private String repDate;
	private String repCont;
	private String parRep;
	private int recipeNo;
	private String memId;
	private String repDel;
	private int level;
}
