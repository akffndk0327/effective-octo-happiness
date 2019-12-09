package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="adId")
public class AdhitVO implements Serializable {
	

	private int adHit;
	private int adId;
	private String adpoId;
	private String resId;
	
	private AdpositionVO adposition ; 
}
