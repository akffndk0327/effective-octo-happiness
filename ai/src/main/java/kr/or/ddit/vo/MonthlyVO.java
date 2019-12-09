package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="monthlyId")
public class MonthlyVO implements Serializable{
	private String monthlyIndate;
	private String menuId;
	private String monthlyId;
	private String memId;
	private String monthlyUse;
	private String monthlyTitle;
	private int rnum;
	private String monPass;
}
