package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="reviewNo")
public class ReviewVO implements Serializable{
	private Integer rnum;
	private Integer reviewNo;
	private String reviewContent;
	private String reviewUse;
	private String reviewIndate;
	private String orderId;
	private String prodId;
	private String memId; //작성자id
	private String reviewEval;
	
	private CodeVO code;
}
