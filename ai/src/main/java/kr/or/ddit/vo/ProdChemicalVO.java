package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdChemicalVO implements Serializable{
	private String prodId;
	private String casId;
	
	private String[] casIds;
	private List<ProdChemicalVO> cheList;
	private ChemicalsVO chemical;
}
