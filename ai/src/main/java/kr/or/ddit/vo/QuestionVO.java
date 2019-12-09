package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import lombok.Data;
@Data
public class QuestionVO implements Serializable{
	private String questId;
	private String questCont;
	private String questType;
	private String surveyId;
	
	private List<ExampleVO> exampleList;
	private List<String> plainTokenResult;
	private List<tokenVO> splitToken;
	
	@Data
	public static class tokenVO implements Serializable{
		private String token;
		private int count;
	}

}
