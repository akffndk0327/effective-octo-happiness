package kr.or.ddit.alba.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.alba.vo.AlbaVO;
import kr.or.ddit.alba.vo.Lic_albaVO;
import kr.or.ddit.alba.vo.LicenseVO;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;

public class AlbaDaoImpl implements IAlbaDao {
	private SqlSessionFactory SqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory(); // 여기가 싱글톤 !

	@Override
	public List<AlbaVO> selectAlbaList() {
		try (
			SqlSession sqlSession = SqlSessionFactory.openSession(); // close시켜야해서 try 안에 넣엇음.
		) {
			IAlbaDao mapper = sqlSession.getMapper(IAlbaDao.class);
			return mapper.selectAlbaList();
		}
	}

	@Override
	public AlbaVO selctAlba(AlbaVO alba) {
		try (
			SqlSession sqlSession = SqlSessionFactory.openSession();
		) {
			IAlbaDao mapper = sqlSession.getMapper(IAlbaDao.class);
			return mapper.selctAlba(alba); // 매개변수를 return => 타입안정성 높아. 벗 속도가 느려
		}
	}

	@Override
	public int updateAlba(AlbaVO alba) {
		try (
			SqlSession sqlSession = SqlSessionFactory.openSession();
		) {
			IAlbaDao mapper = sqlSession.getMapper(IAlbaDao.class);
			int cnt = mapper.updateAlba(alba);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int intsertAlba(AlbaVO alba) {
		try (SqlSession sqlSession = SqlSessionFactory.openSession();) {
			IAlbaDao mapper = sqlSession.getMapper(IAlbaDao.class);
			int cnt = mapper.intsertAlba(alba);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int deleteAlba(String alba) {
		try (SqlSession sqlSession = SqlSessionFactory.openSession();) {

			IAlbaDao mapper = sqlSession.getMapper(IAlbaDao.class);
			int cnt = mapper.deleteAlba(alba);
			sqlSession.commit();
			return cnt;
		}
	}
	
	@Override
	public int deleteLicAlba(String al_id) {
		try (SqlSession sqlSession = SqlSessionFactory.openSession();) {
			IAlbaDao mapper = sqlSession.getMapper(IAlbaDao.class);
			int cnt = mapper.deleteLicAlba(al_id);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public List<LicenseVO> selectLic() {
		try (SqlSession sqlSession = SqlSessionFactory.openSession();) {
			IAlbaDao mapper = sqlSession.getMapper(IAlbaDao.class);
			List<LicenseVO> list = mapper.selectLic();
			return list;
		}
	}

	@Override
	public int insertLicAlba(Lic_albaVO licAlba) {
		try (SqlSession sqlSession = SqlSessionFactory.openSession();) {
			IAlbaDao mapper = sqlSession.getMapper(IAlbaDao.class);
			int cnt = mapper.insertLicAlba(licAlba);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public int deleteLic(Lic_albaVO licAlba) {
		try (SqlSession sqlSession = SqlSessionFactory.openSession();) {
			IAlbaDao mapper = sqlSession.getMapper(IAlbaDao.class);
			int cnt = mapper.deleteLic(licAlba);
			sqlSession.commit();
			return cnt;
		}
	}

	@Override
	public Lic_albaVO selectLicAlba(Lic_albaVO licAlba) {
		try (SqlSession sqlSession = SqlSessionFactory.openSession();) {
			IAlbaDao mapper = sqlSession.getMapper(IAlbaDao.class);
			Lic_albaVO vo = mapper.selectLicAlba(licAlba);
			return vo;
		}
	}

	@Override
	public int updateLicAlba(Lic_albaVO licAlba) {
		try (SqlSession sqlSession = SqlSessionFactory.openSession();) {
			IAlbaDao mapper = sqlSession.getMapper(IAlbaDao.class);
			int cnt = mapper.updateLicAlba(licAlba);
			sqlSession.commit();
			return cnt;
		}
	}


	

}
