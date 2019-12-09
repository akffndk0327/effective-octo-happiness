
package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class AdminVO implements Serializable{
	private String adminNum;
	private String adminTel;
	private String adminAddr;
	private String adminName;
	private String memId;
}
