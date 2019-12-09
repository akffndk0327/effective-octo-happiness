package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="prodattId")
public class ProdAttatchVO implements Serializable{
	public ProdAttatchVO(MultipartFile partWrapper){
		this.partWrapper = partWrapper;
		prodAttName = partWrapper.getOriginalFilename();
		prodAttFiletype = partWrapper.getContentType();
		prodAttFilesize = partWrapper.getSize();
		prodAttFancy = FileUtils.byteCountToDisplaySize(prodAttFilesize);
		prodAttSavename = UUID.randomUUID().toString();
	}
	private String prodattId;                              
	private String prodId; 
	private String prodAttName;                           
	private String prodAttSavename;                               
	private String prodAttFiletype;                            
	private Long prodAttFilesize;                            
	private String prodAttFancy;                       
	
	private MultipartFile partWrapper;
	public void saveFile(File saveFolder) throws IOException {
		partWrapper.transferTo(new File(saveFolder, prodAttSavename));
	}

}
