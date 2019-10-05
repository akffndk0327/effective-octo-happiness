package kr.or.ddit.alba.vo;

import java.util.List;

import lombok.Data;

@Data
public class Lic_albaVO{
	private String al_id       ;
	private String lic_code    ;
	private Byte[] lic_image   ;
	
	private List<LicenseVO> licenseList;

}
