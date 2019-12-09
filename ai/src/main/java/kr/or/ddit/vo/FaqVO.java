package kr.or.ddit.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="faqNo")
public class FaqVO implements Serializable {
	private int rnum;
	private int faqNo;
	@NotBlank
	private String faqTitle;
	@NotBlank
	private String faqContent;
	private String faqUse;
	private String faqIndate;
	private int faqHit;
	private String memId;
	private String resId;
	
	private AdminVO admin;
}
