package kr.or.ddit.idol.service;

import java.util.List;

public interface IIdolSearchService {
	public String readIdol(String code);
	public List<String> readIdols(); //스트링 배열을 문자열로 바꿔야함 
}
