package kr.or.ddit.alba.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class AlbaVO implements Serializable{
	private String al_id;
	private String al_name;
	private int al_age;
	private String al_address;
	private String al_hp;
	private String al_spec;
	private String al_desc;
	private String gr_code;
	private String al_career;
	private String al_gen;
	private String al_btype;
	private String al_mail;
	private GradeVO grade;	
	
	private List<Lic_albaVO> licAlbaLists;
	
	public AlbaVO() {
		super();
	}
	
	public AlbaVO(String al_id, String al_name) {
		super();
		this.al_id = al_id;
		this.al_name = al_name;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((al_id == null) ? 0 : al_id.hashCode());
		result = prime * result + ((al_name == null) ? 0 : al_name.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "AlbaVO [al_id=" + al_id + ", al_name=" + al_name + ", al_age=" + al_age + ", al_address=" + al_address
				+ ", al_hp=" + al_hp + ", al_spec=" + al_spec + ", al_desc=" + al_desc + ", gr_code=" + gr_code
				+ ", al_career=" + al_career + ", al_gen=" + al_gen + ", al_btype=" + al_btype + ", al_mail=" + al_mail
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlbaVO other = (AlbaVO) obj;
		if (al_id == null) {
			if (other.al_id != null)
				return false;
		} else if (!al_id.equals(other.al_id))
			return false;
		if (al_name == null) {
			if (other.al_name != null)
				return false;
		} else if (!al_name.equals(other.al_name))
			return false;
		return true;
	}
	
}
