package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchVO implements Serializable,Comparable<SearchVO>{
	
	private String searchName; 
	private int searchCount;  
	private String searchDate; 
	
	@Override
	public int compareTo(SearchVO o) {
		if (searchCount > o.searchCount) {
			return -1;
		}else if(searchCount==o.searchCount) {
			return 0;
		}else {
			return 1;
		}
	}
	
}
