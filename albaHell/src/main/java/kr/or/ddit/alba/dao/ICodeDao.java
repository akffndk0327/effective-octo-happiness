package kr.or.ddit.alba.dao;

import java.util.List;
import java.util.Map;

public interface ICodeDao {
	public List<Map<String, String>> selectLicense();
	public List<Map<String, String>> selectGrades();
}
