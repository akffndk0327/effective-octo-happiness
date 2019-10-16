package kr.or.ddit.alba.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.alba.dao.IAlbaDao;
import kr.or.ddit.alba.vo.AlbaVO;
import kr.or.ddit.alba.vo.Lic_albaVO;
import kr.or.ddit.alba.vo.PagingInfoVO;
import kr.or.ddit.enums.ServiceResult;

@Service
public class AlbaServiceImpl implements IAlbaService {
	@Inject
	IAlbaDao albaDAO ;
	
	SqlSessionFactory sessionFactory = 
			kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public ServiceResult createAlba(AlbaVO albaVO) {
		try (SqlSession sqlSession = sessionFactory.openSession();) {
			int rowCnt = albaDAO.insertAlba(albaVO, sqlSession);
			List<Lic_albaVO> licAlbaList = albaVO.getLicenseList();
			if(licAlbaList!=null && licAlbaList.size()>0){
				albaDAO.insertLicenses(albaVO, sqlSession);
			}
			ServiceResult result = ServiceResult.FAILED;
			if (rowCnt > 0) {
				result = ServiceResult.OK;
				sqlSession.commit();
			}
			return result;
		}
	}

	@Override
	public AlbaVO retrieveAlba(String al_id) {
		AlbaVO albaVO = albaDAO.selectAlba(al_id);
		if (albaVO == null)
			throw new RuntimeException(al_id + " 알바생이 없음.");
		return albaVO;
	}

	@Override
	public int retrieveAlbaCount(PagingInfoVO<AlbaVO> pagingVO) {
		return albaDAO.selectAlbaCount(pagingVO);
	}

	@Override
	public List<AlbaVO> retrieveAlbaList(PagingInfoVO<AlbaVO> pagingVO) {
		return albaDAO.selectAlbaList(pagingVO);
	}

	@Override
	public ServiceResult modifyAlba(AlbaVO albaVO) {
		try (SqlSession sqlSession = sessionFactory.openSession();) {
			int rowCnt = albaDAO.updateAlba(albaVO, sqlSession);
			List<Lic_albaVO> licAlbaList = albaVO.getLicenseList();
			if(albaVO.getDeleteLic_codes()!=null) {
				rowCnt += albaDAO.deleteLicenses(albaVO, sqlSession);
			}
			if(licAlbaList!=null && licAlbaList.size()>0){
				rowCnt += albaDAO.insertLicenses(albaVO, sqlSession);
			}
			ServiceResult result = ServiceResult.FAILED;
			if (rowCnt > 0) {
				result = ServiceResult.OK;
				sqlSession.commit();
			}
			return result;
		}
	}

	@Override
	public ServiceResult removeAlba(String al_id) {
		try (SqlSession sqlSession = sessionFactory.openSession();) {
			AlbaVO albaVO = new AlbaVO();
			albaVO.setAl_id(al_id);
			// ******** 보유 라이센스 제거 후 개인정보 삭제
			int rowCnt = albaDAO.deleteLicenses(albaVO, sqlSession);
			rowCnt += albaDAO.deleteAlba(al_id, sqlSession);
			ServiceResult result = ServiceResult.FAILED;
			if (rowCnt > 0) {
				result = ServiceResult.OK;
				sqlSession.commit();
			}
			return result;
		}
	}
	
	@Override
	public Lic_albaVO retrieveLicense(Lic_albaVO licAlba) {
		return albaDAO.selectLicense(licAlba);
	}

	
	
	

	

}
