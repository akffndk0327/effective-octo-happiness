package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;


@Data
public class AllergySymtomVO implements Serializable{
	private String symId;
	private String symName;
	private int symStrength;
	private String symColor;
}
