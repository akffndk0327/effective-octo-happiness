package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RawMaterialVO implements Serializable{
	private String rawId;
	private String rawName;
	
}
