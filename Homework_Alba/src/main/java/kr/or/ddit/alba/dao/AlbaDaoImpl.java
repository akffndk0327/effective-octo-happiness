package kr.or.ddit.alba.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.alba.vo.AlbaVO;
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
	public AlbaVO selctAlba(String al_id) {
		try (
			SqlSession sqlSession = SqlSessionFactory.openSession();
		) {
			IAlbaDao mapper = sqlSession.getMapper(IAlbaDao.class);
			return mapper.selctAlba(al_id); // 매개변수를 return => 타입안정성 높아. 벗 속도가 느려
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
	public int deleteAlba(AlbaVO alba) {
		try (SqlSession sqlSession = SqlSessionFactory.openSession();) {

			IAlbaDao mapper = sqlSession.getMapper(IAlbaDao.class);
			int cnt = mapper.deleteAlba(alba);
			sqlSession.commit();
			return cnt;
		}
	}

}
