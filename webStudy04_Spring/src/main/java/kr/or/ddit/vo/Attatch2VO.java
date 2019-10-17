package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Attatch2VO implements Serializable {
	//생성자만들기 
	public Attatch2VO(MultipartFile partWrapper) {
		this.partWrapper = partWrapper; //이걸 이용해서 저장학 ㅣ
		att_filename = partWrapper.getOriginalFilename();
		att_mime = partWrapper.getContentType();
		att_filesize = partWrapper.getSize();
		att_fancysize = FileUtils.byteCountToDisplaySize(att_filesize); //바이트로 받아러 팬시사이즈로 돌려줌
		att_savename = UUID.randomUUID().toString();
	}
	
	private Integer att_no;
	private Integer bo_no;
	private String att_filename; //원본파일명 
	private String att_savename; //저장명
	private String att_mime;	
	private long att_filesize; //number(12)
	private String att_fancysize;
	private Integer att_downcount;
	
	private MultipartFile partWrapper;
	public void saveFile(File saveFolder) throws IOException {
		partWrapper.transferTo(new File(saveFolder, att_savename));
	}
	
	//2진데이터는 없어
}
