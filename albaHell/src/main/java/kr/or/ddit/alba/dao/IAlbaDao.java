package kr.or.ddit.alba.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.alba.vo.AlbaVO;
import kr.or.ddit.alba.vo.Lic_albaVO;
import kr.or.ddit.alba.vo.PagingInfoVO;
@Repository
public interface IAlbaDao {
	
	public int insertAlba(AlbaVO albaVO, SqlSession sqlSession);
	 
	public AlbaVO selectAlba(String al_id);
	
	public int selectAlbaCount(PagingInfoVO<AlbaVO> pagingVO);

	public List<AlbaVO> selectAlbaList(PagingInfoVO<AlbaVO> pagingVO);

	public int updateAlba(AlbaVO albaVO, SqlSession sqlSession);

	public int deleteAlba(String al_id, SqlSession sqlSession);

	public int insertLicenses(AlbaVO albaVO, SqlSession sqlSession);
	
	public int deleteLicenses(AlbaVO albaVO, SqlSession sqlSession);
	
	public Lic_albaVO selectLicense(Lic_albaVO licAlba);
}
