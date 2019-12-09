package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository
public class SurveyVO implements Serializable {
	private String surId;
	private String subName;
	private String surEnddate;
	private String surPurpose;
	private String surContent;
	private String surLead;
	private String surIndate;
	private String surUse;
	private Integer rnum;
	private List<QuestionVO> questionList;
}
