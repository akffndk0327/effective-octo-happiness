package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="menuId")
public class MenuAllergyVO implements Serializable{
	private String menuId;
	private String allId;
	
	private AllergySymtomVO allergySym;
}
