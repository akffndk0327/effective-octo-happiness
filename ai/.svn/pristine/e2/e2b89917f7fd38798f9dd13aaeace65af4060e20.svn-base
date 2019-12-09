package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Repository
public class RecipeAttatchVO {

	public RecipeAttatchVO(MultipartFile partWrapper) {
		this.partWrapper = partWrapper;
		recipeattName = partWrapper.getOriginalFilename();
		recipeattFilesize = (int) partWrapper.getSize();
		recipeattFancy = FileUtils.byteCountToDisplaySize(recipeattFilesize);
		recipeattSavename = UUID.randomUUID().toString();
		
		
	}

	private String recipeattId;
	private String recipeattSavename;
	private String recipeattName;
	private int recipeattFilesize;
	private String recipeattFiletype;
	private String recipeattFancy;
	private int recipeNo;

	private MultipartFile partWrapper;

	public void saveFile(File saveFolder) throws IOException {
		partWrapper.transferTo(new File(saveFolder, recipeattSavename));
	}
}
