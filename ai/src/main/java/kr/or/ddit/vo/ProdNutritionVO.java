package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdNutritionVO implements Serializable{
	private String prodId;
	private String rawId;
	
	private String[] rawIds;
	private List<ProdNutritionVO> rawList;
	
	private RawMaterialVO raw;
}
