package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.hints.InsertHint;
import kr.or.ddit.common.hints.UpdateHint;
import lombok.Data;
/**
 * 10.01
 * 상품 한건에 대해 해당 거래처에 대한 상세정보까지 조회
 * Data mapper를 이용해서 여러개의 테이블로부터 데이터를 조회하는 방법
 * 1. 메인 테이블 선정 테이블간의 관계성 파악
 * 	1:1, 1: N 
 * 2. 모델링 , 테이블간의 관계성을 Domain layer에 반영 
 *  1:1 => has a 
 *  1:N => has many
 * 3. 직접 조인 쿼리 작성 -> resultType 대신 resultMap 으로 수동 바인드 => <id="">가 있어야해 
 *   has a => association
 *   has many => collection
 * 
 */

@Data
public class ProdVO implements Serializable { // implements Serializable : 꼮하기 !!
	@NotBlank(groups = UpdateHint.class)
	private String prod_id;
	@NotBlank
	private String prod_name;
	@NotBlank(groups=InsertHint.class)
	private String prod_lgu;
	private String lprod_nm;
	@NotBlank(groups=InsertHint.class)
	private String prod_buyer;
	private String buyer_name;
	@NotNull
	@Min(0)
	private Integer prod_cost;
	@NotNull
	@Min(0)
	private Integer prod_price;
	@NotNull
	@Min(0)
	private Integer prod_sale;
	private String prod_outline;
	private String prod_detail;
	private String prod_img;
	private Integer prod_totalstock;
	@Pattern(regexp="\\d{4}-||d_(2)-||d{2}")
	private String prod_insdate;
	private Integer prod_properstock;
	private String prod_size;
	private String prod_color;
	private String prod_delivery;
	private String prod_unit;
	private Integer prod_qtyin;
	private Integer prod_qtysale;
	private Integer prod_mileage;
	private BuyerVO buyer; //상품하나가 거래처하나의 관계를 가지고잇다  ProdVO has a BuyerVO(1:1 관계) prod가buyer를 가지고 있다 
	private List<MemberVO> memberList; // ProdVO has many MemberVO (1:n) - 컬렉션으로 바인딩 
	
	//1016 이미지업로드 . 여기부터 
	private MultipartFile prod_image; // 첨부파일 받을수 구조가 됨.
	public void setProd_image(MultipartFile prod_image) throws IOException {
		this.prod_image = prod_image;
		if(prod_image.getSize()<=0) return; //멤버VO참조
		prod_img = UUID.randomUUID().toString(); //저장명 만들어짐. 아직 저장은 안됨 ! -> 이러면prod.setProd_img(savename); 이거 피룡없어
	}
	
	public void transfer(File saveFolder) throws IllegalStateException, IOException {
		//저장위치받어
		if(prod_image==null && prod_img ==null) return;
		prod_image.transferTo(new File(saveFolder, prod_img));
	} 
	//여기까지 한세트 
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prod_id == null) ? 0 : prod_id.hashCode());
		return result;
	}

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
		return true;
	}

	@Override
	public String toString() {
		return "ProdVO [prod_id=" + prod_id + ", prod_name=" + prod_name + ", prod_lgu=" + prod_lgu + ", prod_buyer="
				+ prod_buyer + ", prod_cost=" + prod_cost + ", prod_price=" + prod_price + ", prod_sale=" + prod_sale
				+ ", prod_outline=" + prod_outline + ", prod_detail=" + prod_detail + ", prod_img=" + prod_img
				+ ", prod_totalstock=" + prod_totalstock + ", prod_insdate=" + prod_insdate + ", prod_properstock="
				+ prod_properstock + ", prod_size=" + prod_size + ", prod_color=" + prod_color + ", prod_delivery="
				+ prod_delivery + ", prod_unit=" + prod_unit + ", prod_qtyin=" + prod_qtyin + ", prod_qtysale="
				+ prod_qtysale + ", prod_mileage=" + prod_mileage + "]";
	}
	
	
	
	

}
