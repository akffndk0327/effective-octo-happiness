package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChemicalsVO implements Serializable{
	private String casId;
	private String cheNameKo;
	private String cheNameEn;
	
}
