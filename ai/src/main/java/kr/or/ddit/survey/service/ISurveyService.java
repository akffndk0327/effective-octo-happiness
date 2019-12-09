package kr.or.ddit.survey.service;

import java.util.List;

import kr.or.ddit.enums.ServiceResult;
import kr.or.ddit.vo.ExampleVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.QuestionVO;
import kr.or.ddit.vo.SurveyResultVO;
import kr.or.ddit.vo.SurveyVO;

public interface ISurveyService {
	public ServiceResult insertSurvey(SurveyVO survey);
	
	public List<SurveyVO> selectSurveyList(PagingInfoVO<SurveyVO> pagingVO);
	public SurveyVO selectSurvey(String recipe);
	public int selectSurveyCount(PagingInfoVO<SurveyVO> pagingVO);
	public ServiceResult insertSurveyResult(SurveyResultVO result);
	public List<SurveyResultVO> selectSurveyResponse(int surId);
	
	public List<ExampleVO> selectExample(int surId);
	public int deleteSurvey(SurveyVO survey);
	public List<SurveyResultVO> selectSurveyResponseCount(int surId);
}
