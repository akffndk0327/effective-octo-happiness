package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class PollutionListVO implements Serializable {
	private String dataTime;
	private String itemCode;
	private String dataGubun;
	private String seoul;
	private String busan;
	private String daegu;
	private String incheon;
	private String gwangju;
	private String daejeon;
	private String ulsan;
	private String gyeonggi;
	private String gangwon;
	private String chungbuk;
	private String chungnam;
	private String jeonbuk;
	private String jeonnam;
	private String gyeongbuk;
	private String gyeongnam;
	private String jeju;
	private String sejong;
}
