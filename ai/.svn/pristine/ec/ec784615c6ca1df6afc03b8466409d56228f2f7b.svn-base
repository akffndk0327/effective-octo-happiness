package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewsAttatchVO implements Serializable {
	public NewsAttatchVO(MultipartFile partWrapper) {
		this.partWrapper = partWrapper;
		newsattName = partWrapper.getOriginalFilename();
		newsattFiletype = partWrapper.getContentType();
		newsattFilesize = partWrapper.getSize();
		newsattFancy = FileUtils.byteCountToDisplaySize(newsattFilesize);
		newsattSavename = UUID.randomUUID().toString();
	}
	private String newsattId;
	private int newsNo;
	private String newsattName; //파일명
	private String newsattSavename; //저장명
	private long newsattFilesize;
	private String newsattFiletype; //mime
	private String newsattFancy;
	
	private MultipartFile partWrapper;
	public void saveFile(File saveFolder) throws IOException {
		partWrapper.transferTo(new File(saveFolder, newsattSavename));
	}
}
