package kr.or.ddit.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="noticeNo")
public class NoticeVO implements Serializable{
	private Integer rnum;
	private Integer noticeNo;
	@NotBlank
	private String noticeTitle;
	@NotBlank
	private String noticeContent;
	private String noticeUse;
	private String noticeIndate;
	private Integer noticeHit;
	private String memId;
	private String noticeLevel;
	
	private AdminVO admin;
}
