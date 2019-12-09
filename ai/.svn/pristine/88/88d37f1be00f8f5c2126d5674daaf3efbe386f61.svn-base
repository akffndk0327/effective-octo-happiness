package kr.or.ddit.vo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="bioCheId")
@ToString(exclude= {"bioImg","bioCheImg"})
public class BioCheProVO implements Serializable {
	
	public BioCheProVO(String bioCheId) {
		this.bioCheId =bioCheId;
	}
	
	private int rnum;
	private String bioCheId         ;	//제품 아이디
	@NotBlank
	private String bioCheName       ;	//제품명 
	@NotBlank
	private String bioCheCont       ; 	 //제품분류/용도/중량
	@NotBlank
	private String bioCheCom        ; 	//제조업체
	@NotBlank
	private String bioCheComAddr    ;	//제조업체 주소
	@NotBlank
	private String bioChePreca      ;	//제품 주의사항 
	
	private byte[] bioCheImg	 ;	//제품이미지   BLOB
	
	private MultipartFile bioImg;
	
	//바이너리로 변환됨
	public void setBio_img(MultipartFile bioImg) throws IOException {
		this.bioImg = bioImg;
		if(bioImg.getSize()<=0) {
			return;
		}
		bioCheImg = bioImg.getBytes();
	}
	
	@JsonIgnore
	public String getBio_imgBase64() {
		if(bioCheImg==null) {
			return null;
		}
		return Base64.getEncoder().encodeToString(bioCheImg);
	}
	
	public List<BiochemisVO> biocheList; //화학성분 리스트 bioprod has many biochemi
	
	private String casId;
	
}
