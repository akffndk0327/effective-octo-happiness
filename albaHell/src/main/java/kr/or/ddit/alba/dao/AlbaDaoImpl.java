package kr.or.ddit.alba.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.alba.vo.AlbaVO;
import kr.or.ddit.alba.vo.Lic_albaVO;
import kr.or.ddit.alba.vo.PagingInfoVO;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;

@Repository
public class AlbaDaoImpl implements IAlbaDao {
//	private SqlSessionFactory SqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory(); // 여기가 싱글톤 !
	
	private SqlSessionFactory sessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertAlba(AlbaVO albaVO, SqlSession sqlSession) {
		return sqlSession.insert("kr.or.ddit.alba.dao.IAlbaDao.insertAlba", albaVO);
	}

	@Override
	public AlbaVO selectAlba(String albaId) {
		try(
			SqlSession sqlSession = sessionFactory.openSession();
		){
			return sqlSession.getMapper(IAlbaDao.class).selectAlba(albaId);
		}
	}

	@Override
	public int selectAlbaCount(PagingInfoVO<AlbaVO> pagingVO) {
		try(
			SqlSession session = sessionFactory.openSession();
		){
			return session.getMapper(IAlbaDao.class).selectAlbaCount(pagingVO);
		}
	}
	
	@Override
	public List<AlbaVO> selectAlbaList(PagingInfoVO<AlbaVO> pagingVO) {
		try(
			SqlSession session = sessionFactory.openSession();
		){
			return session.getMapper(IAlbaDao.class).selectAlbaList(pagingVO);
		}
	}

	@Override
	public int updateAlba(AlbaVO albaVO, SqlSession sqlSession) {
		int rowCnt = sqlSession.update("kr.or.ddit.alba.dao.IAlbaDAO.updateAlba", albaVO);
		return rowCnt;
	}

	@Override
	public int insertLicenses(AlbaVO albaVO, SqlSession sqlSession) {
		int rowCnt = sqlSession.insert("kr.or.ddit.alba.dao.IAlbaDAO.insertLicenses", albaVO);
		return rowCnt;
	}
	
	@Override
	public int deleteLicenses(AlbaVO albaVO, SqlSession sqlSession) {
		int rowCnt = sqlSession.delete("kr.or.ddit.alba.dao.IAlbaDAO.deleteLicenses", albaVO);
		return rowCnt;
	}
	
	@Override
	public int deleteAlba(String albaId, SqlSession sqlSession) {
		int rowCnt = sqlSession.delete("kr.or.ddit.alba.dao.IAlbaDAO.deleteAlba", albaId);
		return rowCnt;
	}
	
	@Override
	public Lic_albaVO selectLicense(Lic_albaVO licAlba) {
		try(
				SqlSession session = sessionFactory.openSession();
			){
				return session.getMapper(IAlbaDao.class).selectLicense(licAlba);
			}
	}

	

	
	}


	


