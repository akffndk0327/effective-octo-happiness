package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class SurveyResultVO implements Serializable{
	private String exampleResult;
	private String tokenResult;
	private String examId;
	private String questId;
	private String surId;
	private String resultId;
	private String questionId;
	private String tokenCount;
	
	
	private List<SurveyResultVO> list;
	
	private SurveyVO surveyVO;
	private QuestionVO questionVO;
	private ExampleVO examVO;
	private List<ExampleVO> examList;
}
