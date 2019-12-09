package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdreplyVO implements Serializable {
	
	public AdreplyVO(Integer adId) {
		super();
		this.adId = adId;
	}
	private String adreplyId;
	private String adreplyDate;
	private String adreplyCont;
	private int adId;
	private String memId;
	
}
