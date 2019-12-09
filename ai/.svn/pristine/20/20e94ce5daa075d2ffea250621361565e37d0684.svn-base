package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="menuId")
public class MenuVO implements Serializable,Comparable<MenuVO>{
	private String menuId;
	private String season;
	private String menuName;
	private long menuWeight;
	private String check ="false";
	
	
	private int count;
	
	@Override
	public int compareTo(MenuVO o) {
		if (menuWeight > o.menuWeight) {
			return 1;
		}else if(menuWeight==o.menuWeight) {
			return 0;
		}else {
			return -1;
		}
	}

}
