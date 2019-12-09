package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 이진희
 * @since 2019. 11. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 *
 * --------     --------    ----------------------
 * 2019. 11. 5.      이진희       최초작성
 * Copyright (c) 2019 by DDIT All right reserved
 * </pre>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(of="uneatId")
public class UneatableVO implements Serializable{
	
	private Integer rnum;
	
	@NotNull
	private Long rtrvldsuseSeq;   //회수폐기일련번호
	
	@NotBlank
	private String prdtnm;   // 제품명
	private String bsshnm;   // 업소명
	
	@NotBlank
	private String mnfdt;    // 제조일자
	
	@NotBlank
	private String distbtmlmt;  // 유통기한
	
	@NotBlank
	private String addr;  // 영업자주소
	
	@NotBlank
	private String insttNm;   // 검사기관
	private String regstrTelno;  // 전화번호
	private String frmlcunit;  // 포장단위
	
	@NotBlank
	private String testItmnm;  // 부적합한항목
	@NotBlank
	private String stdrStnd;  // 기준규격
	@NotBlank
	private String testanalsRslt;  // 검사결과
	private String cretDtm; // 등록일
	private int uneatId; // 부적합한식품아이디
	

}
