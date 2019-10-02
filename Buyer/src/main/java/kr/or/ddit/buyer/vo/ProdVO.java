package kr.or.ddit.buyer.vo;

import lombok.Data;

@Data
public class ProdVO {
	private String prod_id;
	private String prod_name;
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdVO other = (ProdVO) obj;
		if (prod_id == null) {
			if (other.prod_id != null)
				return false;
		} else if (!prod_id.equals(other.prod_id))
			return false;
		if (prod_name == null) {
			if (other.prod_name != null)
				return false;
		} else if (!prod_name.equals(other.prod_name))
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prod_id == null) ? 0 : prod_id.hashCode());
		result = prime * result + ((prod_name == null) ? 0 : prod_name.hashCode());
		return result;
	}
	@Override
	public String toString() {
		return "ProdVO [prod_id=" + prod_id + ", prod_name=" + prod_name + "]";
	}
	
	
	
}
