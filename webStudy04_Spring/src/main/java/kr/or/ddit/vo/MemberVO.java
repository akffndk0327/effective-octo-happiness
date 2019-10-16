package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
/**
 * 마이페이지 조회할때 그동안 구매한 상품기록을 함께 조회 => has many
 * @author PC-03
 *
 */
@Data
public class MemberVO implements Serializable{
	
	public MemberVO() {
		super();
	}
	public MemberVO(String mem_id, String mem_pass) {
		super();
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
	}
	
//	@NotNull //null 이면 안된다 의미  검증룰 
	@NotBlank(message="아이디누락") //stringUtils.isnotBlank오 ㅏ같음
	//데이타 딕셔너리 이용하기
	private String mem_id;
	@NotBlank
	@Length(min=4,max=12) //정규식
	private String mem_pass;
	@NotBlank
	private String mem_name;
	@Length(min=6,max=6)
	private transient String mem_regno1; // transient : 데이터 저장도안됨.
	@Length(min=7,max=7)
	private transient String mem_regno2;
	private String mem_bir;
	private String mem_zip;
	private String mem_add1;
	private String mem_add2;
	private String mem_hometel;
	private String mem_comtel;
	private String mem_hp;
	
	@NotBlank
	@Email
	private String mem_mail;
	
	private String mem_job;
	private String mem_like;
	private String mem_memorial;
	private String mem_memorialday;
	private Integer mem_mileage;
	private String mem_delete;
	private byte[] mem_img;
	
	public String getMem_imageBase64() {
		if(mem_img ==null) return null;
		return Base64.getEncoder().encodeToString(mem_img);
	}
	private String mem_role;
	
	private List<ProdVO> prodList;
	
	private MultipartFile mem_image;
	public void setMem_image(MultipartFile mem_image) throws IOException {
		this.mem_image = mem_image;
		//업로드여부확인
		if(mem_image.getSize()<=0) return;
		mem_img = mem_image.getBytes();
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mem_id == null) ? 0 : mem_id.hashCode());
		return result;
	}
	//이거 없엇으면 주소로 판단하기 때문에  인증 어려워짐 
	//상태비교 메소드equals() 꼭 잇어얗 ㅐ! 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		if (mem_id == null) {
			if (other.mem_id != null)
				return false;
		} else if (!mem_id.equals(other.mem_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MemberVO [mem_id=" + mem_id + ", mem_pass=" + mem_pass + ", mem_name=" + mem_name + ", mem_add1="
				+ mem_add1 + ", mem_add2=" + mem_add2 + ", mem_hp=" + mem_hp + "]";
	}
	
	
	
	
}
