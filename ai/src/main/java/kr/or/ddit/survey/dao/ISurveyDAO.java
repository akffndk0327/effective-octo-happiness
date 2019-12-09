package kr.or.ddit.survey.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.ExampleVO;
import kr.or.ddit.vo.PagingInfoVO;
import kr.or.ddit.vo.QuestionVO;
import kr.or.ddit.vo.RecipeBoardVO;
import kr.or.ddit.vo.SurveyResultVO;
import kr.or.ddit.vo.SurveyVO;

@Repository
public interface ISurveyDAO {
	public int getSurveyNextVal();
	public int getQuestionNextVal();
	public int insertSurvey(SurveyVO survey);
	public int getSurveyId();
	public int insertQuestion(QuestionVO question);
	public int getQuestion();
	public int insertExample(ExampleVO example);

	public List<SurveyVO> selectSurveyList(PagingInfoVO<SurveyVO> pagingVO);
	public SurveyVO selectSurvey(String survey);
	public int selectSurveyCount(PagingInfoVO<SurveyVO> pagingVO);

	public int insertSurveyResult(SurveyResultVO result);

	public List<SurveyResultVO> selectSurveyResponse(int surId);
	public List<SurveyResultVO> selectSurveyResponseCount(int surId);

	public List<ExampleVO> selectExample(int surId);

	public int deleteSurvey(SurveyVO survey);
}
