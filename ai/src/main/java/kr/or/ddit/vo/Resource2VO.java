package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="resId")
public class Resource2VO implements Serializable{
	
	private String resId;
	private String resName;
	private String resPattern;
	private String resDesc;
	private String resType;
	private int resOrder;
	private String resIndate;
	private String resModate;
	private String resParent;
	
	
}
