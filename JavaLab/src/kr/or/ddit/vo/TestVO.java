package kr.or.ddit.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

//builder 적용하기
@AllArgsConstructor
@Builder(access=AccessLevel.PUBLIC)
@Getter
@ToString(of= {"mem_id", "mem_pass"})
@EqualsAndHashCode(of="mem_id")
public class TestVO {
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private transient String mem_regno1;
	private transient String mem_regno2;
	private String mem_bir;
	private String mem_zip;
	private String mem_add1;
	private String mem_add2;
	private String mem_hometel;
	private String mem_comtel;
	private String mem_hp;
	private String mem_mail;
	private String mem_job;
	private String mem_like;
	private String mem_memorial;
	private String mem_memorialday;
	private Integer mem_mileage;
	private String mem_delete;
}
