package kr.or.ddit.survey.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import kr.or.ddit.survey.service.ISurveyService;
import kr.or.ddit.vo.ExampleVO;
import kr.or.ddit.vo.QuestionVO;
import kr.or.ddit.vo.QuestionVO.tokenVO;
import kr.or.ddit.vo.SurveyResultVO;

@Controller
public class SurveyResultController {

	@Inject
	ISurveyService service;

	@Inject
	WebApplicationContext container;
	ServletContext application;

	@Value("#{appInfo.FolderRealPath}")
	String saveFolderURL;
	File saveFolder;

	public void surveyResultCloud(List<QuestionVO> word) {

		application = container.getServletContext();
		saveFolder = new File(saveFolderURL);
		if (!saveFolder.exists())
			saveFolder.mkdirs();

		try {
			String filename;
			for (int i = 0; i < word.size(); i++) {
				filename = saveFolderURL + "/" + word.get(i).getSurveyId() + "_" + word.get(i).getQuestId() + ".csv";
				BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(filename), "MS949"));
				String head = "text,frequency\r\n";
				writer.write(head);
				for (int k = 0; k < word.get(i).getSplitToken().size(); k++) {
					String text = word.get(i).getSplitToken().get(k).getToken();
					String count = word.get(i).getSplitToken().get(k).getCount()+"";
					String content = text+","+count+"\r\n";
					writer.write(content);
				}
				writer.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@RequestMapping("survey/surveyResult.do")
	public String surveyResult(@RequestParam int sur, Model model) {
		Set<QuestionVO> question = new HashSet<>();
		// List<QuestionVO> question = null;

		// 설문지 질문+보기 목록 출력
		List<ExampleVO> exampleList = service.selectExample(sur);

		// 설문지 질문별 답변 출력
		List<SurveyResultVO> list = service.selectSurveyResponse(sur);

		for (SurveyResultVO resultVO : list) {
			// 설문지 질문별 타입 확인
			// 1. 객관식
			if (resultVO.getQuestionVO().getQuestType().equals("example")) {
				for (ExampleVO vo : exampleList) {
					if (vo != null) {
						// 동일질문여부 확인
						if (resultVO.getQuestId().equals(vo.getQuestionId())) {
							// 질문선택여부 확인
							if (resultVO.getExamVO().getExamId() != null) {
								// 질문선택번호 확인
								if (resultVO.getExamVO().getExamId().equals(vo.getExamId())) {
									vo.setCheckCount(
											((vo.getCheckCount() + "").equals("") ? 0 : vo.getCheckCount()) + 1);
								}
							}
						}
					}
				}
			}
			// 질문 리스트 만들어 버리기
			QuestionVO qvo = new QuestionVO();
			qvo.setQuestId(resultVO.getQuestionVO().getQuestId());
			qvo.setQuestCont(resultVO.getQuestionVO().getQuestCont());
			qvo.setQuestType(resultVO.getQuestionVO().getQuestType());
			qvo.setSurveyId(resultVO.getSurveyVO().getSurId());
			question.add(qvo);
		}

		// 질문 목록 만들어서 정렬
		List<QuestionVO> quest = new ArrayList<>(question);
		quest.sort(new Comparator<QuestionVO>() {

			@Override
			public int compare(QuestionVO o1, QuestionVO o2) {
				int one = Integer.parseInt(o1.getQuestId());
				int two = Integer.parseInt(o2.getQuestId());
				if (one == two)
					return 0;
				else if (one > two)
					return 1;
				else
					return -1;
			}
		});

		// 답변 리스트 빈 값 지우기
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < exampleList.size(); j++) {
				if (exampleList.get(j) == null) {
					exampleList.remove(j);
				}
			}
		}

		// 질문 안에 답변 넣기
		for (int i = 0; i < quest.size(); i++) {
			List<ExampleVO> ex = new ArrayList<>();
			for (int j = 0; j < exampleList.size(); j++) {
				if (quest.get(i).getQuestId() != null) {
					if (exampleList.get(j) != null) {
						if (StringUtils.isNotBlank(exampleList.get(j).getQuestionId())) {
							if (quest.get(i).getQuestId().equals(exampleList.get(j).getQuestionId())) {
								ex.add(exampleList.get(j));
								quest.get(i).setExampleList(ex);
							}
						}
					}
				}
			}
		}

		// 질문 안에 주관식 넣기
		for (int j = 0; j < quest.size(); j++) {// 질문 리스트
			List<String> plain = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {// 전체 결과 리스트
				if (list.get(i).getTokenResult() != null) {
					if (list.get(i).getQuestionVO().getQuestType().equals("token")) {
						if (quest.get(j).getQuestId().equals(list.get(i).getQuestionVO().getQuestId())) {
							plain.add(list.get(i).getTokenResult());
						}
					}
				}
			}
			if (quest.get(j).getQuestType().equals("token")) {
				quest.get(j).setPlainTokenResult(plain);
			}
		}

		if (list.size() != 0) {
			model.addAttribute("SubName", list.get(0).getSurveyVO().getSubName());
		}

		List<SurveyResultVO> token = service.selectSurveyResponseCount(sur);
		List<QuestionVO> word = new ArrayList<>();
		Set<String> set = new HashSet<>();
		for (int i = 0; i < token.size(); i++) {
			QuestionVO vo = new QuestionVO();
			if (!set.contains(token.get(i).getQuestId())) {
				set.add(token.get(i).getQuestId());
				vo.setQuestId(token.get(i).getQuestId());
				word.add(vo);
			}
		}

		for (int i = 0; i < word.size(); i++) {
			List<tokenVO> tlist = new ArrayList<>();
			for (int j = 0; j < token.size(); j++) {
				if (word.get(i).getQuestId().equals(token.get(j).getQuestId())) {
					if (token.get(j).getTokenResult() != null) {
						tokenVO vo = new tokenVO();
						vo.setToken(token.get(j).getTokenResult());
						vo.setCount(Integer.parseInt(token.get(j).getTokenCount()));
						tlist.add(vo);
					}
				}
			}
			word.get(i).setSplitToken(tlist);
			word.get(i).setSurveyId(sur + "");
		}
		surveyResultCloud(word);

		model.addAttribute("exampleList", exampleList);
		model.addAttribute("word", word);
		model.addAttribute("quest", quest);
		return "survey/surveyResult";
	}
}
