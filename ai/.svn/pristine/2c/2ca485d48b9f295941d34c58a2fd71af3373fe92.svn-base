package kr.or.ddit.vo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of="newsNo")
@ToString(exclude= {"newsImage","newsContent"})
public class NewsVO {
	public NewsVO(int newsNo) {
		this.newsNo = newsNo;
	}
	private int rnum;
	private int newsNo;
	private String newsTitle;
	private String newsContent;
	private String newsIndate; // 작성일 
	private int newsHit;	   // 조회수 
	private String memId;
	private MultipartFile newsImg;

}
